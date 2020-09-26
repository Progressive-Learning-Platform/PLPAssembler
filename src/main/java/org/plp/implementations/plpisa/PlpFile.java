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
    private List<String> fileContent;

    public PlpFile(Path filePath) throws IOException{
        validateFilePath(filePath);
        this.filePath = filePath;
        this.fileContent = new ArrayList<>();
        readFromFile();
    }

    private void validateFilePath(Path filePath) throws IOException {
        if(Files.exists(filePath) && Files.isDirectory(filePath)) {
            throw new IOException(
                    String.format("Given path - %s is a directory, will not be able to create the PlpFile",
                            filePath.toString()));
        }
    }

    /**
     * This will write the inmemory instructions of the program represented as {@link #getInstructions()} to the
     * disk file whose path is given by {@link #getFilePath()}
     *
     * @return true if it is successful to write to file, false if there is an error
     */
    @Override
    public boolean writeToFile() {
        return false;
    }

    /**
     * This will provide the disk file path where the AsmFile will be written to by {@link #writeToFile()} and
     * similarly from where this will read the file in {@link #readFromFile()}
     *
     * @return absolute path of the file
     */
    @Override
    public String getFilePath() {
        return filePath.toAbsolutePath().toString();
    }

    /**
     * Given an instruction of the program, this will add it to its inmemory representation of that program
     *
     * @param instruction Instructions to be added to its in-memory representation
     * @return true is successfully added the instructions else false
     */
    @Override
    public boolean addInstructionToFile(String instruction) {
        return false;
    }

    /**
     * Given a lineNumber, obtain the instruction present at that line
     *
     * @param lineNumber line number in the file/program
     * @return Instruction present at that line
     */
    @Override
    public String getInstructionAtLine(int lineNumber) throws AsmAssemblerException {
        if(lineNumber < 1 || lineNumber > fileContent.size()) {
            throw new AsmAssemblerException(
                    String.format("Invalid line number for the file. Line number should be between 1 and %d",
                            fileContent.size()));
        }
        return fileContent.get(lineNumber - 1);
    }

    /**
     * Get all the instructions stored in memory of this file.
     * Use this only after {@link #readFromFile()} or {@link #addInstructionToFile(String)}
     *
     * @return list of instructions present in the file.
     */
    @Override
    public List<String> getInstructions() {
        return Collections.unmodifiableList(fileContent);
    }

    /**
     * This will read the content of the file present at {@link #getFilePath()} and store in memory all the
     * instructions.
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
     * @return name of the file within the {@link AsmProgram}
     */
    @Override
    public String getFileName() {
        return filePath.getFileName().toString();
    }
}
