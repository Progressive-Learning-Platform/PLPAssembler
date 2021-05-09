package org.plp.isa;

/**
 * Given a line, Tokenizer/Lexer will generate a list of tokens
 * Example: Given `.org 0x100000` as input to Lexer, it will
 * generate two tokens DIRECTIVE(.org) and NUMERIC(0x100000)
 */
public interface AsmToken {
    /**
     * This will return the token type of the value stored.
     * if value is .org then token type would be DIRECTIVE
     * @return {@link AsmTokenType} Token type of value.
     */
    AsmTokenType getTokenType();

    /**
     * This will return the actual value of the Token stored
     * @return actual value stored as string
     */
    String getValue();
}
