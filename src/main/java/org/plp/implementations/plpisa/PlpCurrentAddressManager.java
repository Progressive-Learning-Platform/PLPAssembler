package org.plp.implementations.plpisa;

import org.plp.isa.AsmCurrentAddressManager;

/**
 * This is responsible for holding the current address and updating the address
 * as the assemble process moves forward
 */
public class PlpCurrentAddressManager implements AsmCurrentAddressManager {
    private long currentAddress;

    /**
     * Constructor that takes the starting address of the program
     * @param startingAddress the first memory location of the program
     */
    public PlpCurrentAddressManager(long startingAddress) {
        this.currentAddress = startingAddress;
    }

    @Override
    public void setCurrentAddress(Long address) {
        this.currentAddress = address;
    }

    @Override
    public Long getCurrentAddress() {
        return this.currentAddress;
    }

    @Override
    public void incrementAddressForInstruction() {
        this.currentAddress += 4;
    }
}
