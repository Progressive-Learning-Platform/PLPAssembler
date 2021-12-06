package org.plp.isa;

import org.plp.isa.exceptions.AsmAssemblerException;

import java.util.List;

/**
 * This will handle a particular pseudo instruction. Given its {@link AsmArgument} this will
 * provide the sub {@link AsmInstruction} which will form the part of this pseudo instruction.
 */
public interface AsmPseudoInstructionAssembler {

    /**
     * This will verify if passed arguments to Pseudo Instruction is of the same argument types as expected
     * @param asmArguments list of arguments passed to pseudo instruction
     * @throws AsmAssemblerException throws this if arguments mismatch with what is expected
     */
    default void validateArguments(List<AsmArgument> asmArguments) throws AsmAssemblerException {
        if(asmArguments.size() != getArgumentTypesOfPseudoInstruction().size()) {
            throw new AsmAssemblerException("Mismatch in number of arguments");
        }

        for(int i = 0; i < asmArguments.size(); i++) {
            if(asmArguments.get(i).getType() != getArgumentTypesOfPseudoInstruction().get(i)) {
                throw new AsmAssemblerException(
                        String.format("Expected Argument Type %s but received argument of type %s",
                                getArgumentTypesOfPseudoInstruction().get(i),
                                asmArguments.get(i).getType()));
            }
        }
    }

    /**
     * Given the {@link AsmArgument}s of the pseudo instruction, this will provide a string
     * which consists of the sub instruction that will constitute this pseudo instruction.
     * The multiple sub-instructions are `\n` separated
     *
     * @param arguments arguments of the pseudo instruction
     * @return \n separated sub instructions
     * @throws AsmAssemblerException - thrown if the sub-instruction String could not be built
     */
    String getActualInstructions(List<AsmArgument> arguments) throws AsmAssemblerException;

    /**
     * Provides the expected arguments of the pseudo instruction
     * @return array of argument types in order as expected by pseudo instruction
     */
    List<AsmArgumentType> getArgumentTypesOfPseudoInstruction();

}
