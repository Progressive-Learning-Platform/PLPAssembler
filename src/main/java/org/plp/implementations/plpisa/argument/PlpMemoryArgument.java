package org.plp.implementations.plpisa.argument;

import org.plp.implementations.plpisa.PlpArgumentType;
import org.plp.isa.AsmArgument;
import org.plp.isa.AsmArgumentType;

/**
 * Class representing a Memory argument in PLP.
 * Example: ($t1), 4($t4), LABEL, LABEL+4
 */
public class PlpMemoryArgument implements AsmArgument {
    private final String argument;
    private PlpNumericLiteralArgument offset;
    private PlpRegisterArgument register;

    /**
     * Constructs an {@link AsmArgument} that holds a memory argument
     * @param argument String containing the argument
     */
    public PlpMemoryArgument(String argument) {
        this.argument = argument;
    }

    @Override
    public long encode() {
        // for PLP only possible address format would be number(register)
        // Example: 45($t1)
        // It uses the register part and offset part separately for encoding.

        String[] parts = argument.split("\\(");
        offset = new PlpNumericLiteralArgument(parts[0]);
        register = new PlpRegisterArgument(parts[1].substring(0, parts[1].length()));

        return offset.encode();
    }

    /**
     * This will provide the register number present in memory argument
     * @return register number
     */
    public long registerEncode() {
        if(register == null) {
            encode();
        }
        return register.encode();
    }

    /**
     * This provides the Register part of the Argument
     * @return {@link PlpRegisterArgument} register part of memory argument
     */
    public PlpRegisterArgument getRegisterArgumentOfMemoryArg() {
        return register;
    }

    /**
     * This provides the Numeric/Offset part of the Argument
     * @return {@link PlpNumericLiteralArgument} offset/numeric part of memory argument
     */
    public PlpNumericLiteralArgument getOffsetArgumentOfMemoryArg() {
        return offset;
    }

    @Override
    public String raw() {
        return argument;
    }

    @Override
    public AsmArgumentType getType() {
        return PlpArgumentType.MEMORY;
    }
}
