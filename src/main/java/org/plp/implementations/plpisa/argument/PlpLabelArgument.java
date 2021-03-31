package org.plp.implementations.plpisa.argument;

import org.plp.implementations.plpisa.PlpArgumentType;
import org.plp.implementations.plpisa.PlpAssembler;
import org.plp.implementations.plpisa.exceptions.PlpArgumentException;
import org.plp.isa.AsmArgument;
import org.plp.isa.AsmArgumentType;

/**
 * Class representing a Label argument in PLP.
 * Example: LABEL
 */
public class PlpLabelArgument implements AsmArgument {
    private final String argument;

    /**
     * Constructs an {@link AsmArgument} that holds a Label
     * @param argument Label to be stored
     */
    public PlpLabelArgument(String argument) {
        this.argument = argument;
    }

    @Override
    public long encode() {
        if(!PlpAssembler.SYMBOL_TABLE.symbolExists(argument)) {
            throw new PlpArgumentException("Label does not exist in memory");
        }
        return PlpAssembler.SYMBOL_TABLE.getAddressOfSymbol(argument);
    }

    @Override
    public String raw() {
        return this.argument;
    }

    @Override
    public AsmArgumentType getType() {
        return PlpArgumentType.LABEL;
    }
}
