package org.plp.implementations.plpisa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlpFileTest {

    @Mock PlpProgram mockProgram;
    @Mock PlpFile mockExistingPlpFile;

    @Test
    void createPlpFileSuccessFulTest() {
        Assertions.assertDoesNotThrow(() -> new PlpFile("test.asm", mockProgram));
    }

    @Test
    void createPlpFileFailureTest() {
        when(mockProgram.getAsmFile("test.asm")).thenReturn(mockExistingPlpFile);
        Assertions.assertThrows(IOException.class, () -> new PlpFile("test.asm", mockProgram));
    }
}
