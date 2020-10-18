package org.plp.isa;

import org.plp.isa.exceptions.AsmAssemblerException;

/**
 * A generic assembler which takes in a list of files that have assembly instructions
 * and generates a disassembly
 */
public interface AsmAssembler {

    /**
     * Assembles a list of assembly files and generates an {@link AsmImage}, which has
     * a disassembly of the instructions found in those files
     *
     * @param asmProgram - {@link AsmProgram} which has
     *                   the list {@link AsmFile} which has instructions.
     * @return - An {@link AsmImage} containing the disassembly of
     *                   the instructions found in {@link AsmFile}
     * @throws AsmAssemblerException - thrown when assembly fails
     */
    AsmImage assemble(AsmProgram asmProgram) throws AsmAssemblerException;
}
