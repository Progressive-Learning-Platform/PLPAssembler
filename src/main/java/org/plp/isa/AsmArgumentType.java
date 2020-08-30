package org.plp.isa;

/**
 * Every architecture will have the instructions. Each of these instruction will have nemonic + set of arguments
 * This interfaces provides the type of arguments which the instruction can take.
 * The enum which will implement this interface will define what are the type of {@link AsmArgument} exists
 */
public interface AsmArgumentType {

    /**
     * Given an instruction's argument, this will provide the corresponding implementation of the {@link AsmArgument}
     * @param argumentContent string representation of the instruction's argument
     * @return {@link AsmArgument} representation of argument passed
     */
    public abstract AsmArgument parse(String argumentContent);
}
