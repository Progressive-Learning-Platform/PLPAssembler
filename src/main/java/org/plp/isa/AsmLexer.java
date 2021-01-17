package org.plp.isa;

import lombok.NonNull;

import java.util.List;

/**
 * Given a string it will provide the list of tokens present in that string
 */
public interface AsmLexer {
    /**
     * Given a string it will provide a list of tokens consisting of the string
     * @param input String which needs to be broken into individual tokens
     * @return List of {@link AsmToken} consisting of that string
     */
    List<AsmToken> lex(@NonNull String input);
}
