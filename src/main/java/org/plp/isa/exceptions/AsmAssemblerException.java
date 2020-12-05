package org.plp.isa.exceptions;

/**
 * A generic exception which is generated during the Assembling of an assembly program
 */
public class AsmAssemblerException extends RuntimeException {

    /**
     * Constructor of the exception
     * @param message this is the reason for the error
     */
    public AsmAssemblerException(String message) {
        super(message);
    }
}
