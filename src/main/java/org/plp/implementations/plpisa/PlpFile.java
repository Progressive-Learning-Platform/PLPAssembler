package org.plp.implementations.plpisa;

import org.plp.isa.AsmFile;
import org.plp.isa.AsmProgram;

import java.io.IOException;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

/**
 * This is an in memory representation of an asm file {@link AsmFile}.
 * This will map to a file path and will be part of {@link PlpProgram}
 */
public class PlpFile implements AsmFile {

    private final String fileName;
    private final AsmProgram asmProgram;
    private final List<String> instructions;

    /**
     * Constructor to create an {@link AsmFile} related to PLP
     * @param fileName Name of the File to be created
     * @param asmProgram {@link PlpProgram} where this file will exists
     */
    public PlpFile(String fileName, AsmProgram asmProgram) {
        this.fileName = fileName;
        this.asmProgram = asmProgram;
        this.instructions = new ArrayList<>();
    }

    /**
     * Constructor to create an {@link AsmFile} related to PLP with the instructions
     * @param fileName Name of the File to be created
     * @param asmProgram {@link PlpProgram} where this file will exists
     * @param instructions set of instructions present in this file
     */
    public PlpFile(String fileName, AsmProgram asmProgram, List<String> instructions) {
        this.fileName = fileName;
        this.asmProgram = asmProgram;
        this.instructions = new ArrayList<>(instructions);
    }

    /**
     * This will add the list of instructions at the end of file
     *
     * @param instructions list of instructions to be added
     * @throws IOException if it fails to update the physical file
     */
    @Override
    public void appendInstructionsToFile(List<String> instructions) throws IOException {
        this.instructions.addAll(instructions);
        this.asmProgram.saveProgram();
    }

    /**
     * This will add list of instructions from the lineNumber specified. \
     * If there are fewer lines in the file, then it will add lines at the end.
     *
     * @param lineNumber   Line number where the instructions need to be added
     * @param instructions list of instructions to be added to file.
     * @throws IOException if it fails to update the physical file
     */
    @Override
    public void addInstructionsAtLine(int lineNumber, List<String> instructions)
            throws IOException {
        if(lineNumber <= 0) {
            throw new IOException("Invalid Line Number; line number should be greater than 0");
        } else if(this.instructions.size() == 0 && lineNumber != 1) {
            throw new IOException("Invalid Line Number; Max line number can be 1");
        } else if(this.instructions.size() != 0 && lineNumber > this.instructions.size()) {
            throw new IOException("Invalid Line Number; Max line number can be " + this.instructions.size());
        } else {
            this.instructions.addAll(lineNumber - 1, instructions);
            this.asmProgram.saveProgram();
        }
    }

    /**
     * This will remove an instruction from the file at the given line number
     *
     * @param lineNumber line number at which instruction needs to be removed.
     * @throws IOException if it fails to update the physical file
     */
    @Override
    public void removeInstructionAtLine(int lineNumber) throws IOException {
        if(lineNumber > 0 && lineNumber <= this.instructions.size()) {
            this.instructions.remove(lineNumber - 1);
            this.asmProgram.saveProgram();

            return;
        }
        throw new IOException(
                "Invalid line number; Minimum to be 1 and Max line number can be: " + this.instructions.size());
    }

    /**
     * This will remove all the instructions present in the file
     *
     * @throws IOException if it fails to update the physical file
     */
    @Override
    public void clearInstructions() throws IOException {
        this.instructions.clear();
        this.asmProgram.saveProgram();
    }

    /**
     * Given a lineNumber, obtain the instruction present at that line
     *
     * @param lineNumber - line number in the file/program
     * @return - instruction present at that line
     * @throws IOException if there are no instructions at that line number
     */
    @Override
    public String getInstructionAtLine(int lineNumber) throws IOException {
        if(lineNumber > 0 && lineNumber <= this.instructions.size()) {
            return this.instructions.get(lineNumber - 1);
        }

        throw new IOException(
                "Invalid line number; " +
                        "Minimum line number can be 1 and Max line number can be: " + this.instructions.size());
    }

    /**
     * Get all the instructions stored in memory of this file.
     *
     * @return - list of instructions present in the file.
     */
    @Override
    public List<String> getInstructionsOfFile() {
        return Collections.unmodifiableList(this.instructions);
    }

    /**
     * Name of the file in which this file will be stored within in the {@link AsmProgram}
     *
     * @return - name of the file within the {@link AsmProgram}
     */
    @Override
    public String getFileName() {
        return this.fileName;
    }

    /**
     * The {@link AsmProgram} to which this {@link AsmFile} belongs to
     *
     * @return {@link AsmProgram} of the {@link AsmFile}
     */
    @Override
    public AsmProgram getAsmProgramOfFile() {
        return this.asmProgram;
    }
}
