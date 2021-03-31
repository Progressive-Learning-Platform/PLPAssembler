package org.plp.implementations.plpisa.argument;

import org.plp.implementations.plpisa.PlpArgumentType;
import org.plp.isa.AsmArgument;
import org.plp.isa.AsmArgumentType;

/**
 * Class representing a Numeric argument in PLP.
 * Example: 4
 */
public class PlpNumericLiteralArgument implements AsmArgument {
    private final String argument;

    /**
     * Constructs an {@link AsmArgument} that holds an integer
     * @param argument number to be stored
     */
    public PlpNumericLiteralArgument(String argument) {
        this.argument = argument;
    }

    @Override
    public long encode() {
        // parse argument
        return Integer.parseInt(argument);
    }

    @Override
    public String raw() {
        return this.argument;
    }

    @Override
    public AsmArgumentType getType() {
        return PlpArgumentType.NUMERIC;
    }
}
