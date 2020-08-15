package org.plp.isa;

/**
 * Disassembly of the individual {@link AsmInstruction}
 */
public interface AsmInstructionDisassembly {
    /**
     * Assigned address where this disassembly will be stored
     * @return address
     */
    long getAddress();

    /**
     * Byte code / Machine code of the {@link AsmInstruction} to be stored in address
     * @return machine code of {@link AsmInstruction}
     */
    long getInstructionCode();

    /**
     * Reference to the actual instruction whose disassembly this represents
     * @return Asm Instruction whose disassembly this represents
     */
    AsmInstruction getActualInstruction();
}
