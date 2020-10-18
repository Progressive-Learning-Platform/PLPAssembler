package org.plp.isa;

import org.plp.isa.exceptions.AsmAssemblerException;

/**
 * This represent a type of instruction and how it gets encoded given its arguments
 * Example include add instruction or sub instruction or jle instruction
 */
public interface AsmInstructionAssembler {

    /**
     * Given a set of arguments, this instruction will give the encoded value of that instruction
     *
     * @param arguments list of arguments of the instruction
     * @return encoded value of the instruction
     * @throws AsmAssemblerException - thrown if arguments are invalid
     */
    long assemble(AsmArgument[] arguments) throws AsmAssemblerException;

    /**
     * Provides the list of argument types expected by the instruction in order
     * @return Array of {@link AsmArgumentType}
     */
    AsmArgumentType[] getArgumentTypesOfInstruction();

    /**
     * This represents the size of the encoded value that will be taken up in
     * disassembly representation
     *
     * @return size of encoded value
     */
    int getMemoryAllocationSize();

}
