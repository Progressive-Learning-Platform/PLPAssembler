package org.plp.implementations.plpisa.argument;

import org.plp.implementations.plpisa.PlpArgumentType;
import org.plp.isa.AsmArgument;
import org.plp.isa.AsmArgumentType;

/**
 * Class representing a CharLiteral argument in PLP.
 * Example: 'c'
 */
public class PlpCharLiteralArgument implements AsmArgument {
    private final String argument;

    /**
     * Constructs an {@link AsmArgument} that holds a char literal
     *
     * @param argument String containing the char as well as the surrounding single quotes (')
     */
    public PlpCharLiteralArgument(String argument) {
        this.argument = argument;
    }

    /**
     * This will provide the encoded value of the current argument
     *
     * @return encoded value
     */
    @Override
    public long encode() {
        return argument.charAt(1); // return the char as an int (long) value
    }

    /**
     * String representation of the current argument
     *
     * @return argument as written in program
     */
    @Override
    public String raw() {
        return argument;
    }

    /**
     * The {@link PlpArgumentType} this argument represents
     *
     * @return {@link PlpArgumentType} argument type
     */
    @Override
    public AsmArgumentType getType() {
        return PlpArgumentType.CHAR;
    }
}
