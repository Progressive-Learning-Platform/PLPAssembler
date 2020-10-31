package org.plp.implementations.plpisa;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.plp.isa.AsmFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Collections;

public class PlpProgramTest {

    FileSystem testOSXFileSystem;
    FileSystem testUnixFileSystem;
    FileSystem testWinFileSystem;

    @BeforeEach
    void initializeFileSystem() {
        testOSXFileSystem = Jimfs.newFileSystem(Configuration.osX());
        testUnixFileSystem = Jimfs.newFileSystem(Configuration.unix());
        testWinFileSystem = Jimfs.newFileSystem(Configuration.windows());
    }

    @Test
    void createPlpProgramSuccessFulTest() {
        Path programOSXDirectory = testOSXFileSystem.getPath("rootFolder");
        Assertions.assertFalse(Files.exists(programOSXDirectory));
        Assertions.assertDoesNotThrow(() -> {
            PlpProgram program = new PlpProgram(programOSXDirectory);
        });
        Assertions.assertTrue(Files.exists(programOSXDirectory));

        Path programUnixDirectory = testUnixFileSystem.getPath("rootFolder");
        Assertions.assertFalse(Files.exists(programUnixDirectory));
        Assertions.assertDoesNotThrow(() -> new PlpProgram(programUnixDirectory));
        Assertions.assertTrue(Files.exists(programUnixDirectory));

        Path programWinDirectory = testWinFileSystem.getPath("rootFolder");
        Assertions.assertFalse(Files.exists(programWinDirectory));
        Assertions.assertDoesNotThrow(() -> new PlpProgram(programWinDirectory));
        Assertions.assertTrue(Files.exists(programWinDirectory));

    }

    @Test
    void createPlpProgramFailureFileExistsTest() throws IOException{
        Path programOSXDirectory = testOSXFileSystem.getPath("rootFolder");
        Files.createFile(programOSXDirectory);
        Assertions.assertTrue(Files.exists(programOSXDirectory));
        Assertions.assertThrows(IOException.class, () -> {
            PlpProgram program = new PlpProgram(programOSXDirectory);
        });
        Assertions.assertFalse(Files.isDirectory(programOSXDirectory));

        Path programUnixDirectory = testUnixFileSystem.getPath("rootFolder");
        Files.createFile(programUnixDirectory);
        Assertions.assertTrue(Files.exists(programUnixDirectory));
        Assertions.assertThrows(IOException.class, () -> new PlpProgram(programUnixDirectory));
        Assertions.assertFalse(Files.isDirectory(programUnixDirectory));

        Path programWinDirectory = testWinFileSystem.getPath("rootFolder");
        Files.createFile(programWinDirectory);
        Assertions.assertTrue(Files.exists(programWinDirectory));
        Assertions.assertThrows(IOException.class, () -> new PlpProgram(programWinDirectory));
        Assertions.assertFalse(Files.isDirectory(programWinDirectory));

    }

    @Test
    void getPlpProgramDirectoryTest() {
        Path programOSXDirectory = testOSXFileSystem.getPath("rootFolder");
        Assertions.assertDoesNotThrow(() -> {
            PlpProgram program = new PlpProgram(programOSXDirectory);
            Assertions.assertEquals(program.getProgramDirectory(), programOSXDirectory.toString());
        });

        Path programUnixDirectory = testUnixFileSystem.getPath("rootFolder");
        Assertions.assertDoesNotThrow(() -> {
            PlpProgram program = new PlpProgram(programUnixDirectory);
            Assertions.assertEquals(program.getProgramDirectory(), programUnixDirectory.toString());
        });

        Path programWinDirectory = testWinFileSystem.getPath("rootFolder");
        Assertions.assertDoesNotThrow(() -> {
            PlpProgram program = new PlpProgram(programWinDirectory);
            Assertions.assertEquals(program.getProgramDirectory(), programWinDirectory.toString());
        });

    }

