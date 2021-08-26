package org.plp.isa;

/**
 * This will help to get the right {@link AsmArgument} based on the {@link AsmToken} Passed
 */
public interface AsmArgumentProcessor {

    /**
     * Create the argument based on the token passed
     * @param token {@link AsmToken} to convert to {@link AsmArgument}
     * @return {@link AsmArgument} equivalent of token
     */
    AsmArgument getArgument(AsmToken token);

}
