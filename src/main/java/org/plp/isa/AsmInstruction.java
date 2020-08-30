package org.plp.isa;

/**
 * Representation of an instruction at the assembly level.
 * <p>
 * {@link AsmInstruction}s are typically loaded from an asm file, into a list of
 * instructions. Each {@link AsmInstruction} is aware of its line number, as a measure of
 * human-visible lines in the asm file from which the instruction was loaded. File
 * headers, for instance, should not affect the line number of an {@link AsmInstruction}
 * <p>
 * The line-independent instruction can be retrieved with
 * {@link #getInstructionContents()}, which will return the instruction as a string, as it
 * appears in the asm file.
 * <p>
 * Note that {@link AsmInstruction} is an instruction at the <b>assembly level</b>. The
 * instruction may represent one or more machine instructions, or may represent a
 * directive or other preprocess instruction.
 * <p>
 * Comments are not typically considered an {@link AsmInstruction}, but these details are
 * left to the {@link AsmAssembler} implementation to decide.
 *
 * @author Moore, Zachary
 * @author Nesbitt, Morgan
 *
 */
public interface AsmInstruction {
    /**
     * Returns the line number on which the instruction appears. Note that most languages
     * (including PLP) will require each instruction to be on its own line. As such, no
     * two {@link AsmInstruction}s that originate from the same file may share the same
     * line number in these cases.
     *
     * @return The line number on which the instruction appears
     */
    long getLineNumber();

    /**
     * Returns the assembly instruction as a String, as it was written in the asm file.
     * <p>
     * Example: "li $t4, 88"
     *
     * @return the assembly instruction string
     */
    String getInstructionContents();

    /**
     * This provides the {@link AsmFile} in which this instruction resides
     * @return {@link AsmFile} of the instruction
     */
    AsmFile getAsmFile();
}