    @Test
    void loadExistingPlpFilesInDirectoryTest() throws Exception {
        Path programOSXDirectory = testOSXFileSystem.getPath("rootFolder");
        Files.createDirectory(programOSXDirectory);
        Path testOSXFile1 = testOSXFileSystem.getPath(Paths.get(programOSXDirectory.toString(),"test1.asm").toString());
        Files.createFile(testOSXFile1);
        Files.write(testOSXFile1, Collections.singletonList("#This is Program 1"));
        Path testOSXFile2 = testOSXFileSystem.getPath(Paths.get(programOSXDirectory.toString(),"test2.asm").toString());
        Files.createFile(testOSXFile2);
        Files.write(testOSXFile2, Collections.singletonList("#This is Program 2"));
        Path testOSXFile3 = testOSXFileSystem.getPath(Paths.get(programOSXDirectory.toString(),"test3.asm").toString());
        Files.createFile(testOSXFile3);
        Files.write(testOSXFile1, Collections.singletonList("#This is Program 3"));

        PlpProgram plpOsXProgram = new PlpProgram(programOSXDirectory);
        Assertions.assertNotNull(plpOsXProgram.getAsmFile("test1.asm"));
        Assertions.assertNotNull(plpOsXProgram.getAsmFile("test2.asm"));
        Assertions.assertNotNull(plpOsXProgram.getAsmFile("test3.asm"));

        Path programUnixDirectory = testUnixFileSystem.getPath("rootFolder");
        Files.createDirectory(programUnixDirectory);
        Path testUnixFile1 = testUnixFileSystem.getPath(Paths.get(programUnixDirectory.toString(),"test1.asm").toString());
        Files.createFile(testUnixFile1);
        Files.write(testOSXFile1, Collections.singletonList("#This is Program 1"));
        Path testUnixFile2 = testUnixFileSystem.getPath(Paths.get(programUnixDirectory.toString(),"test2.asm").toString());
        Files.createFile(testUnixFile2);
        Files.write(testOSXFile2, Collections.singletonList("#This is Program 2"));
        Path testUnixFile3 = testUnixFileSystem.getPath(Paths.get(programUnixDirectory.toString(),"test3.asm").toString());
        Files.createFile(testUnixFile3);
        Files.write(testOSXFile1, Collections.singletonList("#This is Program 3"));

        PlpProgram plpUnixProgram = new PlpProgram(programUnixDirectory);
        Assertions.assertNotNull(plpUnixProgram.getAsmFile("test1.asm"));
        Assertions.assertNotNull(plpUnixProgram.getAsmFile("test2.asm"));
        Assertions.assertNotNull(plpUnixProgram.getAsmFile("test3.asm"));

        Path programWinDirectory = testWinFileSystem.getPath("rootFolder");
        Files.createDirectory(programWinDirectory);
        Path testWinFile1 = testWinFileSystem.getPath(Paths.get(programWinDirectory.toString(),"test1.asm").toString());
        Files.createFile(testWinFile1);
        Files.write(testOSXFile1, Collections.singletonList("#This is Program 1"));
        Path testWinFile2 = testWinFileSystem.getPath(Paths.get(programWinDirectory.toString(),"test2.asm").toString());
        Files.createFile(testWinFile2);
        Files.write(testOSXFile2, Collections.singletonList("#This is Program 2"));
        Path testWinFile3 = testWinFileSystem.getPath(Paths.get(programWinDirectory.toString(),"test3.asm").toString());
        Files.createFile(testWinFile3);
        Files.write(testOSXFile1, Collections.singletonList("#This is Program 3"));

        PlpProgram plpWinProgram = new PlpProgram(programWinDirectory);
        Assertions.assertNotNull(plpWinProgram.getAsmFile("test1.asm"));
        Assertions.assertNotNull(plpWinProgram.getAsmFile("test2.asm"));
        Assertions.assertNotNull(plpWinProgram.getAsmFile("test3.asm"));
    }

    @Test
    void createNewAsmFileTest() throws Exception {
        Path programOSXDirectory = testOSXFileSystem.getPath("rootFolder");
        PlpProgram plpOsXProgram = new PlpProgram(programOSXDirectory);
        AsmFile plpOsXFile = plpOsXProgram.createAsmFileInProgram("test1.asm");
        Assertions.assertNotNull(plpOsXFile);
        Assertions.assertEquals("test1.asm", plpOsXFile.getFileName());
        Assertions.assertEquals(plpOsXFile, plpOsXProgram.getAsmFile("test1.asm"));

        Path programUnixDirectory = testUnixFileSystem.getPath("rootFolder");
        PlpProgram plpUnixProgram = new PlpProgram(programUnixDirectory);
        AsmFile plpUnixFile = plpUnixProgram.createAsmFileInProgram("test1.asm");
        Assertions.assertNotNull(plpUnixFile);
        Assertions.assertEquals("test1.asm", plpUnixFile.getFileName());
        Assertions.assertEquals(plpUnixFile, plpUnixProgram.getAsmFile("test1.asm"));

        Path programWinDirectory = testWinFileSystem.getPath("rootFolder");
        PlpProgram plpWinProgram = new PlpProgram(programWinDirectory);
        AsmFile plpWinFile = plpWinProgram.createAsmFileInProgram("test1.asm");
        Assertions.assertNotNull(plpWinFile);
        Assertions.assertEquals("test1.asm", plpWinFile.getFileName());
        Assertions.assertEquals(plpWinFile, plpWinProgram.getAsmFile("test1.asm"));

    }

