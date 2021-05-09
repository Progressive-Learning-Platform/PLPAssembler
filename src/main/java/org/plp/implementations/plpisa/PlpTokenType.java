package org.plp.implementations.plpisa;

import org.plp.isa.AsmTokenType;

/**
 * This enum represents each of the tokens which can be part of the program.
 */
public enum PlpTokenType implements AsmTokenType {

    /**
     * This token identifies all the label definitions
     * Example  main:
     */
    LABELDEFINITION("\\b[a-zA-Z][a-zA-Z0-9_]*[a-zA-Z0-9]:\\B"),

    /**
     *  This tells what can be a possible instruction token, whether this instruction
     *  actually exists is validated later by a different module/function
     *  So this will include pseudo instruction as well as actual instructions
     *  Example addu
     */
    INSTRUCTION("(?i)\\b(addu|addiu|subu|mullo|mulhi|lui|and|andi|or|ori|" +
            "nor|slt|slti|sltu|sltiu|sll|sllv|srl|srlv|beq|bne|j|jal|jr|jalr|" +
            "lw|sw)\\b"),

    /**
     * This tells what are the possible peudo instruction tokens.
     * Example nop, b, move
     */
    PSEUDOINSTRUCTION("(?i)\\b(nop|b|move|push|pop|li|call|return|save|restore|" +
            "lwm|swm)\\b"),

    /**
     * This token represents whenever label is used inside the instruction
     * example branch destination
     * Example MAIN
     */
    LABELUSAGE("\\b[a-zA-Z][a-zA-Z0-9_]*[a-zA-Z0-9]\\b"),

    /**
     * Possible register patterns, this can pick some registers which is not
     * defined at in the Plp yet
     * Example $t1 $0
     */
     REGISTER("\\$([a-zA-Z]|[0-9])+"),

    /**
     * This represents the comments of Plp program
     * Example # this is a comment
     */
    COMMENT("#.*$"),

    /**
     * Usually instruction arguments are separated by `,`
     * Example addu $t1, $t2
     */
    COMMA(","),

    /**
     * For instructions like load and store words we use register with a number
     * Example lw $t0, 8($t1)
     */
    PARENTHESISREGISTER("\\(\\$([a-zA-Z]|[0-9])+\\)"),

    /**
     * This represents any assembler directives
     * Example .org .word .asciiz
     */
    DIRECTIVE("\\B\\.([a-zA-Z]+)\\b"),


    /**
     * This represents the string literal usually used with .ascii directive
     * Example "this is an example string"
     */
    STRING("\"(.*?)\""),

    /**
     * This represents ASCII characters which can be used to store the ASCII value
     */
    CHARACTER("'[\\u0000-\\u007F]'"),

    /**
     * We can have decimal, hexadecimal and binary number representation
     * Example 34543 -45 0xFEED 0b010101
     */
    NUMERIC("((-?[1-9]\\d*)|0+|0x([0-9a-fA-F]+)|0b([01]+))\\b");


    private final String regex;

    private PlpTokenType(String regex) {
        this.regex = regex;
    }

    /**
     * The regex representation of the given token
     *
     * @return regex value of the AsmTokenType
     */
    @Override
    public String regex() {
        return regex;
    }
}
