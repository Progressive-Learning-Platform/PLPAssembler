package org.plp.implementations.plpisa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.plp.isa.AsmFile;
import org.plp.isa.AsmProgram;

import java.io.IOException;
import java.io.InputStream;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * This is a PLP representation of {@link AsmProgram}. This holds all the asm files a PLP
 * program will hold and also maps to a directory where it will store these files.
 */
public class PlpProgram implements AsmProgram {

    private final Path programLocation;
    private PlpProgramMetaData plpProgramMetaData;
    private static final String EXTENSION_OF_PROGRAM = ".plp";
    private static final String DEFAULT_MAIN_ASM_FILE = "main.asm";
    private static final String META_DATA_FILE = "metadata.json";
    private final Map<String, AsmFile> files;
    private ObjectMapper objectMapper = null;

    /**
     * Creates an {@link AsmProgram} for PLP
     * @param programName Name of the Program to be created
     * @param programLocation Location where the program needs to be created.
     * @throws IOException Any error during creation or loading of existing program
     */
    public PlpProgram(String programName, Path programLocation) throws IOException{
        this.objectMapper = new ObjectMapper();
        files = new HashMap<>();

        String fileName = programName;
        if(!programName.toLowerCase().endsWith(EXTENSION_OF_PROGRAM)) {
            fileName += EXTENSION_OF_PROGRAM;
        }

        this.programLocation = Paths.get(programLocation.toString(), fileName);

        if(Files.exists(this.programLocation)) {
            loadProgram();
        } else {
            this.plpProgramMetaData = new PlpProgramMetaData(programName);
            createAsmFileInProgram(DEFAULT_MAIN_ASM_FILE);
        }
    }

    /**
     * Provide the list of {@link AsmFile} which constitute this program
     *
     * @return list of {@link AsmFile}
     */
    @Override
    public List<AsmFile> getAsmFilesOfProgram() {
        return this.files.values().stream().collect(Collectors.toUnmodifiableList());
    }

    /**
     * Given a file name, returns the corresponding {@link AsmFile} stored in the program
     *
     * @param fileName - name of the given {@link AsmFile}
     * @return - null if there is no {@link AsmFile} with the corresponding name,
     * otherwise the {@link AsmFile} with the given name
     */
    @Override
    public AsmFile getAsmFile(String fileName) {
        return this.files.get(fileName);
    }

    /**
     * Creates a new {@link AsmFile} within in this program
     *
     * @param fileName - name of the {@link AsmFile} to be created
     * @return - {@link AsmFile} successfully created in the program, otherwise false
     * @throws IOException {@link AsmFile} file with same name already exists
     */
    @Override
    public AsmFile createAsmFileInProgram(String fileName) throws IOException {
        if(!this.files.containsKey(fileName)) {
            AsmFile asmFile = new PlpFile(fileName, this);
            addFileToProgram(asmFile);
            saveProgram();
            return asmFile;
        }

        throw new IOException(
                String.format("%s program already has a file with the same name as %s",
                        this.getProgramName(),
                        fileName));

    }

    /**
     * Deletes an {@link AsmFile} with name fileName present in the Program
     *
     * @param fileName {@link AsmFile} name which needs to be deleted
     * @throws IOException {@link AsmFile} file with name does not exists or fails to delete
     */
    @Override
    public void deleteAsmFileInProgram(String fileName) throws IOException {
        if(this.files.containsKey(fileName)) {
            deleteFileFromProgram(this.files.get(fileName));
            saveProgram();
        } else {
            throw new IOException(
                    String.format("%s program do not have file %s",
                            this.getProgramName(),
                            fileName));
        }
    }

