package org.plp.isa;

import org.plp.isa.exceptions.AsmAssemblerException;

import java.util.List;

/**
 * This represents a type of instruction and how it gets encoded given its arguments
 * Example include add instruction or sub instruction or jle instruction
 */
public interface AsmInstructionAssembler {

    /**
     * This will verify if passed arguments to instruction is of the same argument types as expected
     * @param asmArguments list of arguments passed to instruction
     * @throws AsmAssemblerException throws this if arguments mismatch with what is expected
     */
    default void validateArguments(List<AsmArgument> asmArguments) throws AsmAssemblerException {
        if(asmArguments.size() != getArgumentTypesOfInstruction().size()) {
            throw new AsmAssemblerException("Mismatch in number of arguments");
        }

        for(int i = 0; i < asmArguments.size(); i++) {
            if(asmArguments.get(i).getType() != getArgumentTypesOfInstruction().get(i)) {
                throw new AsmAssemblerException(
                        String.format("Expected Argument Type %s but received argument of type %s",
                                getArgumentTypesOfInstruction().get(i),
                                asmArguments.get(i).getType()));
            }
        }
    }

    /**
     * Given a set of arguments, this instruction will give the encoded value of that instruction
     *
     * @param arguments list of arguments of the instruction
     * @return encoded value of the instruction
     * @throws AsmAssemblerException - thrown if arguments are invalid
     */
    long assemble(List<AsmArgument> arguments) throws AsmAssemblerException;

    /**
     * Provides the list of argument types expected by the instruction in order
     * @return Array of {@link AsmArgumentType}
     */
    List<AsmArgumentType> getArgumentTypesOfInstruction();

    /**
     * This represents the size of the encoded value that will be taken up in
     * disassembly representation
     *
     * @return size of encoded value
     */
    int getMemoryAllocationSize();

    /**
     * This will provide the instruction name which is processed by this object
     * @return instruction name
     */
    String getInstructionName();

    /**
     * This will return the function code of this instruction
     * @return opcode or function code used for encoding
     */
    int getFunctionCodeOfInstruction();

}
