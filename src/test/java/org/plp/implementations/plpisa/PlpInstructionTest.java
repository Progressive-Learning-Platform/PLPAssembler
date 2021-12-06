package org.plp.implementations.plpisa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class PlpInstructionTest {
    @Mock PlpProgram plpProgram1;
    @Mock PlpProgram plpProgram2;

    @Test
    public void testPlpInstruction() {
        PlpFile plpFile1 = new PlpFile("test1.asm", plpProgram1);
        PlpInstruction plpInstruction = new PlpInstruction(plpFile1, "addu $v0, $a0, $a1", 30L);
        Assertions.assertEquals(plpInstruction.getLineNumber(), 30L);
        Assertions.assertEquals(plpInstruction.getInstructionContents(), "addu $v0, $a0, $a1");
        Assertions.assertEquals(plpInstruction.getAsmFile(), plpFile1);
    }

    @Test
    public void testPlpInstructionEquality() {
        PlpFile plpFile1 = new PlpFile("test1.asm", plpProgram1);
        PlpFile plpFile2 = new PlpFile("test2.asm", plpProgram2);
        PlpInstruction plpInstruction1 = new PlpInstruction(plpFile1, "addu $v0, $a0, $a1", 30L);
        PlpInstruction plpInstruction2 = new PlpInstruction(plpFile2, "addu $v0, $a0, $a1", 30L);
        PlpInstruction plpInstruction3 = new PlpInstruction(plpFile1, "addu $v0, $a0, $a1", 30L);
        PlpInstruction plpInstruction4 = new PlpInstruction(plpFile1, "addu $v0, $a0, $a1", 100L);
        PlpInstruction plpInstruction5 = new PlpInstruction(plpFile1, "subu $v0, $a0, $a1", 30L);

        Assertions.assertTrue(plpInstruction1.equals(plpInstruction1));
        Assertions.assertFalse(plpInstruction1.equals(plpInstruction2));
        Assertions.assertTrue(plpInstruction1.equals(plpInstruction3));
        Assertions.assertFalse(plpInstruction1.equals(plpInstruction4));
        Assertions.assertFalse(plpInstruction1.equals(plpInstruction5));
        Assertions.assertFalse(plpInstruction1.equals(plpFile1));
    }
}
