package org.plp.isa;

import java.util.List;

/**
 * This is the assembly program representation. It also handles the disk I/O of the program.
 */
public interface AsmFile {
    /**
     * This will write the inmemory instructions of the program represented as {@link #getInstructions()} to the
     * disk file whose path is given by {@link #getFilePath()}
     * @return true if it is successful to write to file, false if there is an error
     */
    boolean writeToFile();

    /**
     * This will provide the disk file path where the AsmFile will be written to by {@link #writeToFile()} and
     * similarly from where this will read the file in {@link #readFromFile()}
     * @return absolute path of the file
     */
    String getFilePath();

    /**
     *  This will update the disk file path where the AsmFile will be written to or read from. This is the setter
     *  part of {@link #getFilePath()}
     * @param filePath absolute path of the file
     */
    void setFilePath(String filePath);

    /**
     *  Given a list of instructions of the program, this is add it to its inmemory representation of that program
     * @param instructions List of instructions to be added to its in-memory representation
     * @return list of {@link AsmInstruction} which is equivalent of the string representation
     */
    List<AsmInstruction> addInstructionsToFile(List<String> instructions);

    /**
     *  Given a lineNumber, obtain the instruction present at that line
     * @param lineNumber line number in the file/program
     * @return {@link AsmInstruction} present at that line
     */
    AsmInstruction getInstructionAtLine(int lineNumber);

    /**
     *  Get all the instructions stored in memory of this file.
     *  Use this only after {@link #readFromFile()} or {@link #addInstructionsToFile(List)}
     * @return list of {@link AsmInstruction} of the file.
     */
    List<AsmInstruction> getInstructions();

    /**
     * This will read the content of the file present at {@link #getFilePath()} and store in memory all the
     * instructions.
     */
    void readFromFile();

}
