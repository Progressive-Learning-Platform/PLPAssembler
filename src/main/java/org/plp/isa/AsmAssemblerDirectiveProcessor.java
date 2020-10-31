package org.plp.isa;

import org.plp.isa.exceptions.AsmAssemblerException;

/**
 * This processes a type of assembler directives based on the {@link AsmArgument}s passed to it
 *
 * An assembler directive can have multiple arguments.
 *
 * Example of single argument assemblerDirective .org 0x10000000
 * Example of multiple argument assembler Directive .equ mylocation 0x200000
 * So PlpOrgAssemblerDirectiveProcessor needs to handle single argument 0x100000 where as
 * PlpEquAssemblerDirectiveProcessor needs to handle 2 arguments mylocation and 0x200000.
 *
 * So interface is saying an assembler directive can have multiple arguments
 * but individual implementation will decide the actual number of arguments
 * needed for their directive.
 */
public interface AsmAssemblerDirectiveProcessor {
    /**
     * This processes the assembler directive based on the arguments passed and returns the
     * replacement placeholder for further assembling of the assembly program
     *
     * @param asmArguments list of arguments in the order needed for processing directive
     * @return replacement placeholder instruction to be used during assembly
     * @throws AsmAssemblerException - thrown if the directive cannot be processed
     */
    String perform(AsmArgument[] asmArguments) throws AsmAssemblerException;

    /**
     * List of type of {@link AsmArgument} that is needed to process this directive
     * @return array of {@link AsmArgumentType}
     */
    AsmArgumentType[] getArgumentTypesOfAssembler();
}