    /**
     * This helps to rename an existing {@link AsmFile} in the program to new {@link AsmFile} file
     *
     * @param currentName Existing name of the {@link AsmFile}
     * @param newName     New Name of the {@link AsmFile}
     * @throws IOException {@link AsmFile} file with same new name already exists
     */
    @Override
    public void renameAsmFileInProgram(String currentName, String newName) throws IOException {
        if(this.files.containsKey(currentName) && !this.files.containsKey(newName)) {
            AsmFile oldFile = this.files.get(currentName);
            AsmFile newFile = new PlpFile(newName, this);
            newFile.appendInstructionsToFile(oldFile.getInstructionsOfFile());
            int index = this.plpProgramMetaData.getProgramFiles().indexOf(currentName);
            deleteAsmFileInProgram(currentName);
            addFileToProgramAtIndex(newFile, index);
            saveProgram();

        } else if(!this.files.containsKey(currentName)) {
            throw new IOException(
                    String.format("%s program does not have file %s", this.getProgramName(), currentName)
            );

        } else {
            throw new IOException(
                    String.format("%s program already has a file with same name - %s", this.getProgramName(), newName)
            );
        }
    }

    /**
     * Get the program name
     *
     * @return name of the program
     */
    @Override
    public String getProgramName() {
        return programLocation.getFileName().toString();
    }

    /**
     * Full path of the Program
     *
     * @return File location where the program is saved
     */
    @Override
    public Path getProgramLocation() {
        return programLocation;
    }

    /**
     * This will save the program in the disk
     * @throws IOException Any error during writing to disk
     */
    @Override
    public void saveProgram() throws IOException {
        try(ZipOutputStream zipOutputStream = new ZipOutputStream(
                new BufferedOutputStream(Files.newOutputStream(this.programLocation)))) {
            String metaDataContent = getMetaDataFileContent();
            zipOutputStream.putNextEntry(new ZipEntry(META_DATA_FILE));
            zipOutputStream.write(metaDataContent.getBytes(StandardCharsets.UTF_8));
            zipOutputStream.closeEntry();

            for(String asmFileName: this.plpProgramMetaData.getProgramFiles()) {
                zipOutputStream.putNextEntry(new ZipEntry(asmFileName));
                String fileContent = getAsmFileContentAsString(this.files.get(asmFileName));
                zipOutputStream.write(fileContent.getBytes(StandardCharsets.UTF_8));
                zipOutputStream.closeEntry();
            }
        }
    }

    /**
     * This will read the file from the disk to memory
     * @throws IOException Any error during the file reading
     */
    @Override
    public void loadProgram() throws IOException{
        try(ZipFile zipFile = new ZipFile(this.programLocation.toString())) {
            ZipEntry metaDataEntry = zipFile.getEntry("metadata.json");
            this.plpProgramMetaData = objectMapper.readValue(zipFile.getInputStream(metaDataEntry),
                    PlpProgramMetaData.class);
            for(String fileName: this.plpProgramMetaData.getProgramFiles()) {
                ZipEntry fileEntry = zipFile.getEntry(fileName);
                List<String> content = getContentOfFile(zipFile.getInputStream(fileEntry));
                PlpFile plpFile = new PlpFile(fileName, this, content);
                this.files.put(fileName, plpFile);
            }
        }

    }

    private void addFileToProgram(AsmFile asmFile) {
        this.files.put(asmFile.getFileName(), asmFile);
        this.plpProgramMetaData.addFileToProgram(asmFile.getFileName());
    }

    private void addFileToProgramAtIndex(AsmFile asmFile, int index) {
        this.files.put(asmFile.getFileName(), asmFile);
        this.plpProgramMetaData.addFileToProgramAtIndex(asmFile.getFileName(), index);
    }

    private void deleteFileFromProgram(AsmFile asmFile) {
        this.files.remove(asmFile.getFileName(), asmFile);
        this.plpProgramMetaData.deleteFileInProgram(asmFile.getFileName());
    }

    private String getMetaDataFileContent() throws JsonProcessingException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.plpProgramMetaData);
    }

    private String getAsmFileContentAsString(AsmFile asmFile) {
        List<String> instructions = asmFile.getInstructionsOfFile();
        if(instructions.size() == 0) {
            return "";
        }
        return String.join(System.lineSeparator(), instructions);
    }

    private List<String> getContentOfFile(InputStream inputStream) throws IOException{
        List<String> fileContent = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line = "";
            while((line = reader.readLine()) != null) {
                fileContent.add(line);
            }
        }

        return fileContent;
    }
}
