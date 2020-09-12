package org.plp.implementations.plpisa;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

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



}
