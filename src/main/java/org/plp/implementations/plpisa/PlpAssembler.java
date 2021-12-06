package org.plp.implementations.plpisa;

import org.plp.implementations.plpisa.assemblerdirective.PlpAssemblerDirectivePerformer;
import org.plp.implementations.plpisa.instructions.PlpInstructionsPerformer;
import org.plp.implementations.plpisa.pseudoinstructions.PlpPseudoInstructionsPerformer;
import org.plp.isa.AsmAssembler;
import org.plp.isa.AsmToken;
import org.plp.isa.AsmArgument;
import org.plp.isa.AsmFile;
import org.plp.isa.AsmImage;
import org.plp.isa.AsmProgram;
import org.plp.isa.exceptions.AsmAssemblerException;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will be the driver for the PLP instance. It will be responsible for
 * creating the symbol table and the lexer, loading the program and the files it
 * contains, reading the files, line-by-line, and parsing them into assembly
 * instructions that will be compiled into machine code and disassembly code
 */
public class PlpAssembler implements AsmAssembler {
    /**
     * Symbol table to be used by the entire program to store all the labels, registers,
     */
    private final PlpSymbolTable plpSymbolTable;
    private final PlpArgumentProcessor plpArgumentProcessor;
    private final PlpCurrentAddressManager plpCurrentAddressManager;
    private final PlpLexer plpLexer;
    private final PlpAssemblerDirectivePerformer plpAssemblerDirectivePerformer;
    private final PlpPseudoInstructionsPerformer plpPseudoInstructionsPerformer;
    private final PlpInstructionsPerformer plpInstructionsPerformer;

    private static final String EMPTY_INSTRUCTION_REPRESENTATION = "__ASM_SKIP__";
    private static final String PSEUDO_INSTRUCTION_SEPARATORS = "<<>>";

    /**
     * This will create the PlpAssember which can receive PlpPrograms and assemble them
     * @param plpSymbolTable The symbol table needed for the assembling
     * @param plpArgumentProcessor The argument processor used to process arguments of instructions
     * @param plpCurrentAddressManager This will manage the addresses of the instruction during assembling
     * @param plpLexer This will help to tokenize the given program
     * @param assemblerDirectivePerformer This will encode or operate on all the assembler directives
     * @param pseudoInstructionsPerformer This will encode all the pseudo instructions
     * @param instructionsPerformer this will encode any instructions
     */
    public PlpAssembler(PlpSymbolTable plpSymbolTable,
                        PlpArgumentProcessor plpArgumentProcessor,
                        PlpCurrentAddressManager plpCurrentAddressManager,
                        PlpLexer plpLexer,
                        PlpAssemblerDirectivePerformer assemblerDirectivePerformer,
                        PlpPseudoInstructionsPerformer pseudoInstructionsPerformer,
                        PlpInstructionsPerformer instructionsPerformer) {
        this.plpSymbolTable = plpSymbolTable;
        this.plpArgumentProcessor = plpArgumentProcessor;
        this.plpCurrentAddressManager = plpCurrentAddressManager;
        this.plpLexer = plpLexer;
        this.plpAssemblerDirectivePerformer = assemblerDirectivePerformer;
        this.plpPseudoInstructionsPerformer = pseudoInstructionsPerformer;
        this.plpInstructionsPerformer = instructionsPerformer;
    }

    private void handleLabelDefinitionToken(AsmToken asmToken) {
        String label = asmToken.getValue();
        label = label.replace(":", "");
        if(label.matches(PlpTokenType.DIRECTIVE.regex()) ||
                label.matches(PlpTokenType.PSEUDOINSTRUCTION.regex()) ||
                label.matches(PlpTokenType.INSTRUCTION.regex())) {
            // Throw an exception for using keyword as label definition
            // TODO: replace this with proper exception
            throw new AsmAssemblerException("using keyword as label is not allowed");
        }

        if(plpSymbolTable.isSymbolExists(label)) {
            // Throw an exception symbol is already defined
            // TODO: replace with proper exception
            throw new AsmAssemblerException("label " + label + " is already defined");
        }

        plpSymbolTable.addSymbol(label, plpCurrentAddressManager.getCurrentAddress());
    }

    private List<AsmArgument> tokenToArguments(List<AsmToken> tokens) {
        List<AsmArgument> arguments = new ArrayList<>();
        for(AsmToken asmToken: tokens) {
            if(asmToken.getTokenType() == PlpTokenType.COMMA) {
                continue;
            }
            arguments.add(plpArgumentProcessor.getArgument(asmToken));
        }
        return arguments;
    }

