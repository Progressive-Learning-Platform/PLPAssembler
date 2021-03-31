package org.plp.implementations.plpisa.argument;

import org.plp.implementations.plpisa.PlpArgumentType;
import org.plp.isa.AsmArgument;
import org.plp.isa.AsmArgumentType;

/**
 * Class representing a String argument in PLP.
 * Example: "hello"
 */
public class PlpStringLiteralArgument implements AsmArgument {
    private final String argument;

    /**
     * Constructs an {@link AsmArgument} that holds a String literal
     * @param argument String to be stored
     */
    public PlpStringLiteralArgument(String argument) {
        this.argument = argument;
    }

    @Override
    public long encode() {
        // TODO: need memory location
        throw new UnsupportedOperationException("Need to store string in memory");
    }

    @Override
    public String raw() {
        return this.argument;
    }

    @Override
    public AsmArgumentType getType() {
        return PlpArgumentType.STRING;
    }
}
