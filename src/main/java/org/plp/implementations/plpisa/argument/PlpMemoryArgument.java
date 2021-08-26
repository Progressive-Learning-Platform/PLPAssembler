package org.plp.implementations.plpisa.argument;

import org.plp.implementations.plpisa.PlpArgumentType;
import org.plp.implementations.plpisa.PlpAssembler;
import org.plp.isa.AsmArgument;
import org.plp.isa.AsmArgumentType;

/**
 * Class representing a Memory argument in PLP.
 * Example: ($t1), 4($t4), LABEL, LABEL+4
 */
public class PlpMemoryArgument implements AsmArgument {
    private final String argument;

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
        int offset = 0;
        int start = argument.indexOf("(");
        // strip offset, $, and parentheses from the argument
        long memory = PlpAssembler.SYMBOL_TABLE
                .getAddressOfSymbol(argument.substring(start + 2, argument.length() - 1));
        // if the ( is not at index 0, then an offset exists -> ($t3) vs 4($t3)
        if(start != 0) {
            offset = Integer.parseInt(argument.substring(0, start));
        }
        return memory + offset;
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
