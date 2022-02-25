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
 * This will encode all the instructions of type addiu $rt, $rs, imm
 */
public class ITypeInstructionsProcessor implements AsmInstructionAssembler {
    private final PlpCurrentAddressManager addressManager;
    private final List<AsmArgumentType> expectedArguments;
    private final String instructionName;
    private final int functionCode;

    private static final int MASK_5BIT = 0b011111;
    private static final int MASK_6BIT = 0b111111;
    private static final int MASK_16BIT = 0xFFFF;
    private static final int OP_CODE_POSITION = 26;
    private static final int RS_POSITION = 21;
    private static final int RT_POSITION = 16;
    private static final int IMMEDIATE_POSITION = 0;

    /**
     * This will create and instruction encoder which will encode instructions which have an immediate value
     * Example: addiu $rt, $rs, imm
     * @param instructionName Name of the instruction example addiu
     * @param opCode The opcode of the instruction
     * @param addressManager the current memory address manager
     */
    public ITypeInstructionsProcessor(String instructionName, int opCode, PlpCurrentAddressManager addressManager) {
        this.expectedArguments = new ArrayList<>();
        this.expectedArguments.add(PlpArgumentType.REGISTER);
        this.expectedArguments.add(PlpArgumentType.REGISTER);
        this.expectedArguments.add(PlpArgumentType.NUMERIC);

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
        AsmArgument immediateArgument = arguments.get(2);

        long encodedBitString = 0;
        encodedBitString |= (rsArgument.encode() & MASK_5BIT) << RS_POSITION;
        encodedBitString |= (rtArgument.encode() & MASK_5BIT) << RT_POSITION;
        encodedBitString |= (immediateArgument.encode() & MASK_16BIT) << IMMEDIATE_POSITION;
        encodedBitString |= (functionCode & MASK_6BIT) << OP_CODE_POSITION;

        addressManager.incrementAddressForInstruction();

        return encodedBitString;
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
