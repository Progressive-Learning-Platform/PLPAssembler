package org.plp.implementations.plpisa.assemblerdirective;

import org.plp.implementations.plpisa.PlpCurrentAddressManager;
import org.plp.implementations.plpisa.exceptions.PlpAssemblerException;
import org.plp.isa.AsmArgument;
import org.plp.isa.AsmAssemblerDirectiveAssembler;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Function;

/**
 * This would have all the assembler directive implementation references.
 * This would be responsible for executing respective assembler directive assembler given a set of arguments
 */
public class PlpAssemblerDirectivePerformer {
    /**
     * This would manage the current address of the program being assembled.
     */
    private final PlpCurrentAddressManager addressManager;

    /**
     * Map of assembler directive name to its assembler implementation
     */
    private final Map<String, Function<List<AsmArgument>, String>> directivesMap;

    /**
     * This will create the AssemblerDirectivePerformer which will have all the Assembler directive implementations
     * @param addressManager Assemblers current address manager
     */
    public PlpAssemblerDirectivePerformer(PlpCurrentAddressManager addressManager) {
        this.addressManager = addressManager;
        this.directivesMap = new HashMap<>();

        populateDirectiveAssemblers();
    }

    /**
     * This will create the known Assembler Directive Assemblers and add them to our map to be used for
     * assembling assembler directives
     *
     * In the future, we should remove this, when the orchestrator comes, it will use addAssemblerDirective
     * to do this work outside this class
     */
    private void populateDirectiveAssemblers() {
        OrgAssemblerDirectiveAssembler orgAssemblerDirectiveAssembler = new
                OrgAssemblerDirectiveAssembler(addressManager);

        directivesMap.put(".org", orgAssemblerDirectiveAssembler::perform);
    }

    /**
     * This will return true or false based on if there is an implementation for the given assembler directive
     * @param assemblerDirective Assembler Directive whose implementation needs to be verified example .org
     * @return True if assemblerDirective's implementation exists else false
     */
    public boolean isAssemblerDirectiveExists(String assemblerDirective) {
        return directivesMap.containsKey(assemblerDirective);
    }

    /**
     * This will add the implementation of the Assembler Directive to be used for assembling
     * @param directiveName directive name whose implementation is being passed example .org
     * @param assemblerDirectiveAssembler Implementation of the assembler directive
     */
    public void addDirectiveAssembler(String directiveName,
                                      AsmAssemblerDirectiveAssembler assemblerDirectiveAssembler) {
        directivesMap.put(directiveName, assemblerDirectiveAssembler::perform);
    }

    /**
     * This assembles the given assembler directive and its arguments
     * @param directive assembler directive whose implementation needs to be used for assembling
     * @param arguments list of {@link AsmArgument}s of the assembler directive
     * @return result of the assembling of the directive
     * @throws PlpAssemblerException if no implementation of the directive is found
     */
    public String perform(String directive, List<AsmArgument> arguments) throws PlpAssemblerException {
        if(!directivesMap.containsKey(directive)){
            throw new PlpAssemblerException(String.format("No implementation of %s directive found", directive));
        }
        return directivesMap.get(directive).apply(arguments);
    }
}
