package org.plp.implementations.plpisa.exceptions;

import org.plp.isa.exceptions.AsmAssemblerException;

/**
 * Exception to be thrown when there is an issue with the
 * assembling being done in PLP
 */
public class PlpAssemblerException extends AsmAssemblerException {
    /**
     * Constructor that requires a message to describe the specific
     * issue with the argument
     * @param message Explanation of the issue with the argument
     */
    public PlpAssemblerException(String message) {
        super(message);
    }
}
