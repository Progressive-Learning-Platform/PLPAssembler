package org.plp.isa.exceptions;

/**
 * A generic exception which is generated during the Assembling of an assembly program
 */
public class AsmAssemblerException extends RuntimeException{
    public AsmAssemblerException(String message) {
        super(message);
    }
}
