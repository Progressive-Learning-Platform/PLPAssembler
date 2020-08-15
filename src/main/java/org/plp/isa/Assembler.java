package org.plp.isa;

import java.util.List;

/**
 * A generic assembler which takes in a list of files which has assembly instructions and generates a disassembly
 */
public interface Assembler {

    /**
     * Assembles a list of assembly files and generates an {@link AsmImage}, which has disassembly of the instructions
     * found in those files
     * @param asmFileList list of {@link AsmFile} each containing assembly instructions
     * @return An {@link AsmImage} containing the disassembly of the instructions found in {@link AsmFile}
     */
    AsmImage assemble(List<AsmFile> asmFileList);
}
