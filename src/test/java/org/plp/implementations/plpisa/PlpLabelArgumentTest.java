package org.plp.implementations.plpisa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.plp.implementations.plpisa.argument.PlpLabelArgument;
import org.plp.implementations.plpisa.exceptions.PlpArgumentException;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlpLabelArgumentTest {

    @Mock
    PlpSymbolTable symbolTable;

    @Test
    public void testLabelArgument() {
        PlpLabelArgument argument = new PlpLabelArgument("test_label", symbolTable);
        when(symbolTable.isSymbolExists("test_label")).thenReturn(Boolean.TRUE);
        when(symbolTable.getAddressOfSymbol("test_label")).thenReturn(123456L);

        Assertions.assertEquals("test_label", argument.raw());
        Assertions.assertEquals(123456L, argument.encode());
        Assertions.assertEquals(PlpArgumentType.LABEL, argument.getType());
    }

    @Test
    public void testLabelDoNotExistArgument() {
        PlpLabelArgument argument = new PlpLabelArgument("test_label", symbolTable);
        when(symbolTable.isSymbolExists("test_label")).thenReturn(Boolean.FALSE);

        Assertions.assertThrows(PlpArgumentException.class, argument::encode, "Label test_label does not exist in memory");
    }
}
