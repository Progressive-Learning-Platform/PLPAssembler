package org.plp.isa;

/**
 * This interface will be implemented by the respective architecture enums.
 * This interface represents each of the tokens which can be part of the program.
 */
public interface AsmTokenType {
    /**
     * The regex representation of the given token
     * @return regex value of the AsmTokenType
     */
    public abstract String regex();
}
