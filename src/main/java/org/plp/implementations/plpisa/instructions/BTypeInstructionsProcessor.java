package org.plp.implementations.plpisa.instructions;

import org.plp.implementations.plpisa.PlpArgumentType;
import org.plp.implementations.plpisa.PlpCurrentAddressManager;
import org.plp.isa.AsmArgument;
import org.plp.isa.AsmArgumentType;
import org.plp.isa.AsmInstructionAssembler;
import org.plp.isa.exceptions.AsmAssemblerException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This will
 */
public class BTypeInstructionsProcessor implements AsmInstructionAssembler {
    private final PlpCurrentAddressManager addressManager;
    private final List<AsmArgumentType> expectedArguments;
    private final String instructionName;
    private final int functionCode;

    public static final int MASK_5BIT = 0b011111;
    public static final int MASK_6BIT = 0b111111;
    public static final int MASK_16BIT = 0xFFFF;
    public static final int RS_POSITION = 21;
    public static final int RT_POSITION = 16;
    public static final int OP_CODE_POSITION = 26;
    public static final int LABEL_LOCATION = 0;

    /**
     *
     * @param instructionName
     * @param opCode
     */
    public BTypeInstructionsProcessor(String instructionName,
                                      int opCode,
                                      PlpCurrentAddressManager addressManager) {
        this.expectedArguments = new ArrayList<>();
        this.expectedArguments.add(PlpArgumentType.REGISTER);
        this.expectedArguments.add(PlpArgumentType.REGISTER);
        this.expectedArguments.add(PlpArgumentType.LABEL);

        this.instructionName = instructionName;
        this.functionCode = opCode;
        this.addressManager = addressManager;
    }

    /**
     * Given a set of arguments, this instruction will give the encoded value of that instruction
     *
     * @param arguments list of arguments of the instruction
     * @return encoded value of the instruction
     * @throws AsmAssemblerException - thrown if arguments are invalid
     */
    @Override
    public long assemble(List<AsmArgument> arguments) throws AsmAssemblerException {
        validateArguments(arguments);
        AsmArgument rtArgument = arguments.get(0);
        AsmArgument rsArgument = arguments.get(1);
        AsmArgument labelArgument = arguments.get(2);

        long encodedValue = 0;

        long branchTarget = labelArgument.encode() - (addressManager.getCurrentAddress() + 4);
        branchTarget /= 4;

        encodedValue |= (branchTarget & MASK_16BIT) << LABEL_LOCATION;
        encodedValue |= (rsArgument.encode() & MASK_5BIT) << RS_POSITION;
        encodedValue |= (rtArgument.encode() & MASK_5BIT )<< RT_POSITION;
        encodedValue |= (functionCode & MASK_6BIT) << OP_CODE_POSITION;

        this.addressManager.incrementAddressForInstruction();

        return encodedValue;
    }

    /**
     * Provides the list of argument types expected by the instruction in order
     *
     * @return Array of {@link AsmArgumentType}
     */
    @Override
    public List<AsmArgumentType> getArgumentTypesOfInstruction() {
        return Collections.unmodifiableList(expectedArguments);
    }

    /**
     * This represents the size of the encoded value in bytes that will be taken up in
     * disassembly representation
     *
     * @return size of encoded value
     */
    @Override
    public int getMemoryAllocationSize() {
        return 4;
    }

    /**
     * This will provide the instruction name which is processed by this object
     *
     * @return instruction name
     */
    @Override
    public String getInstructionName() {
        return instructionName;
    }

    /**
     * This will return the function code of this instruction
     *
     * @return opcode or function code used for encoding
     */
    @Override
    public int getFunctionCodeOfInstruction() {
        return functionCode;
    }
}
