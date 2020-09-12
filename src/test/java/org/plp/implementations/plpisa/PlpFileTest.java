package org.plp.implementations.plpisa;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

@ExtendWith(MockitoExtension.class)
public class PlpFileTest {

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
    void createPlpFileSuccessFulTest() {
        Assertions.assertDoesNotThrow(() -> new PlpFile(Path.of("test.asm")));
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
}
