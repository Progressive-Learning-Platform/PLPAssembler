package org.plp.isa;

import java.io.IOException;
import java.nio.file.Path;
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
     * Creates a new {@link AsmFile} within in this program
     *
     * @param fileName - name of the {@link AsmFile} to be created
     * @return - {@link AsmFile} successfully created in the program, otherwise false
     * @throws IOException {@link AsmFile} file with same name already exists
     */
    AsmFile createAsmFileInProgram(String fileName) throws IOException;

    /**
     * Deletes an {@link AsmFile} with name fileName present in the Program
     *
     * @param fileName {@link AsmFile} name which needs to be deleted
     * @throws IOException {@link AsmFile} file with name does not exists or fails to delete
     */
    void deleteAsmFileInProgram(String fileName) throws IOException;

    /**
     * This helps to rename an existing {@link AsmFile} in the program to new {@link AsmFile} file
     *
     * @param currentName Existing name of the {@link AsmFile}
     * @param newName New Name of the {@link AsmFile}
     * @throws IOException {@link AsmFile} file with same new name already exists
     */
    void renameAsmFileInProgram(String currentName, String newName) throws IOException;

    /**
     * Get the program name
     *
     * @return name of the program
     */
    String getProgramName();

    /**
     * Full path of the Program
     * @return File location where the program is saved
     */
    Path getProgramLocation();

    /**
     * This will save the program in the disk
     * @throws IOException Any error during writing to disk
     */
    void saveProgram() throws IOException;

    /**
     * This will read the file from the disk to memory
     * @throws IOException Any error during the file reading
     */
    void loadProgram() throws IOException;
}
