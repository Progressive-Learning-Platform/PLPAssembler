package org.plp.implementations.plpisa.instructions;

import org.plp.implementations.plpisa.PlpCurrentAddressManager;
import org.plp.isa.AsmArgument;
import org.plp.isa.AsmInstructionAssembler;
import org.plp.isa.exceptions.AsmAssemblerException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * This will store all the individual instruction handlers and based on the
 * instruction string, it will call the respective instruction handlers to encode
 */
public class PlpInstructionsPerformer {
    private final PlpCurrentAddressManager currentAddressManager;
    private final Map<String, Function<List<AsmArgument>, Long>> instructionsMap;
    private final PlpInstructionOpcodesTypes plpInstructionOpcodesTypes;

    private Function<List<AsmArgument>, Long> createInstructionProcessors(String instructionName,
                                                                          int opCode,
                                                                          PlpInstructionType typeOfInstruction) {
        AsmInstructionAssembler instructionProcessor;
        switch (typeOfInstruction) {
            case RI_TYPE_INSTRUCTION:
                instructionProcessor = new RITypeInstructionsProcessor(instructionName, opCode, currentAddressManager);
                break;
            case R_TYPE_INSTRUCTION:
                instructionProcessor = new RTypeInstructionsProcessor(instructionName, opCode, currentAddressManager);
                break;
            case B_TYPE_INSTRUCTION:
                instructionProcessor = new BTypeInstructionsProcessor(instructionName, opCode, currentAddressManager);
                break;
            default:
                instructionProcessor = new ITypeInstructionsProcessor(instructionName, opCode, currentAddressManager);
                break;
        }

        return instructionProcessor::assemble;
    }

    /**
     * This will create an object which will handle all instructions encoding
     * @param currentAddressManager {@link PlpCurrentAddressManager} handles queries regarding address of program
     */
    public PlpInstructionsPerformer(PlpCurrentAddressManager currentAddressManager,
                                    PlpInstructionOpcodesTypes plpInstructionOpcodesTypes) {
        this.currentAddressManager = currentAddressManager;
        this.plpInstructionOpcodesTypes = plpInstructionOpcodesTypes;
        instructionsMap = new HashMap<>();

        populateInstructionsMap();
    }

    private void populateInstructionsMap() {

        for(String instruction : this.plpInstructionOpcodesTypes.getAvailableInstructions()) {
            Integer opCode = plpInstructionOpcodesTypes.opCodeFromInstruction(instruction);
            PlpInstructionType instructionType = plpInstructionOpcodesTypes.getInstructionType(instruction);
            instructionsMap.put(instruction,
                    createInstructionProcessors(instruction, opCode, instructionType));
        }
    }

    /**
     * Given an instruction name and its list of arguments, this will call the
     * respective handler to encode the instruction
     * @param instruction instruction name whose handle to be called to encoding
     * @param arguments list of arguments needed to encode that instruction
     * @return encoded value of the instruction
     * @throws AsmAssemblerException if an instruction whose handler do not exist is passed
     */
    public Long perform(String instruction, List<AsmArgument> arguments) {
        if(!instructionsMap.containsKey(instruction)) {
            // TODO: Throw an exception, a non existent instruction is being executed
            throw new AsmAssemblerException(instruction + " - this instruction does not have an handler");
        }

        return instructionsMap.get(instruction).apply(arguments);
    }


}
