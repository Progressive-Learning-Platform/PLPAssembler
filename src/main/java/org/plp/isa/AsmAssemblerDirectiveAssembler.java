package org.plp.isa;

import org.plp.isa.exceptions.AsmAssemblerException;

import java.util.List;

/**
 * This processes a type of assembler directives based on the {@link AsmArgument}s passed to it
 *
 * An assembler directive can have multiple arguments.
 *
 * Example of single argument assemblerDirective .org 0x10000000
 * Example of multiple argument assembler Directive .equ mylocation 0x200000
 * So OrgAssemblerDirectiveAssembler needs to handle single argument 0x100000 where as
 * EquAssemblerDirectiveAssembler needs to handle 2 arguments mylocation and 0x200000.
 *
 * So interface is saying an assembler directive can have multiple arguments
 * but individual implementation will decide the actual number of arguments
 * needed for their directive.
 */
public interface AsmAssemblerDirectiveAssembler {


    /**
     * This will verify if passed arguments to assembler directive is of the same argument types as expected
     * @param asmArguments list of arguments passed to assembler directive
     * @throws AsmAssemblerException throws this if arguments mismatch with what is expected
     */
    default void validateArguments(List<AsmArgument> asmArguments) throws AsmAssemblerException {
        if(asmArguments.size() != getArgumentTypesOfAssembler().size()) {
            throw new AsmAssemblerException("Mismatch in number of arguments");
        }

        for(int i = 0; i < asmArguments.size(); i++) {
            if(asmArguments.get(i).getType() != getArgumentTypesOfAssembler().get(i)) {
                throw new AsmAssemblerException(
                        String.format("Expected Argument Type %s but received argument of type %s",
                                getArgumentTypesOfAssembler().get(i),
                                asmArguments.get(i).getType()));
            }
        }
    }

    /**
     * This processes the assembler directive based on the arguments passed and returns the
     * replacement placeholder for further assembling of the assembly program
     *
     * @param asmArguments list of arguments in the order needed for processing directive
     * @return replacement placeholder instruction to be used during assembly
     * @throws AsmAssemblerException - thrown if the directive cannot be processed
     */
    String perform(List<AsmArgument> asmArguments) throws AsmAssemblerException;

    /**
     * List of type of {@link AsmArgument} that is needed to process this directive
     * @return List of {@link AsmArgumentType}
     */
    List<AsmArgumentType> getArgumentTypesOfAssembler();
}
