package org.plp.implementations.plpisa.instructions;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PlpInstructionOpcodesTypes {

    private final BiMap<String, Integer> instructionOpcodeStore;
    private final Map<String, PlpInstructionType> instructionTypeStore;

    public PlpInstructionOpcodesTypes() {
        instructionOpcodeStore = HashBiMap.create();
        instructionTypeStore = new HashMap<>();
        initialize();
    }

    private void initialize() {

        instructionOpcodeStore.put("addu", 0x21);
        instructionTypeStore.put("addu", PlpInstructionType.R_TYPE_INSTRUCTION);
        instructionOpcodeStore.put("subu", 0x23);
        instructionTypeStore.put("subu", PlpInstructionType.R_TYPE_INSTRUCTION);
        instructionOpcodeStore.put("mullo", 0x10);
        instructionTypeStore.put("mullo", PlpInstructionType.R_TYPE_INSTRUCTION);
        instructionOpcodeStore.put("mulhi", 0x11);
        instructionTypeStore.put("mulhi", PlpInstructionType.R_TYPE_INSTRUCTION);
        instructionOpcodeStore.put("and", 0x24);
        instructionTypeStore.put("and", PlpInstructionType.R_TYPE_INSTRUCTION);
        instructionOpcodeStore.put("or", 0x25);
        instructionTypeStore.put("or", PlpInstructionType.R_TYPE_INSTRUCTION);
        instructionOpcodeStore.put("nor", 0x27);
        instructionTypeStore.put("nor", PlpInstructionType.R_TYPE_INSTRUCTION);
        instructionOpcodeStore.put("slt", 0x2A);
        instructionTypeStore.put("slt", PlpInstructionType.R_TYPE_INSTRUCTION);
        instructionOpcodeStore.put("sltu", 0x2B);
        instructionTypeStore.put("sltu", PlpInstructionType.R_TYPE_INSTRUCTION);
        instructionOpcodeStore.put("sllv", 0x01);
        instructionTypeStore.put("sllv", PlpInstructionType.R_TYPE_INSTRUCTION);
        instructionOpcodeStore.put("srlv", 0x03);
        instructionTypeStore.put("srlv", PlpInstructionType.R_TYPE_INSTRUCTION);

        instructionOpcodeStore.put("sll", 0x00);
        instructionTypeStore.put("sll", PlpInstructionType.RI_TYPE_INSTRUCTION);
        instructionOpcodeStore.put("srl", 0x02);
        instructionTypeStore.put("srl", PlpInstructionType.RI_TYPE_INSTRUCTION);

        instructionOpcodeStore.put("addiu", 0x09);
        instructionTypeStore.put("addiu", PlpInstructionType.I_TYPE_INSTRUCTION);
        instructionOpcodeStore.put("ori", 0x0D);
        instructionTypeStore.put("ori", PlpInstructionType.I_TYPE_INSTRUCTION);
        instructionOpcodeStore.put("andiu", 0x0C);
        instructionTypeStore.put("andiu", PlpInstructionType.I_TYPE_INSTRUCTION);
        instructionOpcodeStore.put("slti", 0x0A);
        instructionTypeStore.put("slti", PlpInstructionType.I_TYPE_INSTRUCTION);
        instructionOpcodeStore.put("sltiu", 0x0B);
        instructionTypeStore.put("sltiu", PlpInstructionType.I_TYPE_INSTRUCTION);

        instructionOpcodeStore.put("beq", 0x04);
        instructionTypeStore.put("beq", PlpInstructionType.B_TYPE_INSTRUCTION);
        instructionOpcodeStore.put("bne", 0x05);
        instructionTypeStore.put("bne", PlpInstructionType.B_TYPE_INSTRUCTION);

        instructionOpcodeStore.put("jr", 0x08);
        instructionTypeStore.put("jr", PlpInstructionType.RJ_TYPE_INSTRUCTION);
        instructionOpcodeStore.put("jalr", 0x09);
        instructionTypeStore.put("addiu", PlpInstructionType.RJ_TYPE_INSTRUCTION);

        instructionOpcodeStore.put("j", 0x02);
        instructionTypeStore.put("j", PlpInstructionType.J_TYPE_INSTRUCTION);
        instructionOpcodeStore.put("jal", 0x03);
        instructionTypeStore.put("jal", PlpInstructionType.J_TYPE_INSTRUCTION);

        instructionOpcodeStore.put("lw", 0x23);
        instructionTypeStore.put("lw", PlpInstructionType.LS_TYPE_INSTRUCTION);
        instructionOpcodeStore.put("sw", 0x2B);
        instructionTypeStore.put("sw", PlpInstructionType.LS_TYPE_INSTRUCTION);
    }

    public String instructionFromOpCode(Integer opCode) {
        return instructionOpcodeStore.inverse().get(opCode);
    }

    public PlpInstructionType getInstructionType(String instruction) {
        return instructionTypeStore.get(instruction);
    }

    public Integer opCodeFromInstruction(String instruction) {
        return instructionOpcodeStore.get(instruction);
    }

    public Set<String> getAvailableInstructions() {
        return Collections.unmodifiableSet(instructionOpcodeStore.keySet());
    }
}
