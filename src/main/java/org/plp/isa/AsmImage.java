package org.plp.isa;

import java.util.List;

/**
 * This is the product of assembling list of {@link AsmFile}.
 * It has map for instruction to disassembly of that instruction.
 */
public interface AsmImage {
    /**
     * This provides all the disassembly of the instructions which is stored in-memory.
     * @return list of disassembly of the instruction
     */
    List<AsmInstructionDisassembly> getDisassemblyOfInstructions();

    /**
     * Given an instruction,this will provide its corresponding disassembly
     * @param asmInstruction Asm Instruction whose disassembly needs to be obtained
     * @return Disassembly of the instruction
     */
    AsmInstructionDisassembly getDisassemblyOfInstruction(AsmInstruction asmInstruction);

    /**
     * Given a disassembly, provide the corresponding instruction whose disassembly it is
     * @param asmInstructionDisassembly Disassembly of the instruction
     * @return Assembly instruction of the given disassembly
     */
    AsmInstruction getInstructionOfDisassembly(AsmInstructionDisassembly asmInstructionDisassembly);

    /**
     * This will store the list of disassembly to in-memory store which are generated
     * from the instructions.
     *
     * @param asmInstructionDisassemblyList list of disassembly of instructions.
     */
    void addDisassemblyOfInstructions(
            List<AsmInstructionDisassembly> asmInstructionDisassemblyList);

    /**
     * This provides the {@link AsmProgram} whose asm image this is
     * @return Assembly program whose disassembly this is.
     */
    AsmProgram getProgram();
}
