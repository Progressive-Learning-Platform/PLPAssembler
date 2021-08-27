package org.plp.implementations.plpisa;

import org.plp.implementations.plpisa.argument.PlpStringLiteralArgument;
import org.plp.implementations.plpisa.argument.PlpCharLiteralArgument;
import org.plp.implementations.plpisa.argument.PlpLabelArgument;
import org.plp.implementations.plpisa.argument.PlpNumericLiteralArgument;
import org.plp.implementations.plpisa.argument.PlpRegisterArgument;
import org.plp.implementations.plpisa.argument.PlpMemoryArgument;
import org.plp.implementations.plpisa.exceptions.PlpArgumentException;
import org.plp.isa.AsmArgument;
import org.plp.isa.AsmArgumentProcessor;
import org.plp.isa.AsmToken;

/**
 * Converts the given {@link PlpToken} to one of the {@link AsmArgument}
 */
public class PlpArgumentProcessor implements AsmArgumentProcessor {

    private final PlpSymbolTable symbolTable;

    /**
     * Creates an argument processor
     * @param symbolTable used for accessing address of labels
     */
    public PlpArgumentProcessor(PlpSymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    /**
     * Create the argument based on the token passed
     *
     * @param token {@link AsmToken} to convert to {@link AsmArgument}
     * @return {@link AsmArgument} equivalent of token
     * @throws PlpArgumentException if an ill legal {@link AsmToken} is passed
     */
    @Override
    public AsmArgument getArgument(AsmToken token) {
        if(token.getTokenType() == PlpArgumentType.CHAR.tokenType()) {
            return new PlpCharLiteralArgument(token.getValue());
        } else if(token.getTokenType() == PlpArgumentType.LABEL.tokenType()) {
            return new PlpLabelArgument(token.getValue(), symbolTable);
        } else if(token.getTokenType() == PlpArgumentType.NUMERIC.tokenType()) {
            return new PlpNumericLiteralArgument(token.getValue());
        } else if(token.getTokenType() == PlpArgumentType.STRING.tokenType()) {
            return new PlpStringLiteralArgument(token.getValue());
        } else if(token.getTokenType() == PlpArgumentType.REGISTER.tokenType()) {
            return new PlpRegisterArgument(token.getValue());
        } else if(token.getTokenType() == PlpArgumentType.MEMORY.tokenType()) {
            return new PlpMemoryArgument(token.getValue());
        } else {
            throw new PlpArgumentException("Illegal or Unsupported Argument");
        }
    }
}
