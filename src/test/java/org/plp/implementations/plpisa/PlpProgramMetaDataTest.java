package org.plp.implementations.plpisa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class PlpProgramMetaDataTest {

    PlpProgramMetaData metaData;

    @BeforeEach
    public void setup() {
        metaData = new PlpProgramMetaData("testProgram");
    }

    @Test
    public void testPlpProgramMetaDataCreation() {
        Assertions.assertEquals("testProgram", metaData.getProgramName());
        Assertions.assertEquals("7.0", metaData.getVersion());
    }

    @Test
    public void testPlpProgramMetaDataFiles() {
        PlpProgramMetaData metaData = new PlpProgramMetaData("testProgram");
        Assertions.assertEquals(0, metaData.getProgramFiles().size());
        metaData.addFileToProgram("testFile.asm");
        Assertions.assertEquals(1, metaData.getProgramFiles().size());
        Assertions.assertEquals("testFile.asm", metaData.getProgramFiles().get(0));
        metaData.addFileToProgram("testFile2.asm");
        Assertions.assertEquals(2, metaData.getProgramFiles().size());
        Assertions.assertEquals("testFile2.asm", metaData.getProgramFiles().get(1));

    }

    @Test
    public void testPlpProgramMetaDataDeleteFiles() {
        Assertions.assertEquals(0, metaData.getProgramFiles().size());
        metaData.addFileToProgram("testFile.asm");
        Assertions.assertEquals(1, metaData.getProgramFiles().size());
        metaData.deleteFileInProgram("testFile.asm");
        Assertions.assertEquals(0, metaData.getProgramFiles().size());
    }

    @Test
    public void testPlpProgramMetaDataAddFileAtIndex() {
        Assertions.assertEquals(0, metaData.getProgramFiles().size());
        metaData.addFileToProgramAtIndex("testFile.asm", 0);
        Assertions.assertEquals(1, metaData.getProgramFiles().size());
        metaData.addFileToProgramAtIndex("testFile2.asm", 0);
        Assertions.assertEquals(2, metaData.getProgramFiles().size());
        metaData.addFileToProgramAtIndex("testFile3.asm", 1);
        Assertions.assertEquals(3, metaData.getProgramFiles().size());
        metaData.addFileToProgramAtIndex("testFile4.asm", 10);
        Assertions.assertEquals(4, metaData.getProgramFiles().size());
        Assertions.assertEquals("testFile2.asm", metaData.getProgramFiles().get(0));
        Assertions.assertEquals("testFile3.asm", metaData.getProgramFiles().get(1));
        Assertions.assertEquals("testFile.asm", metaData.getProgramFiles().get(2));
        Assertions.assertEquals("testFile4.asm", metaData.getProgramFiles().get(3));

    }

    @Test
    public void testInitiatePlpProgramDataWithListOfFiles() {
        List<String> files = Arrays.asList("main.asm", "abc.asm");
        PlpProgramMetaData tempMetaData = new PlpProgramMetaData("testFile", files);

        Assertions.assertEquals("testFile", tempMetaData.getProgramName());
        List<String> actualFiles = tempMetaData.getProgramFiles();
        Assertions.assertEquals(2, actualFiles.size());

        Assertions.assertEquals("main.asm", actualFiles.get(0));
        Assertions.assertEquals("abc.asm", actualFiles.get(1));
    }

}
