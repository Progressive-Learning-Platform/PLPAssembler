package org.plp.implementations.plpisa;

import org.plp.isa.AsmArgument;
import org.plp.isa.AsmArgumentType;
import org.plp.isa.AsmTokenType;

/**
 * Enumeration that contains the different acceptable argument types for PLP and
 * provides a method to generate the appropriate argument object
 */
public enum PlpArgumentType implements AsmArgumentType {
    /**
     * Character literal 'c'
     */
    CHAR(PlpTokenType.CHARACTER),

    /**
     * String literal "s"
     */
    STRING(PlpTokenType.STRING),

    /**
     * Label storing memory address LABEL
     */
    LABEL(PlpTokenType.LABELUSAGE),

    /**
     * Register value $t1
     */
    REGISTER(PlpTokenType.REGISTER),

    /**
     * Memory argument 4($t4)
     */
    MEMORY(PlpTokenType.MEMORYLOCATION),

    /**
     * Whole number numeric literal 4
     */
    NUMERIC(PlpTokenType.NUMERIC);

    private final AsmTokenType tokenType;

    private PlpArgumentType(AsmTokenType tokenType) {
        this.tokenType = tokenType;
    }

    /**
     * The {@link AsmTokenType} which has the regex representation of given Argument
     *
     * @return {@link AsmTokenType} of the {@link AsmArgument}
     */
    @Override
    public AsmTokenType tokenType() {
        return tokenType;
    }
}
