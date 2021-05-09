package org.plp.implementations.plpisa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.plp.isa.AsmProgram;

import java.io.IOException;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class PlpFileTest {

    @Mock
    AsmProgram mockAsmProgram;

    PlpFile plpFile;

    @BeforeEach
    void setup() {
        plpFile = new PlpFile("testFile.asm", mockAsmProgram);
    }

    @Test
    void createPlpFileSuccessFulTest() throws IOException {
        Assertions.assertEquals("testFile.asm", plpFile.getFileName());
        Assertions.assertEquals(0, plpFile.getInstructionsOfFile().size());
        Assertions.assertEquals(mockAsmProgram, plpFile.getAsmProgramOfFile());
    }

    @Test
    void testAppendInstructionsToFile() throws IOException {
        Assertions.assertEquals(0, plpFile.getInstructionsOfFile().size());
        plpFile.appendInstructionsToFile(Arrays.asList(".org 0x100000"));
        Assertions.assertEquals(".org 0x100000", plpFile.getInstructionAtLine(1));
        plpFile.appendInstructionsToFile(Arrays.asList("# this is a comment in 2nd line"));
        Assertions.assertEquals(2, plpFile.getInstructionsOfFile().size());
        Assertions.assertEquals("# this is a comment in 2nd line", plpFile.getInstructionAtLine(2));
    }

    @Test
    void testAddInstructionAtLine() throws IOException {
        Assertions.assertEquals(0, plpFile.getInstructionsOfFile().size());
        plpFile.addInstructionsAtLine(1, Arrays.asList(".org 0x100000"));
        Assertions.assertEquals(".org 0x100000", plpFile.getInstructionAtLine(1));
        plpFile.addInstructionsAtLine(1, Arrays.asList("# This is an example"));
        Assertions.assertEquals("# This is an example", plpFile.getInstructionAtLine(1));
        Assertions.assertEquals(2, plpFile.getInstructionsOfFile().size());
        plpFile.addInstructionsAtLine(2, Arrays.asList("addu $t1, $t2, $t3"));
        Assertions.assertEquals("addu $t1, $t2, $t3", plpFile.getInstructionAtLine(2));
        Assertions.assertEquals(3, plpFile.getInstructionsOfFile().size());

    }

    @Test
    void testAddInstructionAtLineNegativeLineNumber() throws Exception {
        Assertions.assertThrows(IOException.class, () -> {
            plpFile.addInstructionsAtLine(-1, Arrays.asList(".org 0x100000"));
        }, "Invalid Line Number; line number should be greater than 0");
    }

    @Test
    void testAddInstructionAtLineFirstLineNumber() throws Exception {
        Assertions.assertThrows(IOException.class, () -> {
            plpFile.addInstructionsAtLine(2, Arrays.asList(".org 0x100000"));
        }, "Invalid Line Number; Max line number can be 1");
    }

    @Test
    void testAddInstructionAtLineWrongLineNumber() throws Exception {
        plpFile.addInstructionsAtLine(1, Arrays.asList(".org 0x100000", "# This is a comment"));

        Assertions.assertThrows(IOException.class, () -> {
            plpFile.addInstructionsAtLine(4, Arrays.asList("addu"));
        }, "Invalid Line Number; Max line number can be 2");
    }

    @Test
    void testRemoveInstructionWithNegativeLineNumber() throws Exception {
        Assertions.assertThrows(IOException.class, () -> {
            plpFile.removeInstructionAtLine(-2);
        }, "Invalid line number; Minimum to be 1 and Max line number can be: 0");
    }

    @Test
    void testRemoveInstructionWithInvalidLineNumber() throws Exception {
        plpFile.appendInstructionsToFile(Arrays.asList("#This is a test program", ".org 0x100000"));
        Assertions.assertEquals(2, plpFile.getInstructionsOfFile().size());
        Assertions.assertThrows(IOException.class, () -> {
            plpFile.removeInstructionAtLine(10);
        }, "Invalid line number; Minimum to be 1 and Max line number can be: 2");
    }

    @Test
    void testRemoveInstructionWithSuccess() throws Exception {
        plpFile.appendInstructionsToFile(Arrays.asList("#This is a test program", ".org 0x100000", "addu $t1, $t2, $t3"));
        Assertions.assertEquals(3, plpFile.getInstructionsOfFile().size());
        plpFile.removeInstructionAtLine(2);
        Assertions.assertFalse(plpFile.getInstructionsOfFile().contains(".org 0x100000"));
        Assertions.assertEquals(2, plpFile.getInstructionsOfFile().size());
        plpFile.removeInstructionAtLine(2);
        Assertions.assertFalse(plpFile.getInstructionsOfFile().contains("addu $t1, $t2, $t3"));
        Assertions.assertEquals(1, plpFile.getInstructionsOfFile().size());
        plpFile.removeInstructionAtLine(1);
        Assertions.assertEquals(0, plpFile.getInstructionsOfFile().size());
    }

    @Test
    void testClearInstructions() throws Exception {
        Assertions.assertEquals(0, plpFile.getInstructionsOfFile().size());
        plpFile.appendInstructionsToFile(Arrays.asList("#This is a test program", ".org 0x100000", "addu $t1, $t2, $t3"));
        Assertions.assertEquals(3, plpFile.getInstructionsOfFile().size());
        plpFile.clearInstructions();
        Assertions.assertEquals(0, plpFile.getInstructionsOfFile().size());
    }

    @Test
    void testGetInstructionAtLineSuccess() throws Exception {
        Assertions.assertEquals(0, plpFile.getInstructionsOfFile().size());
        plpFile.appendInstructionsToFile(Arrays.asList("#This is a test program", ".org 0x100000", "addu $t1, $t2, $t3"));
        Assertions.assertEquals(3, plpFile.getInstructionsOfFile().size());
        Assertions.assertEquals(".org 0x100000", plpFile.getInstructionAtLine(2));
        Assertions.assertEquals("#This is a test program", plpFile.getInstructionAtLine(1));
        Assertions.assertEquals("addu $t1, $t2, $t3", plpFile.getInstructionAtLine(3));
    }

    @Test
    void testGetInstructionAtNegativeLine() throws Exception {
        Assertions.assertEquals(0, plpFile.getInstructionsOfFile().size());
        plpFile.appendInstructionsToFile(Arrays.asList("#This is a test program", ".org 0x100000", "addu $t1, $t2, $t3"));
        Assertions.assertEquals(3, plpFile.getInstructionsOfFile().size());
        Assertions.assertThrows(IOException.class, () ->{
            plpFile.getInstructionAtLine(-1);
        }, "Invalid line number; Minimum line number can be 1 and Max line number can be: 3");
    }

    @Test
    void testGetInstructionAtInvalidLine() throws Exception {
        Assertions.assertEquals(0, plpFile.getInstructionsOfFile().size());
        plpFile.appendInstructionsToFile(Arrays.asList("#This is a test program", ".org 0x100000", "addu $t1, $t2, $t3"));
        Assertions.assertEquals(3, plpFile.getInstructionsOfFile().size());
        Assertions.assertThrows(IOException.class, () ->{
            plpFile.getInstructionAtLine(10);
        }, "Invalid line number; Minimum line number can be 1 and Max line number can be: 3");
    }

    @Test
    void testGetInstructionsOfFile() throws Exception {
        Assertions.assertEquals(0, plpFile.getInstructionsOfFile().size());
        plpFile.appendInstructionsToFile(Arrays.asList("#This is a test program", ".org 0x100000", "addu $t1, $t2, $t3"));
        Assertions.assertEquals(3, plpFile.getInstructionsOfFile().size());
        Assertions.assertEquals("#This is a test program", plpFile.getInstructionsOfFile().get(0));
        Assertions.assertEquals(".org 0x100000", plpFile.getInstructionsOfFile().get(1));
        Assertions.assertEquals("addu $t1, $t2, $t3", plpFile.getInstructionsOfFile().get(2));
    }

}
