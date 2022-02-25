package org.plp.implementations.plpisa.instructions;

/**
 * Based on the way we encode PLP instructions, we are classifying them into
 * different categories
 */
public enum PlpInstructionType {
    /**
     * Example: addu $rd, $rs, $rt
     */
    R_TYPE_INSTRUCTION,

    /**
     * Example: sll $rd, $rt, shamt
     */
    RI_TYPE_INSTRUCTION,

    /**
     * Example: jr $rs
     */
    RJ_TYPE_INSTRUCTION,

    /**
     * Example: addiu $rt, $rs, imm
     */
    I_TYPE_INSTRUCTION,

    /**
     * Example: beq $rt, $rs, label
     */
    B_TYPE_INSTRUCTION,

    /**
     * Example: j label
     */
    J_TYPE_INSTRUCTION,

    /**
     * Example: lw $rt, imm($rs)
     */
    LS_TYPE_INSTRUCTION
}
