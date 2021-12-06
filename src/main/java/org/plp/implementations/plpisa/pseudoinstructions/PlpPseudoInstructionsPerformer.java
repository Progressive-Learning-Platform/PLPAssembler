package org.plp.implementations.plpisa.pseudoinstructions;

import org.plp.implementations.plpisa.PlpCurrentAddressManager;
import org.plp.isa.AsmArgument;
import org.plp.isa.exceptions.AsmAssemblerException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * This will have all the handlers of the pseudo instructions and based on the
 * given pseudo instruction name it will encode them by calling respecitve handlers
 */
public class PlpPseudoInstructionsPerformer {
    private final PlpCurrentAddressManager addressManager;
    private final Map<String, Function<List<AsmArgument>, String>> pseudoInstructionsMap;

    /**
     * TThis will create an object which will handle all pseudo instructions encoding
     * @param addressManager {@link PlpCurrentAddressManager} handles queries regarding address of program
     */
    public PlpPseudoInstructionsPerformer(PlpCurrentAddressManager addressManager) {
        this.addressManager = addressManager;
        pseudoInstructionsMap = new HashMap<>();
        populatePseudoInstructionsMap();
    }

    private void populatePseudoInstructionsMap() {
        NopPseudoInstructionProcessor nopPseudoInstructionProcessor = new NopPseudoInstructionProcessor(addressManager);
        pseudoInstructionsMap.put("nop", nopPseudoInstructionProcessor::getActualInstructions);
    }

    /**
     * Given a pseudo instruction name and its list of arguments, this will call the
     * respective handler to encode the pseudo instruction
     * @param pseudoInstruction pseudo instruction name whose handle to be called to encoding
     * @param arguments list of arguments needed to encode that pseudo instruction
     * @return encoded value of the pseudo instruction
     * @throws AsmAssemblerException if a pseudo instruction whose handler do not exist is passed
     */
    public String perform(String pseudoInstruction, List<AsmArgument> arguments) {
        if(!pseudoInstructionsMap.containsKey(pseudoInstruction)){
            //ToDo: no corresponding pseudo instruction processor found, throw error
            throw new AsmAssemblerException("There is no pseudo instruction by name " + pseudoInstruction);
        }
        return pseudoInstructionsMap.get(pseudoInstruction).apply(arguments);
    }
}
