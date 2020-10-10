package org.plp.isa;

import java.io.IOException;
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
    boolean writeToFile() throws IOException;

    /**
     * This will provide the disk file path where the AsmFile will be written to by {@link #writeToFile()} and
     * similarly from where this will read the file in {@link #readFromFile()}
     * @return absolute path of the file
     */
    String getFilePath();

    /**
     * Given an instruction of the program, this will add it to its inmemory representation of that program
     * @param instruction Instructions to be added to its in-memory representation
     * @return true is successfully added the instructions else false
     */
    boolean addInstructionToFile(String instruction);

    /**
     *  Given a lineNumber, obtain the instruction present at that line
     * @param lineNumber line number in the file/program
     * @return Instruction present at that line
     */
    String getInstructionAtLine(int lineNumber);

    /**
     *  Get all the instructions stored in memory of this file.
     *  Use this only after {@link #readFromFile()} or {@link #addInstructionToFile(String)}
     * @return list of instructions present in the file.
     */
    List<String> getInstructions();

    /**
     * This will read the content of the file present at {@link #getFilePath()} and store in memory all the
     * instructions.
     */
    void readFromFile() throws IOException;

    /**
     * Name of the file in which this file will be stored within in the {@link AsmProgram}
     * @return name of the file within the {@link AsmProgram}
     */
    String getFileName();

}
