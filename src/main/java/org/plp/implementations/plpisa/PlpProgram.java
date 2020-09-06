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

public class PlpProgram implements AsmProgram {

    private final Map<String, AsmFile> filesInProgram;
    private final Path programDirectory;

    public PlpProgram(Path programDirectory) throws IOException{
        filesInProgram = new HashMap<>();
        this.programDirectory = programDirectory;
        init();
    }

    private void init() throws IOException {
        if(!Files.exists(programDirectory)) {
            Files.createDirectories(programDirectory);
        } else if(!Files.isDirectory(programDirectory)) {
            throw new IOException("Path provided has a file instead of directory - " + programDirectory.toString());
        }
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
