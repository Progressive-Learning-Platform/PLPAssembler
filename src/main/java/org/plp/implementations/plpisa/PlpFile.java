package org.plp.implementations.plpisa;

import org.plp.isa.AsmFile;
import org.plp.isa.AsmProgram;
import org.plp.isa.exceptions.AsmAssemblerException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is an in memory representation of an asm file {@link AsmFile}.
 * This will map to a file path and will be part of {@link PlpProgram}
 */
public class PlpFile implements AsmFile {
    private final Path filePath;
    private final List<String> fileContent;

    /**
     * Constructor for a PlpFile
     *
     * @param filePath - path to the physical file containing assembly commands
     * @throws IOException - thrown if file cannot be read
     */
    public PlpFile(Path filePath) throws IOException{
        validateFilePath(filePath);
        this.filePath = filePath;
        this.fileContent = new ArrayList<>();
        readFromFile();
    }

    private void validateFilePath(Path filePath) throws IOException {
        if(Files.exists(filePath) && Files.isDirectory(filePath)) {
            throw new IOException(
                    String.format("Given path - %s is a directory, will not be able " +
                                    "to create the PlpFile", filePath.toString()));
        }
    }

    /**
     * This will write the in-memory instructions of the program represented as
     * {@link #getInstructions()} to disk at the path given by {@link #getFilePath()}
     *
     * @return - true if writing to the file was successful, false otherwise
     */
    @Override
    public boolean writeToFile() {
        return false;
    }

    /**
     * This will provide the disk file path where the AsmFile will be written to by
     * {@link #writeToFile()} and similarly from where this will read the file in
     * {@link #readFromFile()}
     *
     * @return - absolute path of the file
     */
    @Override
    public String getFilePath() {
        return filePath.toAbsolutePath().toString();
    }

    /**
     * Given an instruction of the program, this will add it to its in-memory representation
     * of that program
     *
     * @param instruction - Instruction to be added to its in-memory representation
     * @return - true if instruction is successfully added, false otherwise
     */
    @Override
    public boolean addInstructionToFile(String instruction) {
        return false;
    }

    /**
     * Given a lineNumber, obtain the instruction present at that line
     *
     * @param lineNumber - line number in the file/program
     * @return - instruction present at that line
     * @throws AsmAssemblerException - thrown if encounters an invalid line number
     */
    @Override
    public String getInstructionAtLine(int lineNumber) throws AsmAssemblerException {
        if(lineNumber < 1 || lineNumber > fileContent.size()) {
            throw new AsmAssemblerException(
                    String.format("Invalid line number for the file. Line number should be " +
                                    "between 1 and %d", fileContent.size()));
        }
        return fileContent.get(lineNumber - 1);
    }

    /**
     * Get all the instructions stored in memory of this file.
     *
     * Use this only after {@link #readFromFile()} or {@link #addInstructionToFile(String)}
     *
     * @return - list of instructions present in the file.
     */
    @Override
    public List<String> getInstructions() {
        return Collections.unmodifiableList(fileContent);
    }

    /**
     * This will read the contents of the file present at {@link #getFilePath()} and store
     * all the instructions contained in the file in memory.
     *
     * @throws IOException - thrown if file cannot be read
     */
    @Override
    public void readFromFile() throws IOException {
        if(Files.exists(filePath)) {
            fileContent.clear();
            fileContent.addAll(Files.readAllLines(filePath));
        }
    }

    /**
     * Name of the file in which this file will be stored within in the {@link AsmProgram}
     *
     * @return - name of the file within the {@link AsmProgram}
     */
    @Override
    public String getFileName() {
        return filePath.getFileName().toString();
    }
}
