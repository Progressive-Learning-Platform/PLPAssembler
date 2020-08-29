package org.plp.isa;

/**
 * This processes a type of assembler directives based on the {@link AsmArgument}s passed to it
 */
public interface AsmAssemblerDirectiveProcessor {
    /**
     * This processes the assembler directive based on the arguments passed and returns the
     * replacement placeholder for further assembling of the assembly program
     * @param asmArguments list of arguments in the order needed for processing directive
     * @return replacement placeholder instruction to be used during assembly
     */
    String perform(AsmArgument[] asmArguments);

    /**
     * List of type of {@link AsmArgument} that is needed to process this directive
     * @return array of {@link AsmArgumentType}
     */
    AsmArgumentType[] getArgumentsOfAssembler();
}
