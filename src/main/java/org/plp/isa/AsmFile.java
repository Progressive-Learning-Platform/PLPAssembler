package org.plp.isa;

import java.io.IOException;
import java.util.List;

/**
 * This is the assembly program representation. It also handles the disk I/O of the program.
 */
public interface AsmFile {
    /**
     * This will write the in-memory instructions of the program represented as
     * {@link #getInstructions()} to disk at the path given by {@link #getFilePath()}
     *
     * @return - true if writing to the file was successful, false otherwise
     */
    boolean writeToFile();

    /**
     * This will provide the disk file path where the AsmFile will be written to by
     * {@link #writeToFile()} and similarly from where this will read the file in
     * {@link #readFromFile()}
     *
     * @return - absolute path of the file
     */
    String getFilePath();

    /**
     * Given an instruction of the program, this will add it to its in-memory representation
     * of that program
     *
     * @param instruction - Instruction to be added to its in-memory representation
     * @return - true if instruction is successfully added, false otherwise
     */
    boolean addInstructionToFile(String instruction);

    /**
     * Given a lineNumber, obtain the instruction present at that line
     *
     * @param lineNumber - line number in the file/program
     * @return - instruction present at that line
     */
    String getInstructionAtLine(int lineNumber);

    /**
     * Get all the instructions stored in memory of this file.
     *
     * Use this only after {@link #readFromFile()} or {@link #addInstructionToFile(String)}
     *
     * @return - list of instructions present in the file.
     */
    List<String> getInstructions();

    /**
     * This will read the contents of the file present at {@link #getFilePath()} and store
     * all the instructions contained in the file in memory.
     *
     * @throws IOException - thrown if file cannot be read
     */
    void readFromFile() throws IOException;

    /**
     * Name of the file in which this file will be stored within in the {@link AsmProgram}
     *
     * @return - name of the file within the {@link AsmProgram}
     */
    String getFileName();

}
