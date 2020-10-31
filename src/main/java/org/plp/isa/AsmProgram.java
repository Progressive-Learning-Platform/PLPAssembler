package org.plp.isa;

import java.io.IOException;
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
     * Copy the given {@link AsmFile} to this program. It is going to copy the content to the passed
     * {@link AsmFile} as {@link AsmFile} path is immutable.
     * @param asmFile AsmFile to be added to the program
     * @return returns the new {@link AsmFile} created from the content of passed {@link AsmFile}
     */
    AsmFile copyAsmFileToProgram(AsmFile asmFile) throws IOException;

    /**
     * Creates a new {@link AsmFile} within in this program
     * @param fileName Name of the {@link AsmFile} to be created
     * @return {@link AsmFile} created in the program else null in case of failures
     */
    AsmFile createAsmFileInProgram(String fileName) throws IOException;

    /**
     * Provide the full path of the {@link AsmProgram} where it stores its files
     * @return Absolute path of the program's direcotry.
     */
    String getProgramDirectory();
}
