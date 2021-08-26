package org.plp.isa;

/**
 * Every architecture will have the instructions. Each of these instruction will have
 * a mnemonic + a set of arguments.
 * This interface provides the type of arguments that the instruction can take.
 * The enum which will implement this interface will define the types of {
 * @link AsmArgument} that exist
 */
public interface AsmArgumentType {

    /**
     * The {@link AsmTokenType} which has the regex representation of given Argument
     * @return {@link AsmTokenType} of the {@link AsmArgument}
     */
    public abstract AsmTokenType tokenType();
}
