package org.plp.isa;

/**
 * This manage the symbol table used by the Assembler.
 * Its implementation will be as simple a hashtable
 */
public interface AsmSymbolTable {
    /**
     * This function will add the label and its address to its internal storage
     * @param label This is the symbol whos address needs to be saved
     * @param address address of the label
     */
    void addSymbol(String label, Long address);

    /**
     * Verify if the label exists in the symbol table
     * @param label symbol whose entry needs to be searched for
     * @return True if symbol exists else false
     */
    boolean isSymbolExists(String label);

    /**
     * Get the address of the symbol/label stored
     * @param label symbol whose address needs to be fetched
     * @return address of the label/symbol
     */
    Long getAddressOfSymbol(String label);
}
