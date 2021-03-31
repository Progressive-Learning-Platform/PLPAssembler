package org.plp.implementations.plpisa.exceptions;

import org.plp.isa.exceptions.AsmAssemblerException;

/**
 * Exception to be thrown when there is an issue with the
 * arguments being passed into PLP
 */
public class PlpArgumentException extends AsmAssemblerException {
    /**
     * Constructor that requires a meesage to describe the specific
     * issue with the argument
     * @param message Explanation of the issue with the argument
     */
    public PlpArgumentException(String message) {
        super(message);
    }
}
