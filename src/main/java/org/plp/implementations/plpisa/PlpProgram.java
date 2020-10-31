package org.plp.implementations.plpisa;

import org.plp.isa.AsmFile;
import org.plp.isa.AsmProgram;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This is a PLP representation of {@link AsmProgram}. This holds all the asm files a PLP
 * program will hold and also maps to a directory where it will store these files.
 */
public class PlpProgram implements AsmProgram {

    private final Map<String, AsmFile> filesInProgram;
    private final Path programDirectory;

    /**
     * Constructor for a PlpProgram object
     *
     * @param programDirectory - the directory where the asm file(s) are stored
     * @throws IOException - thrown if the directory or files cannot be read
     */
    public PlpProgram(Path programDirectory) throws IOException{
        validatePath(programDirectory);
        filesInProgram = new HashMap<>();
        this.programDirectory = programDirectory;
        loadFilesToMemory();
    }

    /**
     * This verifies if given directory exists. If it does not, it will create one.
     *
     * @param programDirectory - directory path to be verified
     * @throws IOException if the program path is invalid (i.e. it is a directory)
     */
    private void validatePath(Path programDirectory) throws IOException {
        if(!Files.exists(programDirectory)) {
            Files.createDirectories(programDirectory);
        } else if(!Files.isDirectory(programDirectory)) {
            throw new IOException("Path provided has a file instead of directory - "
                    + programDirectory.toString());
        }
    }

    /**
     * Reads the content of the {@link #programDirectory} and if it has any {@link AsmFile}s
     * it will read them into memory.
     * @throws IOException if it fails to read the {@link AsmFile}s
     * @throws UnsupportedOperationException - thrown until implemented
     */
    private void loadFilesToMemory() throws IOException {
        if(Files.exists(programDirectory)) {
            try (Stream<Path> paths = Files.walk(programDirectory)) {
                List<Path> programFiles = paths.filter(Files::isRegularFile)
                        .collect(Collectors.toList());
                for(Path path: programFiles) {
                    AsmFile asmFile = new PlpFile(path);
                    filesInProgram.put(asmFile.getFileName(), asmFile);
                }

            }
        }
    }

    /**
     * Returns a list of {@link AsmFile}s which constitute this program
     *
     * @return - list of {@link AsmFile}
     */
    @Override
    public List<AsmFile> getAsmFilesOfProgram() {
        return new ArrayList<>(filesInProgram.values());
    }

    /**
     * Given a file name, returns the corresponding {@link AsmFile} stored in the program
     *
     * @param fileName - name of the given {@link AsmFile}
     * @return - null if there is no {@link AsmFile} with the corresponding name,
     *           otherwise the {@link AsmFile} with the given name
     */
    @Override
    public AsmFile getAsmFile(String fileName) {
        return filesInProgram.get(fileName);
    }

    /**
     * Copy the given {@link AsmFile} to this program. It is going to copy the content to the passed
     * {@link AsmFile} as {@link AsmFile} path is immutable.
     * @param asmFile AsmFile to be added to the program
     * @return returns the new {@link AsmFile} created from the content of passed {@link AsmFile}
     * @throws IOException if already a {@link AsmFile} with same name exists in program
     */
    @Override
    public AsmFile copyAsmFileToProgram(AsmFile asmFile) throws IOException{
        AsmFile newFile = null;
        if(!filesInProgram.containsKey(asmFile.getFileName())) {

            Path newFilePath = Paths.get(programDirectory.toString(), asmFile.getFileName());
            Files.copy(asmFile.getFilePath(),
                    newFilePath,
                    StandardCopyOption.REPLACE_EXISTING);

            newFile = new PlpFile(newFilePath);
            filesInProgram.put(newFile.getFileName(), newFile);
        } else {
            throw new IOException(String.format("File with same name %s exists in the program %s",
                    asmFile.getFileName(),
                    programDirectory.getFileName()));
        }
        return newFile;
    }

    /**
     * Creates a new {@link AsmFile} within in this program
     *
     * @param fileName - name of the {@link AsmFile} to be created
     * @return - {@link AsmFile} successfully created in the program, otherwise false
     * @throws IOException {@link AsmFile} file with same name already exists
     */
    @Override
    public AsmFile createAsmFileInProgram(String fileName) throws IOException{
        AsmFile plpFile = null;
        if(filesInProgram.containsKey(fileName)) {
            throw new IOException(String.format("File with same name %s exists in the program %s",
                    fileName,
                    programDirectory.getFileName()));
        } else {
            plpFile = new PlpFile(Paths.get(programDirectory.toString(), fileName));
            filesInProgram.put(plpFile.getFileName(), plpFile);
        }
        return plpFile;
    }

    /**
     * Provides the full path of the {@link AsmProgram} where the files are stored
     *
     * @return - absolute path of the program's directory.
     */
    @Override
    public String getProgramDirectory() {
        return programDirectory.toString();
    }
}
