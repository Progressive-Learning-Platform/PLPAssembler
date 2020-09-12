package org.plp.implementations.plpisa;

import org.plp.isa.AsmFile;
import org.plp.isa.AsmProgram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is an in memory representation of an asm file {@link AsmFile}.
 * This will map to a file path and will be part of {@link PlpProgram}
 */
public class PlpFile implements AsmFile {
    private final String fileName;
    private List<String> fileContent;
    private final AsmProgram program;

    public PlpFile(String fileName, AsmProgram program) throws IOException {
        validateFileName(fileName, program);
        this.fileName = fileName;
        this.program = program;
        this.fileContent = new ArrayList<>();
    }

    /**
     * Verify if {@link AsmProgram } already has an {@link AsmFile} with the given file name
     * @param fileName file name which needs to be verified
     * @param program AsmProgram in which file needs to be verified
     * @throws IOException if program already has an {@link AsmFile} with the same name
     */
    private void validateFileName(String fileName, AsmProgram program) throws IOException {
        if(program.getAsmFile(fileName) != null) {
            throw new IOException(String.format("Already a file with name %s exists in the Program", fileName));
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
        return null;
    }

    /**
     * This will update the disk file path where the AsmFile will be written to or read from. This is the setter
     * part of {@link #getFilePath()}
     *
     * @param filePath absolute path of the file
     */
    @Override
    public void setFilePath(String filePath) {

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
    public String getInstructionAtLine(int lineNumber) {
        return null;
    }

    /**
     * Get all the instructions stored in memory of this file.
     * Use this only after {@link #readFromFile()} or {@link #addInstructionToFile(String)}
     *
     * @return list of instructions present in the file.
     */
    @Override
    public List<String> getInstructions() {
        return null;
    }

    /**
     * This will read the content of the file present at {@link #getFilePath()} and store in memory all the
     * instructions.
     */
    @Override
    public void readFromFile() {

    }

    /**
     * {@link AsmProgram} to which this file belongs to.
     *
     * @return {@link AsmProgram} to which this file belongs
     */
    @Override
    public AsmProgram getProgramOfFile() {
        return null;
    }

    /**
     * Name of the file in which this file will be stored within in the {@link AsmProgram}
     *
     * @return name of the file within the {@link AsmProgram}
     */
    @Override
    public String getFileName() {
        return null;
    }
}