    private void preprocess(PlpImage plpImage) {
        for(AsmFile plpFile: plpImage.getProgram().getAsmFilesOfProgram()) {
            int lineNumber = 1;
            for(String instruction: plpFile.getInstructionsOfFile()) {
                List<AsmToken> tokenList = new ArrayList<>();
                if(!instruction.isEmpty()) {
                    // add $t1, $t2, $t3
                    // my_label: .org 0x100000
                    // j: xyz
                    // TODO: if it is label, check it is not a keyword
                    //  (instruction, pseudo instruction, directives, registers)
                    tokenList = plpLexer.lex(instruction);
                }

                plpImage.storeInstructionsTokens(plpFile.getFileName(), lineNumber, tokenList);
                String intermediateRep = EMPTY_INSTRUCTION_REPRESENTATION;

                if(!tokenList.isEmpty() && tokenList.get(0).getTokenType() != PlpTokenType.COMMENT) {
                    if(tokenList.get(0).getTokenType() == PlpTokenType.DIRECTIVE) {
                        intermediateRep = plpAssemblerDirectivePerformer.perform(tokenList.get(0).getValue(),
                                tokenToArguments(tokenList.subList(1, tokenList.size())));
                    } else if(tokenList.get(0).getTokenType() == PlpTokenType.PSEUDOINSTRUCTION) {
                        intermediateRep = plpPseudoInstructionsPerformer.perform(tokenList.get(0).getValue(),
                                tokenToArguments(tokenList.subList(1, tokenList.size())));
                    } else if(tokenList.get(0).getTokenType() == PlpTokenType.INSTRUCTION) {
                        // TODO: remove comments after instruction
                        plpCurrentAddressManager.incrementAddressForInstruction();
                        intermediateRep = instruction;
                    } else if(tokenList.get(0).getTokenType() == PlpTokenType.LABELDEFINITION) {
                        // Handle label, here we are assuming that my_label: .org 0x10000 is considered illegal
                        handleLabelDefinitionToken(tokenList.get(0));

                    } else {
                        // Throw an error - invalid syntax error
                        // TODO: throw valid exception
                        throw new AsmAssemblerException("invalid token found");
                    }
                }

                plpImage.storeInstructionsIntermediate(plpFile.getFileName(), lineNumber, intermediateRep);
                lineNumber += 1;
            }
        }
    }

    private PlpInstructionDisassembly processInstruction(AsmFile asmFile,
                                                         String instruction,
                                                         long lineNumber,
                                                         List<AsmToken> tokens) {
        Long addressToStore = plpCurrentAddressManager.getCurrentAddress();
        Long encoding = plpInstructionsPerformer.perform(tokens.get(0).getValue(),
                tokenToArguments(tokens.subList(1, tokens.size())));
        PlpInstruction plpInstruction = new PlpInstruction(asmFile, instruction, lineNumber);
        return new PlpInstructionDisassembly(plpInstruction,
                encoding,
                addressToStore);
    }

    private void process(PlpImage plpImage) {
        for(AsmFile plpFile: plpImage.getProgram().getAsmFilesOfProgram()) {
            int lineNumber = 1;
            for(String instruction: plpFile.getInstructionsOfFile()) {
                String intermediate = plpImage.getInstructionsIntermediate(plpFile.getFileName(), lineNumber);
                List<AsmToken> tokens = plpImage.getInstructionsTokens(plpFile.getFileName(), lineNumber);
                if(!EMPTY_INSTRUCTION_REPRESENTATION.equals(intermediate)) {
                    if(tokens.get(0).getTokenType() == PlpTokenType.DIRECTIVE) {
                        plpAssemblerDirectivePerformer.perform(tokens.get(0).getValue(),
                                tokenToArguments(tokens.subList(1, tokens.size())));
                    } else if(tokens.get(0).getTokenType() == PlpTokenType.PSEUDOINSTRUCTION) {
                        // Using intermediate instruction, divide them into individual instructions and process them
                        List<String> instructions = List.of(intermediate.split(PSEUDO_INSTRUCTION_SEPARATORS));
                        for(String subInstruction: instructions) {
                            List<AsmToken> subTokens = plpLexer.lex(subInstruction);
                            PlpInstructionDisassembly plpInstructionDisassembly = processInstruction(plpFile,
                                    subInstruction,
                                    lineNumber,
                                    subTokens);
                            plpImage.addDisassemblyOfInstruction(plpInstructionDisassembly.getActualInstruction(),
                                    plpInstructionDisassembly);
                        }
                    } else {
                        PlpInstructionDisassembly plpInstructionDisassembly = processInstruction(plpFile,
                                instruction,
                                lineNumber,
                                tokens);

                        plpImage.addDisassemblyOfInstruction(plpInstructionDisassembly.getActualInstruction(),
                                plpInstructionDisassembly);
                    }

                }

                lineNumber += 1;
            }
        }
    }

    @Override
    public AsmImage assemble(AsmProgram asmProgram) throws AsmAssemblerException {
        PlpImage image = new PlpImage(asmProgram);
        plpCurrentAddressManager.setCurrentAddress(0L);
        preprocess(image);
        plpCurrentAddressManager.setCurrentAddress(0L);
        process(image);
        return image;
    }
}
