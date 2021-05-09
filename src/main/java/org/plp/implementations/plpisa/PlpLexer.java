package org.plp.implementations.plpisa;

import lombok.NonNull;
import org.plp.isa.AsmLexer;
import org.plp.isa.AsmToken;
import org.plp.isa.exceptions.AsmIllegalTokenException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Given a line, this will provide a list of {@link PlpToken}
 * Some of the ideas taken from
 * http://giocc.com/writing-a-lexer-in-java-1-7-using-regex-named-capturing-groups.html
 */
public class PlpLexer implements AsmLexer {

    /**
     * Given a string it will provide a list of tokens consisting of the string
     *
     * @param input String which needs to be broken into individual tokens
     * @return List of {@link AsmToken} consisting of that string
     * @throws AsmIllegalTokenException when it cannot find the proper token type
     */
    @Override
    public List<AsmToken> lex(@NonNull String input) throws AsmIllegalTokenException {

        input = input.trim();

        List<AsmToken> asmTokens = new ArrayList<>();

        Pattern pattern = getCumulativeTokenPattern();
        Matcher matcher = pattern.matcher(input);
        Matcher nonSpaceMatcher = Pattern.compile("\\S").matcher(input);

        int expectedTokenStartIndex = 0;

        while(matcher.find(expectedTokenStartIndex)) {
            if(expectedTokenStartIndex != matcher.start()) {
                String errorMessage = String.format(
                        "ERROR! Invalid Character Found: %s",
                        input.substring(expectedTokenStartIndex));

                throw new AsmIllegalTokenException(errorMessage);
            }

            for(PlpTokenType tokenType: PlpTokenType.values()) {
                if(matcher.group(tokenType.name()) != null) {
                    asmTokens.add(new PlpToken(tokenType, matcher.group(tokenType.name())));
                    break;
                }
            }

            expectedTokenStartIndex = matcher.end();
            if(nonSpaceMatcher.find(expectedTokenStartIndex)) {
                expectedTokenStartIndex = nonSpaceMatcher.start();
            }
        }

        if(expectedTokenStartIndex < input.length()) {
            String errorMessage = String.format(
                    "ERROR! Invalid Character Found: %s",
                    input.substring(expectedTokenStartIndex));

            throw new AsmIllegalTokenException(errorMessage);

        }

        return asmTokens;
    }

    private Pattern getCumulativeTokenPattern() {
        StringBuilder allPatterns = new StringBuilder();

        for(PlpTokenType tokenType: PlpTokenType.values()) {
            allPatterns.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.regex()));
        }

        return Pattern.compile(allPatterns.substring(1));
    }
}
