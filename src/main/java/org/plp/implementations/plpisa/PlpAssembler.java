package org.plp.implementations.plpisa;

import org.plp.isa.AsmAssembler;
import org.plp.isa.AsmImage;
import org.plp.isa.AsmProgram;
import org.plp.isa.exceptions.AsmAssemblerException;

/**
 * This class will be the driver for the PLP instance. It will be responsible for
 * creating the symbol table and the lexer, loading the program and the files it
 * contains, reading the files, line-by-line, and parsing them into assembly
 * instructions that will be compiled into machine code and disassembly code
 */
public class PlpAssembler implements AsmAssembler {
    // TODO: Currently incomplete. Just a dummy class to allow for compilation
    /**
     * Symbol table to be used by the entire program to store all the labels, registers,
     */
    public static final PlpSymbolTable SYMBOL_TABLE = new PlpSymbolTable();
    private final PlpProgram program;

    /**
     * Constructor that will take a PLP Program to process
     * @param program the PLP Program to process
     */
    public PlpAssembler(PlpProgram program) {
        initializeSymbolTable();
        this.program = program;
    }

    @Override
    public AsmImage assemble(AsmProgram asmProgram) throws AsmAssemblerException {
        throw new UnsupportedOperationException("Cannot assemble yet");
    }

    private void initializeSymbolTable() {
        // TODO: add registers to table here?
    }
}
