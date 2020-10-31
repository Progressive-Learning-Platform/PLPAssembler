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
     * Given an instruction's argument, this will provide the corresponding implementation
     * of the {@link AsmArgument}
     *
     * @param argumentContent string representation of the instruction's argument
     * @return {@link AsmArgument} representation of argument passed
     */
    AsmArgument parse(String argumentContent);
}
