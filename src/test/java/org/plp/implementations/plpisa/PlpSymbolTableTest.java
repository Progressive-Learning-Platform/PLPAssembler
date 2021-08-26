package org.plp.implementations.plpisa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlpSymbolTableTest {
    @Test
    public void testSymbolTable() {
        PlpSymbolTable symbolTable = new PlpSymbolTable();
        Assertions.assertFalse(symbolTable.isSymbolExists("test_label"));
        symbolTable.addSymbol("test_label", 123456789L);
        Assertions.assertTrue(symbolTable.isSymbolExists("test_label"));
        Assertions.assertEquals(123456789L, symbolTable.getAddressOfSymbol("test_label"));
    }

}
