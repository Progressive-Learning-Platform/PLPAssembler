package org.plp.implementations.plpisa;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.plp.isa.exceptions.AsmAssemblerException;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class PlpFileTest {

    FileSystem testOSXFileSystem;
    FileSystem testUnixFileSystem;
    FileSystem testWinFileSystem;

    private void createTestInstruction(List<String> programLines) {
        programLines.add(".org 0x100000");
        programLines.add("");
        programLines.add("add $r1,$r2  # this is a test");
    }

    @BeforeEach
    void initializeFileSystem() {
        testOSXFileSystem = Jimfs.newFileSystem(Configuration.osX());
        testUnixFileSystem = Jimfs.newFileSystem(Configuration.unix());
        testWinFileSystem = Jimfs.newFileSystem(Configuration.windows());
    }

    @Test
    void createPlpFileSuccessFulTest() {
        Path testOSXFile = testOSXFileSystem.getPath("test.asm");
        Assertions.assertDoesNotThrow(() -> new PlpFile(testOSXFile));

        Path testUnixFile = testUnixFileSystem.getPath("test.asm");
        Assertions.assertDoesNotThrow(() -> new PlpFile(testUnixFile));

        Path testWinFile = testWinFileSystem.getPath("test.asm");
        Assertions.assertDoesNotThrow(() -> new PlpFile(testWinFile));
    }

    @Test
    void createPlpFileSuccessFulForExistingFileTest() throws IOException{
        List<String> programLines = new ArrayList<>();
        createTestInstruction(programLines);

        Path testOSXFile = testOSXFileSystem.getPath("test.asm");
        Files.createFile(testOSXFile);
        Files.write(testOSXFile, programLines);
        PlpFile plpOsxFile = new PlpFile(testOSXFile);
        Assertions.assertEquals(plpOsxFile.getInstructions(), programLines);


        Path testUnixFile = testUnixFileSystem.getPath("test.asm");
        Files.createFile(testUnixFile);
        Files.write(testUnixFile, programLines);
        PlpFile plpUnixFile = new PlpFile(testUnixFile);
        Assertions.assertEquals(plpUnixFile.getInstructions(), programLines);

        Path testWinFile = testWinFileSystem.getPath("test.asm");
        Files.createFile(testWinFile);
        Files.write(testWinFile, programLines);
        PlpFile plpWinFile = new PlpFile(testWinFile);
        Assertions.assertEquals(plpWinFile.getInstructions(), programLines);
    }

    @Test
    void createPlpFileFailureTest() throws IOException {
        Path programOSXDirectory = testOSXFileSystem.getPath("test.asm");
        Files.createDirectory(programOSXDirectory);
        Assertions.assertThrows(IOException.class, () -> new PlpFile(programOSXDirectory));

        Path programUnixDirectory = testUnixFileSystem.getPath("test.asm");
        Files.createDirectory(programUnixDirectory);
        Assertions.assertThrows(IOException.class, () -> new PlpFile(programUnixDirectory));

        Path programWinDirectory = testWinFileSystem.getPath("test.asm");
        Files.createDirectory(programWinDirectory);
        Assertions.assertThrows(IOException.class, () -> new PlpFile(programWinDirectory));
    }

    @Test
    void getInstructionAtLineNumberFromPlpFileTest() throws IOException{
        List<String> programLines = new ArrayList<>();
        createTestInstruction(programLines);

        Path testOSXFile = testOSXFileSystem.getPath("test.asm");
        Files.createFile(testOSXFile);
        Files.write(testOSXFile, programLines);
        PlpFile plpOsxFile = new PlpFile(testOSXFile);
        Assertions.assertEquals(".org 0x100000", plpOsxFile.getInstructionAtLine(1));
        Assertions.assertEquals("", plpOsxFile.getInstructionAtLine(2));
        Assertions.assertEquals("add $r1,$r2  # this is a test", plpOsxFile.getInstructionAtLine(3));


        Path testUnixFile = testUnixFileSystem.getPath("test.asm");
        Files.createFile(testUnixFile);
        Files.write(testUnixFile, programLines);
        PlpFile plpUnixFile = new PlpFile(testUnixFile);
        Assertions.assertEquals(".org 0x100000", plpUnixFile.getInstructionAtLine(1));
        Assertions.assertEquals("", plpUnixFile.getInstructionAtLine(2));
        Assertions.assertEquals("add $r1,$r2  # this is a test", plpUnixFile.getInstructionAtLine(3));

        Path testWinFile = testWinFileSystem.getPath("test.asm");
        Files.createFile(testWinFile);
        Files.write(testWinFile, programLines);
        PlpFile plpWinFile = new PlpFile(testWinFile);
        Assertions.assertEquals(".org 0x100000", plpWinFile.getInstructionAtLine(1));
        Assertions.assertEquals("", plpWinFile.getInstructionAtLine(2));
        Assertions.assertEquals("add $r1,$r2  # this is a test", plpWinFile.getInstructionAtLine(3));
    }

    @Test
    void getInstructionAtLineNumberFromPlpFileFailureTest() throws IOException{
        List<String> programLines = new ArrayList<>();
        createTestInstruction(programLines);

        Path testOSXFile = testOSXFileSystem.getPath("test.asm");
        Files.createFile(testOSXFile);
        Files.write(testOSXFile, programLines);
        PlpFile plpOsxFile = new PlpFile(testOSXFile);
        Assertions.assertThrows(AsmAssemblerException.class, () -> plpOsxFile.getInstructionAtLine(0));
        Assertions.assertThrows(AsmAssemblerException.class, () -> plpOsxFile.getInstructionAtLine(4));
        Assertions.assertDoesNotThrow(() -> plpOsxFile.getInstructionAtLine(1));


        Path testUnixFile = testUnixFileSystem.getPath("test.asm");
        Files.createFile(testUnixFile);
        Files.write(testUnixFile, programLines);
        PlpFile plpUnixFile = new PlpFile(testUnixFile);
        Assertions.assertThrows(AsmAssemblerException.class, () -> plpUnixFile.getInstructionAtLine(0));
        Assertions.assertThrows(AsmAssemblerException.class, () -> plpUnixFile.getInstructionAtLine(4));
        Assertions.assertDoesNotThrow(() -> plpUnixFile.getInstructionAtLine(1));

        Path testWinFile = testWinFileSystem.getPath("test.asm");
        Files.createFile(testWinFile);
        Files.write(testWinFile, programLines);
        PlpFile plpWinFile = new PlpFile(testWinFile);
        Assertions.assertThrows(AsmAssemblerException.class, () -> plpWinFile.getInstructionAtLine(0));
        Assertions.assertThrows(AsmAssemblerException.class, () -> plpWinFile.getInstructionAtLine(4));
        Assertions.assertDoesNotThrow(() -> plpWinFile.getInstructionAtLine(1));
    }

    @Test
    void getFilePathTest() throws IOException {
        Path testOSXFile = testOSXFileSystem.getPath("test.asm");
        PlpFile plpOSXFile = new PlpFile(testOSXFile);
        Assertions.assertEquals(testOSXFile, plpOSXFile.getFilePath());

        Path testUnixFile = testOSXFileSystem.getPath("test.asm");
        PlpFile plpUnixFile = new PlpFile(testOSXFile);
        Assertions.assertEquals(testUnixFile, plpUnixFile.getFilePath());

        Path testWinFile = testOSXFileSystem.getPath("test.asm");
        PlpFile plpWinFile = new PlpFile(testWinFile);
        Assertions.assertEquals(testWinFile, plpWinFile.getFilePath());

    }

    @Test
    void getFileNameTest() throws IOException {
        Path testOSXFile = testOSXFileSystem.getPath("test.asm");
        PlpFile plpOSXFile = new PlpFile(testOSXFile);
        Assertions.assertEquals(testOSXFile.getFileName().toString(), plpOSXFile.getFileName());

        Path testUnixFile = testOSXFileSystem.getPath("test.asm");
        PlpFile plpUnixFile = new PlpFile(testOSXFile);
        Assertions.assertEquals(testUnixFile.getFileName().toString(), plpUnixFile.getFileName());

        Path testWinFile = testOSXFileSystem.getPath("test.asm");
        PlpFile plpWinFile = new PlpFile(testWinFile);
        Assertions.assertEquals(testWinFile.getFileName().toString(), plpWinFile.getFileName());

    }

    @Test
    void addInstructionToFileTest() throws IOException {
        List<String> programLines = new ArrayList<>();
        programLines.add(".org 0x100000");
        programLines.add("");
        programLines.add("add $r1,$r2  # this is a test");

        Path testOSXFile = testOSXFileSystem.getPath("test.asm");
        Files.createFile(testOSXFile);
        Files.write(testOSXFile, programLines);
        PlpFile plpOsxFile = new PlpFile(testOSXFile);
        Assertions.assertTrue(plpOsxFile.addInstructionToFile("sub $r2, $r3"));
        Assertions.assertEquals("sub $r2, $r3", plpOsxFile.getInstructionAtLine(4));


        Path testUnixFile = testUnixFileSystem.getPath("test.asm");
        Files.createFile(testUnixFile);
        Files.write(testUnixFile, programLines);
        PlpFile plpUnixFile = new PlpFile(testUnixFile);
        Assertions.assertTrue(plpUnixFile.addInstructionToFile("sub $r2, $r3"));
        Assertions.assertEquals("sub $r2, $r3", plpUnixFile.getInstructionAtLine(4));

        Path testWinFile = testWinFileSystem.getPath("test.asm");
        Files.createFile(testWinFile);
        Files.write(testWinFile, programLines);
        PlpFile plpWinFile = new PlpFile(testWinFile);
        Assertions.assertTrue(plpWinFile.addInstructionToFile("sub $r2, $r3"));
        Assertions.assertEquals("sub $r2, $r3", plpWinFile.getInstructionAtLine(4));
    }

    @Test
    void writeFileTest() throws IOException {
        List<String> programLines = new ArrayList<>();
        programLines.add(".org 0x100000");
        programLines.add("");
        programLines.add("add $r1,$r2  # this is a test");

        Path testOSXFile = testOSXFileSystem.getPath("test.asm");
        Files.createFile(testOSXFile);
        Files.write(testOSXFile, programLines);
        PlpFile plpOsxFile = new PlpFile(testOSXFile);
        Assertions.assertTrue(plpOsxFile.addInstructionToFile("sub $r2, $r3"));
        plpOsxFile.writeToFile();
        Assertions.assertEquals("sub $r2, $r3", Files.readAllLines(testOSXFile).get(3));

        Path testUnixFile = testUnixFileSystem.getPath("test.asm");
        Files.createFile(testUnixFile);
        Files.write(testUnixFile, programLines);
        PlpFile plpUnixFile = new PlpFile(testUnixFile);
        Assertions.assertTrue(plpUnixFile.addInstructionToFile("sub $r2, $r3"));
        plpUnixFile.writeToFile();
        Assertions.assertEquals("sub $r2, $r3", Files.readAllLines(testUnixFile).get(3));

        Path testWinFile = testWinFileSystem.getPath("test.asm");
        Files.createFile(testWinFile);
        Files.write(testWinFile, programLines);
        PlpFile plpWinFile = new PlpFile(testWinFile);
        Assertions.assertTrue(plpWinFile.addInstructionToFile("sub $r2, $r3"));
        plpWinFile.writeToFile();
        Assertions.assertEquals("sub $r2, $r3", Files.readAllLines(testWinFile).get(3));
    }
}
