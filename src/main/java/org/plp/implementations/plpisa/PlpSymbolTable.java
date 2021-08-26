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

    /**
     * This function will add the label and its address to its internal storage
     * @param label This is the symbol whos address needs to be saved
     * @param address address of the label
     */
    @Override
    public void addSymbol(String label, Long address) {
        symbolTable.put(label, address);
    }

    /**
     * Verify if the label exists in the symbol table
     * @param label symbol whose entry needs to be searched for
     * @return True if symbol exists else false
     */
    @Override
    public boolean isSymbolExists(String label) {
        return symbolTable.containsKey(label);
    }

    /**
     * Get the address of the symbol/label stored
     * @param label symbol whose address needs to be fetched
     * @return address of the label/symbol
     */
    @Override
    public Long getAddressOfSymbol(String label) {
        return symbolTable.get(label);
    }
}
