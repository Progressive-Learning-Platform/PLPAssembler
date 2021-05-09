package org.plp.isa;

import java.io.IOException;
import java.util.List;

/**
 * This is the assembly program representation. It also handles the disk I/O of the program.
 */
public interface AsmFile {

    /**
     * This will add the list of instructions at the end of file
     * @param instructions list of instructions to be added
     * @throws IOException if it fails to update the physical file
     */
    void appendInstructionsToFile(List<String> instructions) throws IOException;

    /**
     * This will add list of instructions from the lineNumber specified. \
     * If there are fewer lines in the file, then it will add lines at the end.
     * @param lineNumber Line number where the instructions need to be added
     * @param instructions list of instructions to be added to file.
     * @throws IOException if it fails to update the physical file
     */
    void addInstructionsAtLine(int lineNumber, List<String> instructions) throws IOException;

    /**
     * This will remove an instruction from the file at the given line number
     * @param lineNumber line number at which instruction needs to be removed.
     * @throws IOException if it fails to update the physical file
     */
    void removeInstructionAtLine(int lineNumber) throws IOException;

    /**
     * This will remove all the instructions present in the file
     * @throws IOException if it fails to update the physical file
     */
    void clearInstructions() throws IOException;

    /**
     * Given a lineNumber, obtain the instruction present at that line
     *
     * @param lineNumber - line number in the file/program
     * @return - instruction present at that line
     * @throws IOException if there are no instructions at that line number
     */
    String getInstructionAtLine(int lineNumber) throws IOException;

    /**
     * Get all the instructions stored in memory of this file.
     *
     * @return - list of instructions present in the file.
     */
    List<String> getInstructionsOfFile();


    /**
     * Name of the file in which this file will be stored within in the {@link AsmProgram}
     *
     * @return - name of the file within the {@link AsmProgram}
     */
    String getFileName();

    /**
     * The {@link AsmProgram} to which this {@link AsmFile} belongs to
     * @return {@link AsmProgram} of the {@link AsmFile}
     */
    AsmProgram getAsmProgramOfFile();
}
