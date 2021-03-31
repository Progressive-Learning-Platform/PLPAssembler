package org.plp.implementations.plpisa;

import org.plp.implementations.plpisa.argument.*;
import org.plp.implementations.plpisa.exceptions.PlpArgumentException;
import org.plp.isa.AsmArgument;
import org.plp.isa.AsmArgumentType;

/**
 * Enumeration that contains the different acceptable argument types for PLP and
 * provides a method to generate the appropriate argument object
 */
public enum PlpArgumentType implements AsmArgumentType {
    /**
     * Character literal -> 'c'
     */
    CHAR,
    /**
     * String literal -> "s"
     */
    STRING,
    /**
     * Label storing memory address -> LABEL
     */
    LABEL,
    /**
     * Register value -> $t1
     */
    REGISTER,
    /**
     * Memory argument -> 4($t4), (t4), LABEL+4
     */
    MEMORY,
    /**
     * Whole number numeric literal -> 4
     */
    NUMERIC;

    /**
     * Given an instruction's argument, this will provide the corresponding implementation
     * of the {@link AsmArgument}. The type will be determined by the patterns denoted in
     * {@link PlpTokenType}
     *
     * @param argumentContent string representation of the instruction's argument
     * @return {@link AsmArgument} representation of argument passed
     * @throws PlpArgumentException if argumentContent is not a valid argument type
     */
    @Override
    public AsmArgument parse(String argumentContent) throws PlpArgumentException {
        if(argumentContent.matches(PlpTokenType.CHARACTER.regex())) {
            return new PlpCharLiteralArgument(argumentContent);
        } else if(argumentContent.matches(PlpTokenType.LABEL_USAGE.regex())) {
            return new PlpLabelArgument(argumentContent);
        } else if(argumentContent.matches(PlpTokenType.NUMERIC.regex())) {
            return new PlpNumericLiteralArgument(argumentContent);
        } else if(argumentContent.matches(PlpTokenType.STRING.regex())) {
            return new PlpStringLiteralArgument(argumentContent);
        } else if(argumentContent.matches(PlpTokenType.REGISTER.regex())) {
            return new PlpRegisterArgument(argumentContent);
        } else if(argumentContent.matches(PlpTokenType.PARENTHESIS_REGISTER.regex())) {
            return new PlpMemoryArgument(argumentContent);
        } else {
            throw new PlpArgumentException("Illegal or Unsupported Argument");
        }
    }
}
