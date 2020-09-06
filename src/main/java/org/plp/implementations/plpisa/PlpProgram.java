package org.plp.implementations.plpisa;

import org.plp.isa.AsmFile;
import org.plp.isa.AsmProgram;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the Plp representation of {@link AsmProgram}. This holds all the asm files a plp program will hold
 * and also maps to a directory where it will store these files.
 */
public class PlpProgram implements AsmProgram {

    private final Map<String, AsmFile> filesInProgram;
    private final Path programDirectory;

    public PlpProgram(Path programDirectory) throws IOException{
        validatePath(programDirectory);
        filesInProgram = new HashMap<>();
        this.programDirectory = programDirectory;
        loadFilesToMemory();
    }

    /**
     * This verifies if given directory exists. If it does not, it will create one.
     * @param programDirectory Directory Path which needs to be verified
     * @throws IOException if program's path is invalid that is path is a file instead of directory
     */
    private void validatePath(Path programDirectory) throws IOException {
        if(!Files.exists(programDirectory)) {
            Files.createDirectories(programDirectory);
        } else if(!Files.isDirectory(programDirectory)) {
            throw new IOException("Path provided has a file instead of directory - " + programDirectory.toString());
        }
    }

    /**
     * This is read the content of the {@link #programDirectory} and if it has any {@link AsmFile}
     * it will read it into memory.
     * @throws IOException if it fails to read the {@link AsmFile}s
     */
    private void loadFilesToMemory() throws IOException {
        // TODO: if the directory exists, read the asm files present in that directory.
        // TODO: This will be implemented after PlpFile is implemented.
    }

    /**
     * Provide the list of {@link AsmFile} which constitute this program
     *
     * @return list of {@link AsmFile}
     */
    @Override
    public List<AsmFile> getAsmFilesOfProgram() {
        return new ArrayList<>(filesInProgram.values());
    }

    /**
     * Add the given {@link AsmFile} to this program
     *
     * @param asmFile AsmFile to be added to the program
     * @return true if successfully able to add the file to program else false
     */
    @Override
    public boolean addAsmFileToProgram(AsmFile asmFile) {
        //ToBe Implemented
        return false;
    }

    /**
     * Creates a new {@link AsmFile} within in this program
     *
     * @param fileName Name of the {@link AsmFile} to be created
     * @return {@link AsmFile} created in the program else null in case of failures
     */
    @Override
    public AsmFile createAsmFileInProgram(String fileName) {
        //ToBe Implemented
        return null;
    }

    /**
     * Provide the full path of the {@link AsmProgram} where it stores its files
     *
     * @return Absolute path of the program's direcotry.
     */
    @Override
    public String getProgramDirectory() {
        return programDirectory.toString();
    }
}
