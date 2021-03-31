package org.plp.implementations.plpisa;

import org.plp.isa.AsmSymbolTable;

import java.util.Hashtable;

/**
 * This manages the symbol table used by the Assembler
 * using a simple hashtable. Hashtable is chosen over
 * a HashMap to handle synchronization if multi-threading
 * is implemented
 */
public class PlpSymbolTable implements AsmSymbolTable {
    private final Hashtable<String, Long> symbolTable = new Hashtable<>();

    @Override
    public void addSymbol(String label, Long address) {
        symbolTable.put(label, address);
    }

    @Override
    public boolean symbolExists(String label) {
        return symbolTable.containsKey(label);
    }

    @Override
    public Long getAddressOfSymbol(String label) {
        return symbolTable.get(label);
    }
}
