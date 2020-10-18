package org.plp.isa;

/**
 * This represent the individual arguments of an instruction
 * Example arguments of an instruction are register, memory location, numeric literal value,
 * string literal, etc
 */
public interface AsmArgument {
    /**
     * This will provide the encoded value of the current argument
     * @return encoded value
     */
    long encode();

    /**
     * String representation of the current argument
     * @return argument as written in program
     */
    String raw();

    /**
     * The {@link AsmArgumentType} this argument represents
     * @return {@link AsmArgumentType} argument type
     */
    AsmArgumentType getType();
}
