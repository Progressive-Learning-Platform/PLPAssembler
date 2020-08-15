package org.plp.isa.exceptions;

/**
 * A generic exception which is generated during the Assembling of an assembly program
 */
public class AssemblerException extends RuntimeException{
    public AssemblerException(String message) {
        super(message);
    }
}
