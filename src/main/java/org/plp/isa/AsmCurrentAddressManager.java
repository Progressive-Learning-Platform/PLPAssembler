package org.plp.isa;

/**
 * This is responsible for holding the current address and updating the address
 * as the assemble process moves forward
 */
public interface AsmCurrentAddressManager {

    /**
     * This will set the current address to the value passed
     * @param address memory address to be used as current address
     */
    void setCurrentAddress(Long address);

    /**
     * This will get the current address
     * @return memory address at which instruction will be stored
     */
    Long getCurrentAddress();

    /**
     * This is increment the address based on the fixed size of the
     * instruction as defined by the ISA, example PLP will increment
     * by 4
     */
    void incrementAddressForInstruction();
}
