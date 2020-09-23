package org.plp.isa;

import java.util.List;

/**
 * An Assembly program can be distributed across multiple asm files.
 */
public interface AsmProgram {
    /**
     * Provide the list of {@link AsmFile} which constitute this program
     * @return list of {@link AsmFile}
     */
    List<AsmFile> getAsmFilesOfProgram();

    /**
     * Given a file name return the corresponding {@link AsmFile} stored in the program
     * @param fileName name of the {@link AsmFile} searching for
     * @return null if there is no {@link AsmFile} with the corresponding name else {@link AsmFile} with the name
     */
    AsmFile getAsmFile(String fileName);

    /**
     * Add the given {@link AsmFile} to this program
     * @param asmFile AsmFile to be added to the program
     * @return true if successfully able to add the file to program else false
     */
    boolean addAsmFileToProgram(AsmFile asmFile);

    /**
     * Creates a new {@link AsmFile} within in this program
     * @param fileName Name of the {@link AsmFile} to be created
     * @return {@link AsmFile} created in the program else null in case of failures
     */
    AsmFile createAsmFileInProgram(String fileName);

    /**
     * Provide the full path of the {@link AsmProgram} where it stores its files
     * @return Absolute path of the program's direcotry.
     */
    String getProgramDirectory();
}
