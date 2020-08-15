package org.plp.isa;

/**
 * This represents an individual assembly instruction of a program
 */
public interface AsmInstruction {
    /**
     * Line number at which this instruction is stored
     * @return line number of the instruction
     */
    int getLineNumber();

    /**
     * Assembly Instruction
     * @return assembly instruction represented as string
     */
    String getInstruction();

    /**
     * File in which this instruction is stored
     * @return reference to {@link AsmFile} where this is present
     */
    AsmFile getASMFileOfInstruction();
}