    @Test
    void createNewAsmFileFailureTest() throws Exception {
        Path programOSXDirectory = testOSXFileSystem.getPath("rootFolder");
        Files.createDirectory(programOSXDirectory);
        Path testOSXFile1 = testOSXFileSystem.getPath(Paths.get(programOSXDirectory.toString(),"test1.asm").toString());
        Files.createFile(testOSXFile1);
        Files.write(testOSXFile1, Collections.singletonList("#This is Program 1"));
        PlpProgram plpOsXProgram = new PlpProgram(programOSXDirectory);
        Assertions.assertThrows(IOException.class, () -> plpOsXProgram.createAsmFileInProgram("test1.asm"));

        Path programUnixDirectory = testUnixFileSystem.getPath("rootFolder");
        Files.createDirectory(programUnixDirectory);
        Path testUnixFile1 = testUnixFileSystem.getPath(Paths.get(programUnixDirectory.toString(),"test1.asm").toString());
        Files.createFile(testUnixFile1);
        Files.write(testOSXFile1, Collections.singletonList("#This is Program 1"));
        PlpProgram plpUnixProgram = new PlpProgram(programUnixDirectory);
        Assertions.assertThrows(IOException.class, () -> plpUnixProgram.createAsmFileInProgram("test1.asm"));

        Path programWinDirectory = testWinFileSystem.getPath("rootFolder");
        Files.createDirectory(programWinDirectory);
        Path testWinFile1 = testWinFileSystem.getPath(Paths.get(programWinDirectory.toString(),"test1.asm").toString());
        Files.createFile(testWinFile1);
        Files.write(testOSXFile1, Collections.singletonList("#This is Program 1"));
        PlpProgram plpWinProgram = new PlpProgram(programWinDirectory);
        Assertions.assertThrows(IOException.class, () -> plpWinProgram.createAsmFileInProgram("test1.asm"));

    }

    @Test
    void addFilesToProgramFailureTest(@TempDir Path rootDirectory) throws Exception {
        Path programDirectory = rootDirectory.resolve("testProgram");
        Files.createDirectory(programDirectory);
        Path asmFile1 = programDirectory.resolve("test1.asm");
        Files.write(asmFile1, Arrays.asList("# This is program 1"));
        Path asmFile2 = programDirectory.resolve("test2.asm");
        Files.write(asmFile2, Arrays.asList("# This is program 2"));

        Path asmFile3 = rootDirectory.resolve("test2.asm");
        Files.write(asmFile3, Arrays.asList("# This is another program"));
        PlpFile plpFile = new PlpFile(asmFile3);

        PlpProgram plpProgram = new PlpProgram(programDirectory);
        Assertions.assertThrows(IOException.class,
                () -> plpProgram.copyAsmFileToProgram(plpFile),
                "File with same name test2.asm exists in the program testProgram");

        AsmFile existingFile = plpProgram.getAsmFile("test2.asm");
        Assertions.assertNotNull(existingFile);
        Assertions.assertEquals(existingFile.getInstructionAtLine(1), "# This is program 2");
    }

    @Test
    void addFilesToProgramSuccessTest(@TempDir Path rootDirectory) throws Exception {
        Path programDirectory = rootDirectory.resolve("testProgram");
        Files.createDirectory(programDirectory);
        Path asmFile1 = programDirectory.resolve("test1.asm");
        Files.write(asmFile1, Arrays.asList("# This is program 1"));

        Path asmFile2 = rootDirectory.resolve("test2.asm");
        Files.write(asmFile2, Arrays.asList("# This is program 2"));
        PlpFile plpFile = new PlpFile(asmFile2);

        PlpProgram plpProgram = new PlpProgram(programDirectory);
        Assertions.assertDoesNotThrow(() -> plpProgram.copyAsmFileToProgram(plpFile));

        Assertions.assertNotNull(plpProgram.getAsmFile("test2.asm"));

    }

}
