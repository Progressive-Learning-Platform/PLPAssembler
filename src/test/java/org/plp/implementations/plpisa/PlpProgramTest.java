package org.plp.implementations.plpisa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.plp.isa.AsmFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PlpProgramTest {

    @Test
    void createPlpProgramSuccessFulTest(@TempDir Path rootDirectory) throws IOException {
        Path programDirectory = rootDirectory.resolve("TestProgramDirectory");
        Files.createDirectory(programDirectory);

        PlpProgram program = new PlpProgram("testProgram", programDirectory);

        Assertions.assertEquals("testProgram.plp", program.getProgramName());
        Assertions.assertEquals(
                Paths.get(programDirectory.toString(), "testProgram.plp"),
                program.getProgramLocation());

        Assertions.assertEquals(1, program.getAsmFilesOfProgram().size());
        Assertions.assertEquals("main.asm", program.getAsmFilesOfProgram().get(0).getFileName());
        Assertions.assertEquals(program, program.getAsmFilesOfProgram().get(0).getAsmProgramOfFile());

    }

    @Test
    void createPlpProgramSuccessWithExtensionTest(@TempDir Path rootDirectory) throws IOException {
        Path programDirectory = rootDirectory.resolve("TestProgramDirectory");
        Files.createDirectory(programDirectory);

        PlpProgram program = new PlpProgram("testProgram.plp", programDirectory);

        Assertions.assertEquals("testProgram.plp", program.getProgramName());
        Assertions.assertEquals(
                Paths.get(programDirectory.toString(), "testProgram.plp"),
                program.getProgramLocation());
    }

    @Test
    void loadExistingProgramTest(@TempDir Path rootDirectory) throws IOException {
        Path programDirectory = rootDirectory.resolve("TestProgramDirectory");
        Files.createDirectory(programDirectory);

        PlpProgram program = new PlpProgram("testProgram.plp", programDirectory);
        AsmFile asmFile = program.getAsmFile("main.asm");
        asmFile.appendInstructionsToFile(Arrays.asList("#This is starting", ".org 0x1000000", "addu $t1, $t2, $t3"));

        PlpProgram readProgram = new PlpProgram("testProgram.plp", programDirectory);
        Assertions.assertEquals(program.getProgramName(), readProgram.getProgramName());
        Assertions.assertEquals(program.getProgramLocation().toString(), readProgram.getProgramLocation().toString());
        Assertions.assertEquals(1, readProgram.getAsmFilesOfProgram().size());
        AsmFile readAsmFile = readProgram.getAsmFile("main.asm");
        Assertions.assertEquals(readProgram, readAsmFile.getAsmProgramOfFile());
        Assertions.assertEquals(asmFile.getFileName(), readAsmFile.getFileName());
        List<String> instructionsRead = readAsmFile.getInstructionsOfFile();
        Assertions.assertEquals(3, instructionsRead.size());
        Assertions.assertEquals("#This is starting", instructionsRead.get(0));
        Assertions.assertEquals(".org 0x1000000", instructionsRead.get(1));
        Assertions.assertEquals("addu $t1, $t2, $t3", instructionsRead.get(2));
    }

    @Test
    public void testGetAsmFilesOfProgram(@TempDir Path rootDirectory) throws IOException{
        Path programDirectory = rootDirectory.resolve("TestProgramDirectory");
        Files.createDirectory(programDirectory);

        PlpProgram program = new PlpProgram("testProgram.plp", programDirectory);
        AsmFile asmFile = program.getAsmFile("main.asm");
        asmFile.appendInstructionsToFile(Arrays.asList("#This is starting", ".org 0x1000000", "addu $t1, $t2, $t3"));

        AsmFile readAsmFile = program.getAsmFile("main.asm");
        Assertions.assertEquals(1, program.getAsmFilesOfProgram().size());
        Assertions.assertEquals("main.asm", program.getAsmFilesOfProgram().get(0).getFileName());
    }

    @Test
    public void testGetAsmFile(@TempDir Path rootDirectory) throws IOException {
        Path programDirectory = rootDirectory.resolve("TestProgramDirectory");
        Files.createDirectory(programDirectory);

        PlpProgram program = new PlpProgram("testProgram.plp", programDirectory);
        AsmFile asmFile = program.getAsmFile("main.asm");
        Assertions.assertEquals("main.asm", asmFile.getFileName());
    }

    @Test
    public void testCreateAsmFileInProgram(@TempDir Path rootDirectory) throws IOException {
        Path programDirectory = rootDirectory.resolve("TestProgramDirectory");
        Files.createDirectory(programDirectory);

        PlpProgram program = new PlpProgram("testProgram.plp", programDirectory);

        program.createAsmFileInProgram("newFile.asm");

        List<AsmFile> asmFiles = program.getAsmFilesOfProgram();

        Assertions.assertEquals(2, asmFiles.size());

        List<String> asmFileNames = asmFiles.stream().map(AsmFile::getFileName).collect(Collectors.toList());

        Assertions.assertTrue(asmFileNames.contains("main.asm"));
        Assertions.assertTrue(asmFileNames.contains("newFile.asm"));

    }

    @Test
    public void testCreateAsmFileFailure(@TempDir Path rootDirectory) throws IOException {
        Path programDirectory = rootDirectory.resolve("TestProgramDirectory");
        Files.createDirectory(programDirectory);

        PlpProgram program = new PlpProgram("testProgram.plp", programDirectory);

        Assertions.assertThrows(IOException.class, () -> {
            program.createAsmFileInProgram("main.asm");
        }, "testProgram.plp program already has a file with same name as main.asm");
    }

    @Test
    public void testDeleteAsmFileInProgram(@TempDir Path rootDirectory) throws IOException {
        Path programDirectory = rootDirectory.resolve("TestProgramDirectory");
        Files.createDirectory(programDirectory);

        PlpProgram program = new PlpProgram("testProgram.plp", programDirectory);

        program.createAsmFileInProgram("newFile.asm");

        Assertions.assertEquals(2, program.getAsmFilesOfProgram().size());

        program.deleteAsmFileInProgram("newFile.asm");

        Assertions.assertEquals(1, program.getAsmFilesOfProgram().size());

        Assertions.assertEquals("main.asm", program.getAsmFilesOfProgram().get(0).getFileName());

    }

    @Test
    public void testDeleteAsmFileFailure(@TempDir Path rootDirectory) throws IOException {
        Path programDirectory = rootDirectory.resolve("TestProgramDirectory");
        Files.createDirectory(programDirectory);

        PlpProgram program = new PlpProgram("testProgram.plp", programDirectory);

        Assertions.assertThrows(IOException.class, () -> {
            program.deleteAsmFileInProgram("newFile.asm");
        }, "testProgram.plp program do not have file newFile.asm");
    }

    @Test
    public void testRenameAsmFileSuccess(@TempDir Path rootDirectory) throws IOException {
        Path programDirectory = rootDirectory.resolve("TestProgramDirectory");
        Files.createDirectory(programDirectory);

        PlpProgram program = new PlpProgram("testProgram.plp", programDirectory);

        Assertions.assertEquals(1, program.getAsmFilesOfProgram().size());

        Assertions.assertEquals("main.asm", program.getAsmFile("main.asm").getFileName());

        program.renameAsmFileInProgram("main.asm", "newName.asm");

        Assertions.assertEquals(1, program.getAsmFilesOfProgram().size());

        Assertions.assertEquals("newName.asm", program.getAsmFile("newName.asm").getFileName());

    }

    @Test
    public void testRenameAsmFileFailureFileDoNotExists(@TempDir Path rootDirectory) throws IOException {
        Path programDirectory = rootDirectory.resolve("TestProgramDirectory");
        Files.createDirectory(programDirectory);

        PlpProgram program = new PlpProgram("testProgram.plp", programDirectory);

        Assertions.assertEquals(1, program.getAsmFilesOfProgram().size());

        Assertions.assertEquals("main.asm", program.getAsmFile("main.asm").getFileName());

        Assertions.assertThrows(IOException.class,
                () -> {program.renameAsmFileInProgram("newName.asm", "nextName.asm");},
                "testProgram.plp program does not have file newName.asm");
    }

    @Test
    public void testRenameAsmFileFailureFileAlreadyExists(@TempDir Path rootDirectory) throws IOException {
        Path programDirectory = rootDirectory.resolve("TestProgramDirectory");
        Files.createDirectory(programDirectory);

        PlpProgram program = new PlpProgram("testProgram.plp", programDirectory);

        program.createAsmFileInProgram("newName.asm");

        Assertions.assertThrows(IOException.class,
                () -> {program.renameAsmFileInProgram("main.asm", "newName.asm");},
                "testProgram.plp program already has a file with same name - newName.asm");

    }

    @Test
    public void testProgramName(@TempDir Path rootDirectory) throws IOException {
        Path programDirectory = rootDirectory.resolve("TestProgramDirectory");
        Files.createDirectory(programDirectory);

        PlpProgram program = new PlpProgram("testProgram.plp", programDirectory);

        Assertions.assertEquals("testProgram.plp", program.getProgramName());
    }

    @Test
    public void testProgramLocation(@TempDir Path rootDirectory) throws IOException {
        Path programDirectory = rootDirectory.resolve("TestProgramDirectory");
        Files.createDirectory(programDirectory);

        PlpProgram program = new PlpProgram("testProgram.plp", programDirectory);

        Assertions.assertEquals(programDirectory.toString() + "/testProgram.plp", program.getProgramLocation().toString());
    }


}
