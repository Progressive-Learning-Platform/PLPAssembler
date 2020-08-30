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
     * Add the given list of {@link AsmFile} to this program
     * @param asmFiles list of AsmFiles to be added to the program
     * @return true if successfully able to add the files to program else false
     */
    boolean addAsmFilesToProgram(List<AsmFile> asmFiles);
}
