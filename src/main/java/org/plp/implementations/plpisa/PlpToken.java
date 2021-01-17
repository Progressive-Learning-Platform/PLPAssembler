package org.plp.implementations.plpisa;

import lombok.NonNull;
import org.plp.isa.AsmToken;
import org.plp.isa.AsmTokenType;

/**
 * This represents the each tokens which will be created by the Lexer or Tokenizer
 * Example, In assembler directive `.org 0x100000`,
 * here .org is a token and 0x100000 is also another token.
 */
public class PlpToken implements AsmToken {

    PlpTokenType plpTokenType;
    String actualValue;

    /**
     * Create a plptoken based on tokentype and the actual value
     *
     * @param plpTokenType {@link PlpTokenType} token type of actual value
     * @param actualValue value whose token representation is stored
     */
    public PlpToken(@NonNull PlpTokenType plpTokenType, @NonNull String actualValue) {
        this.plpTokenType = plpTokenType;
        this.actualValue = actualValue;
    }

    /**
     * This will return the token type of the value stored.
     * if value is .org then token type would be DIRECTIVE
     *
     * @return {@link AsmTokenType} Token type of value.
     */
    @Override
    public AsmTokenType getTokenType() {
        return plpTokenType;
    }

    /**
     * This will return the actual value of the Token stored
     *
     * @return actual value stored as string
     */
    @Override
    public String getValue() {
        return actualValue;
    }
}
