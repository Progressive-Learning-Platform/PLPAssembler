package org.plp.implementations.plpisa.pseudoinstructions;

import org.plp.implementations.plpisa.PlpCurrentAddressManager;
import org.plp.isa.AsmArgument;
import org.plp.isa.AsmArgumentType;
import org.plp.isa.AsmPseudoInstructionAssembler;
import org.plp.isa.exceptions.AsmAssemblerException;

import java.util.ArrayList;
import java.util.List;

/**
 * This handles the nop pseudo instruction
 * Example nop
 */
public class NopPseudoInstructionProcessor implements AsmPseudoInstructionAssembler {
    private final PlpCurrentAddressManager addressManager;
    private final List<AsmArgumentType> asmArgumentTypes;

    /**
     * This will create a handle which handles the nop pseudo instruction
     * @param addressManager This will handle the current address of the program
     */
    public NopPseudoInstructionProcessor(PlpCurrentAddressManager addressManager) {
        this.addressManager = addressManager;
        asmArgumentTypes = new ArrayList<>();
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
    @Override
    public String getActualInstructions(List<AsmArgument> arguments) throws AsmAssemblerException {
        validateArguments(arguments);
        addressManager.incrementAddressForInstruction();
        return "sll $zero, $zero, 0";
    }

    /**
     * Provides the expected arguments of the pseudo instruction
     *
     * @return array of argument types in order as expected by pseudo instruction
     */
    @Override
    public List<AsmArgumentType> getArgumentTypesOfPseudoInstruction() {
        return asmArgumentTypes;
    }
}
