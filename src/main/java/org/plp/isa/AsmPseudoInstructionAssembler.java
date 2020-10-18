package org.plp.isa;

import org.plp.isa.exceptions.AsmAssemblerException;

/**
 * This will handle a particular pseudo instruction. Given its {@link AsmArgument} this will
 * provide the sub {@link AsmInstruction} which will form the part of this pseudo instruction.
 */
public interface AsmPseudoInstructionAssembler {
    /**
     * Given the {@link AsmArgument}s of the pseudo instruction, this will provide a string
     * which consists of the sub instruction that will constitute this pseudo instruction.
     * The multiple sub-instructions are `\n` separated
     *
     * @param arguments arguments of the pseudo instruction
     * @return \n separated sub instructions
     * @throws AsmAssemblerException - thrown if the sub-instruction String could not be built
     */
    String getActualInstructions(AsmArgument[] arguments) throws AsmAssemblerException;

    /**
     * Provides the expected arguments of the pseudo instruction
     * @return array of argument types in order as expected by pseudo instruction
     */
    AsmArgumentType[] getArgumentTypesOfPseudoInstruction();

}
