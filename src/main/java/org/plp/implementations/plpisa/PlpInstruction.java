package org.plp.implementations.plpisa;

import org.plp.isa.AsmFile;
import org.plp.isa.AsmInstruction;

/**
 * This represents an individual instruction in the {@link PlpProgram}
 */
public class PlpInstruction implements AsmInstruction {
    /**
     * The line number where this instruction is present in the {@link PlpFile}
     */
    private final long lineNumber;

    /**
     * The PlpFile where this instruction is located
     */
    private final AsmFile plpFile;

    /**
     * Actual content of the instruction
     */
    private final String instruction;

    /**
     * This will create an individual instruction of the PlpProgram
     * @param plpFile the PlpFile where instruction is located
     * @param instruction the actual instruction
     * @param lineNumber the line number of the PlpFile where the instruction is located
     */
    public PlpInstruction(AsmFile plpFile, String instruction, long lineNumber) {
        this.plpFile = plpFile;
        this.instruction = instruction;
        this.lineNumber = lineNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(!(obj instanceof PlpInstruction)) {
            return false;
        }

        PlpInstruction argument = (PlpInstruction) obj;

        return this.lineNumber == argument.getLineNumber() &&
                this.plpFile.equals(argument.getAsmFile()) &&
                this.instruction.equals(argument.getInstructionContents());
    }

    /**
     * Returns the line number on which the instruction appears. Note that most languages
     * (including PLP) will require each instruction to be on its own line. As such, no
     * two {@link AsmInstruction}s that originate from the same file may share the same
     * line number in these cases.
     *
     * @return The line number on which the instruction appears
     */
    @Override
    public long getLineNumber() {
        return lineNumber;
    }

    /**
     * Returns the assembly instruction as a String, as it was written in the asm file.
     * <p>
     * Example: "li $t4, 88"
     *
     * @return the assembly instruction string
     */
    @Override
    public String getInstructionContents() {
        return instruction;
    }

    /**
     * This provides the {@link AsmFile} in which this instruction resides
     *
     * @return {@link AsmFile} of the instruction
     */
    @Override
    public AsmFile getAsmFile() {
        return plpFile;
    }
}
