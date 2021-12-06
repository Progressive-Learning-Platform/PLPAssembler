package org.plp.implementations.plpisa.assemblerdirective;

import org.plp.implementations.plpisa.PlpArgumentType;
import org.plp.implementations.plpisa.PlpCurrentAddressManager;
import org.plp.isa.AsmArgument;
import org.plp.isa.AsmArgumentType;
import org.plp.isa.AsmAssemblerDirectiveAssembler;
import org.plp.isa.exceptions.AsmAssemblerException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This will handle the PLP Assembler Directive - .org
 * Example: .org 0x100000
 */
public class OrgAssemblerDirectiveAssembler implements AsmAssemblerDirectiveAssembler {
    private final PlpCurrentAddressManager addressManager;
    private final List<AsmArgumentType> expectedArguments;
    private static final String INTERMEDIATE_REPRESENTATION = "__ASM_ORG__";

    /**
     * This will create the assembler which encodes .org assembler directive
     * @param addressManager {@link PlpCurrentAddressManager} address manager which will modify the address
     */
    public OrgAssemblerDirectiveAssembler(PlpCurrentAddressManager addressManager) {
        this.addressManager = addressManager;
        expectedArguments = new ArrayList<>();
        expectedArguments.add(PlpArgumentType.NUMERIC);
    }


    /**
     * This processes the assembler directive based on the arguments passed and returns the
     * replacement placeholder for further assembling of the assembly program
     *
     * @param asmArguments list of arguments in the order needed for processing directive
     * @return replacement placeholder instruction to be used during assembly
     * @throws AsmAssemblerException - thrown if the directive cannot be processed
     */
    @Override
    public String perform(List<AsmArgument> asmArguments) throws AsmAssemblerException {
        validateArguments(asmArguments);
        addressManager.setCurrentAddress(asmArguments.get(0).encode());
        return INTERMEDIATE_REPRESENTATION;
    }

    /**
     * List of type of {@link AsmArgument} that is needed to process this directive
     *
     * @return array of {@link AsmArgumentType}
     */
    @Override
    public List<AsmArgumentType> getArgumentTypesOfAssembler() {
        return Collections.unmodifiableList(expectedArguments);
    }
}
