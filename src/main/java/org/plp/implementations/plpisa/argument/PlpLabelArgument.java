package org.plp.implementations.plpisa.argument;

import org.plp.implementations.plpisa.PlpArgumentType;
import org.plp.implementations.plpisa.PlpSymbolTable;
import org.plp.implementations.plpisa.exceptions.PlpArgumentException;
import org.plp.isa.AsmArgument;
import org.plp.isa.AsmArgumentType;

/**
 * Class representing a Label argument in PLP.
 * Example: LABEL
 */
public class PlpLabelArgument implements AsmArgument {
    private final String argument;
    private final PlpSymbolTable symbolTable;

    /**
     * Constructs an {@link AsmArgument} that holds a Label
     * @param argument Label to be stored
     * @param symbolTable {@link PlpSymbolTable} where labels address is present
     */
    public PlpLabelArgument(String argument, PlpSymbolTable symbolTable) {
        this.argument = argument;
        this.symbolTable = symbolTable;
    }

    @Override
    public long encode() {
        if(!symbolTable.isSymbolExists(argument)) {
            throw new PlpArgumentException("Label "+ argument +" does not exist in memory");
        }
        return symbolTable.getAddressOfSymbol(argument);
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
