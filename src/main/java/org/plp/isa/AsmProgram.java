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
     * Given a file name, returns the corresponding {@link AsmFile} stored in the program
     *
     * @param fileName - name of the given {@link AsmFile}
     * @return - null if there is no {@link AsmFile} with the corresponding name,
     *           otherwise the {@link AsmFile} with the given name
     */
    AsmFile getAsmFile(String fileName);

    /**
     * Adds the given {@link AsmFile} to this program
     *
     * @param asmFile - AsmFile to be added to the program
     * @return - true if successfully added the file to program, false otherwise
     */
    boolean addAsmFileToProgram(AsmFile asmFile);

    /**
     * Creates a new {@link AsmFile} within in this program
     *
     * @param fileName - name of the {@link AsmFile} to be created
     * @return - {@link AsmFile} successfully created in the program, otherwise false
     */
    AsmFile createAsmFileInProgram(String fileName);

    /**
     * Provides the full path of the {@link AsmProgram} where the files are stored
     *
     * @return - absolute path of the program's directory.
     */
    String getProgramDirectory();
}
