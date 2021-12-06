package org.plp.implementations.plpisa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class PlpInstructionDisassemblyTest {
    @Mock PlpProgram plpProgram1;
    @Mock PlpProgram plpProgram2;

    @Test
    public void testPlpInstructionDisassembly() {
        PlpFile plpFile1 = new PlpFile("test.asm", plpProgram1);
        PlpInstruction plpInstruction1 = new PlpInstruction(plpFile1, "addu $v0, $v1, $t0", 30L);
        PlpInstructionDisassembly instructionDisassembly = new PlpInstructionDisassembly(plpInstruction1, 12345L, 0x1000012);

        Assertions.assertEquals(12345L, instructionDisassembly.getInstructionCode());
        Assertions.assertEquals(0x1000012, instructionDisassembly.getAddress());
        Assertions.assertEquals(plpInstruction1, instructionDisassembly.getActualInstruction());
    }

    @Test
    public void testPlpInstructionDisassemblyEquality() {
        PlpFile plpFile1 = new PlpFile("test.asm", plpProgram1);
        PlpFile plpFile2 = new PlpFile("test.asm", plpProgram2);

        PlpInstruction plpInstruction1 = new PlpInstruction(plpFile1, "addu $v0, $v1, $t0", 30L);
        PlpInstruction plpInstruction2 = new PlpInstruction(plpFile2, "addu $v0, $v1, $t0", 30L);

        PlpInstructionDisassembly instructionDisassembly1 = new PlpInstructionDisassembly(plpInstruction1, 123456L, 0x1000012);
        PlpInstructionDisassembly instructionDisassembly2 = new PlpInstructionDisassembly(plpInstruction2, 123456L, 0x1000012);
        PlpInstructionDisassembly instructionDisassembly3 = new PlpInstructionDisassembly(plpInstruction1, 123457L, 0x1000012);
        PlpInstructionDisassembly instructionDisassembly4 = new PlpInstructionDisassembly(plpInstruction1, 123456L, 0x1000034);
        PlpInstructionDisassembly instructionDisassembly5 = new PlpInstructionDisassembly(plpInstruction1, 123456L, 0x1000012);

        Assertions.assertFalse(instructionDisassembly1.equals(instructionDisassembly2));
        Assertions.assertTrue(instructionDisassembly1.equals(instructionDisassembly1));
        Assertions.assertFalse(instructionDisassembly1.equals(plpInstruction2));
        Assertions.assertFalse(instructionDisassembly1.equals(instructionDisassembly3));
        Assertions.assertFalse(instructionDisassembly1.equals(instructionDisassembly4));
        Assertions.assertTrue(instructionDisassembly1.equals(instructionDisassembly5));




    }
}
