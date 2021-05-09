package org.plp.isa.exceptions;

/**
 * This exception is thrown when it finds input string or token that cannot be classified
 */
public class AsmIllegalTokenException extends AsmAssemblerException {
    /**
     * Constructor of the exception
     *
     * @param message this is the reason for the error
     */
    public AsmIllegalTokenException(String message) {
        super(message);
    }
}
