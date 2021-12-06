package org.plp.implementations.plpisa;

import org.plp.isa.AsmInstruction;
import org.plp.isa.AsmInstructionDisassembly;

/**
 * This represents the disassembly information of each instruction of a {@link PlpProgram}
 */
public class PlpInstructionDisassembly implements AsmInstructionDisassembly {
    /**
     * this is the memory location where this instruction will be encoded
     */
    private final long address;

    /**
     * this is the machine code/encoded representation of the instruction
     */
    private final long machineCode;

    /**
     * this is the actual instruction whose disassembly this object represents
     */
    private final AsmInstruction plpInstruction;

    /**
     * This creates the disassembly of a given {@link PlpInstruction}
     * @param plpInstruction this is the instruction whose disassembly this represents
     * @param machineCode the machine code of the instruction
     * @param address the address where this machine code will be encoded
     */
    public PlpInstructionDisassembly(AsmInstruction plpInstruction, long machineCode, long address) {
        this.address = address;
        this.machineCode = machineCode;
        this.plpInstruction = plpInstruction;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(!(obj instanceof PlpInstructionDisassembly)) {
            return false;
        }

        PlpInstructionDisassembly argument = (PlpInstructionDisassembly) obj;

        return this.address == argument.getAddress() &&
                this.plpInstruction.equals(argument.getActualInstruction()) &&
                this.machineCode == argument.getInstructionCode();
    }

    /**
     * Assigned address where this disassembly will be stored
     *
     * @return address
     */
    @Override
    public long getAddress() {
        return address;
    }

    /**
     * Byte code / Machine code of the {@link AsmInstruction} to be stored in address
     *
     * @return machine code of {@link AsmInstruction}
     */
    @Override
    public long getInstructionCode() {
        return machineCode;
    }

    /**
     * Reference to the actual instruction whose disassembly this represents
     *
     * @return Asm Instruction whose disassembly this represents
     */
    @Override
    public AsmInstruction getActualInstruction() {
        return plpInstruction;
    }
}
