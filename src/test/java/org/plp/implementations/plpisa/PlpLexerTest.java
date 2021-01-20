package org.plp.implementations.plpisa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.plp.isa.AsmToken;
import org.plp.isa.exceptions.AsmIllegalTokenException;

import java.util.List;

public class PlpLexerTest {

    @Test
    public void testSuccessFullParsing() {
        String input = ".org 0x100000";

        PlpLexer lexer = new PlpLexer();
        List<AsmToken> tokens = lexer.lex(input);

        Assertions.assertEquals(2, tokens.size());
        Assertions.assertEquals(PlpTokenType.DIRECTIVE.name(), tokens.get(0).getTokenType().toString());
        Assertions.assertEquals(".org", tokens.get(0).getValue());
        Assertions.assertEquals(PlpTokenType.NUMERIC.name(), tokens.get(1).getTokenType().toString());
        Assertions.assertEquals("0x100000", tokens.get(1).getValue());
    }

    @Test
    public void testCommentParsing() {
        String input = "# This is just a comment; .org 0x10000 addu $t0, $a1";

        PlpLexer lexer = new PlpLexer();
        List<AsmToken> tokens = lexer.lex(input);

        Assertions.assertEquals(1, tokens.size());
        Assertions.assertEquals(PlpTokenType.COMMENT.name(), tokens.get(0).getTokenType().toString());
        Assertions.assertEquals(input, tokens.get(0).getValue());
    };

    @Test
    public void testInstructionParsing() {
        String input = "addu $v0, $a0, $a1";

        PlpLexer lexer = new PlpLexer();
        List<AsmToken> tokens = lexer.lex(input);

        Assertions.assertEquals(6, tokens.size());
        Assertions.assertEquals(PlpTokenType.INSTRUCTION.name(), tokens.get(0).getTokenType().toString());
        Assertions.assertEquals("addu", tokens.get(0).getValue());
        Assertions.assertEquals(PlpTokenType.REGISTER.name(), tokens.get(1).getTokenType().toString());
        Assertions.assertEquals("$v0", tokens.get(1).getValue());
        Assertions.assertEquals(PlpTokenType.COMMA.name(), tokens.get(2).getTokenType().toString());
        Assertions.assertEquals(",", tokens.get(2).getValue());
        Assertions.assertEquals(PlpTokenType.REGISTER.name(), tokens.get(3).getTokenType().toString());
        Assertions.assertEquals("$a0", tokens.get(3).getValue());
        Assertions.assertEquals(PlpTokenType.COMMA.name(), tokens.get(4).getTokenType().toString());
        Assertions.assertEquals(",", tokens.get(4).getValue());
        Assertions.assertEquals(PlpTokenType.REGISTER.name(), tokens.get(5).getTokenType().toString());
        Assertions.assertEquals("$a1", tokens.get(5).getValue());
    }

    @Test
    public void testInstructionWithParameterRegister() {
        String input = "lw $t0, 8($t1)";

        PlpLexer lexer = new PlpLexer();
        List<AsmToken> tokens = lexer.lex(input);

        Assertions.assertEquals(5, tokens.size());
        Assertions.assertEquals(PlpTokenType.INSTRUCTION.name(), tokens.get(0).getTokenType().toString());
        Assertions.assertEquals("lw", tokens.get(0).getValue());
        Assertions.assertEquals(PlpTokenType.REGISTER.name(), tokens.get(1).getTokenType().toString());
        Assertions.assertEquals("$t0", tokens.get(1).getValue());
        Assertions.assertEquals(PlpTokenType.COMMA.name(), tokens.get(2).getTokenType().toString());
        Assertions.assertEquals(",", tokens.get(2).getValue());
        Assertions.assertEquals(PlpTokenType.NUMERIC.name(), tokens.get(3).getTokenType().toString());
        Assertions.assertEquals("8", tokens.get(3).getValue());
        Assertions.assertEquals(PlpTokenType.PARENTHESISREGISTER.name(), tokens.get(4).getTokenType().toString());
        Assertions.assertEquals("($t1)", tokens.get(4).getValue());

    }

    @Test
    public void testLabelDeclarationParsing() {
        String input = "test_label: #This is a label test";

        PlpLexer lexer = new PlpLexer();
        List<AsmToken> tokens = lexer.lex(input);

        Assertions.assertEquals(2, tokens.size());
        Assertions.assertEquals(PlpTokenType.LABELDEFINITION.name(), tokens.get(0).getTokenType().toString());
        Assertions.assertEquals("test_label:", tokens.get(0).getValue());
        Assertions.assertEquals(PlpTokenType.COMMENT.name(), tokens.get(1).getTokenType().toString());
        Assertions.assertEquals("#This is a label test", tokens.get(1).getValue());
    }

    @Test
    public void testLexingOfLabelUsage() {
        String input = "beq $a0, $a1, done";

        PlpLexer lexer = new PlpLexer();
        List<AsmToken> tokens = lexer.lex(input);

        Assertions.assertEquals(6, tokens.size());
        Assertions.assertEquals(PlpTokenType.INSTRUCTION.name(), tokens.get(0).getTokenType().toString());
        Assertions.assertEquals("beq", tokens.get(0).getValue());
        Assertions.assertEquals(PlpTokenType.REGISTER.name(), tokens.get(1).getTokenType().toString());
        Assertions.assertEquals("$a0", tokens.get(1).getValue());
        Assertions.assertEquals(PlpTokenType.COMMA.name(), tokens.get(2).getTokenType().toString());
        Assertions.assertEquals(",", tokens.get(2).getValue());
        Assertions.assertEquals(PlpTokenType.REGISTER.name(), tokens.get(3).getTokenType().toString());
        Assertions.assertEquals("$a1", tokens.get(3).getValue());
        Assertions.assertEquals(PlpTokenType.COMMA.name(), tokens.get(4).getTokenType().toString());
        Assertions.assertEquals(",", tokens.get(4).getValue());
        Assertions.assertEquals(PlpTokenType.LABELUSAGE.name(), tokens.get(5).getTokenType().toString());
        Assertions.assertEquals("done", tokens.get(5).getValue());

    }

    @Test
    public void testStringLexToken() {
        String input = ".ascii \"example string\"";

        PlpLexer lexer = new PlpLexer();
        List<AsmToken> tokens = lexer.lex(input);

        Assertions.assertEquals(2, tokens.size());
        Assertions.assertEquals(PlpTokenType.DIRECTIVE.name(), tokens.get(0).getTokenType().toString());
        Assertions.assertEquals(".ascii", tokens.get(0).getValue());
        Assertions.assertEquals(PlpTokenType.STRING.name(), tokens.get(1).getTokenType().toString());
        Assertions.assertEquals("\"example string\"", tokens.get(1).getValue());

    }

    @Test
    public void testCharacterToken() {
        String input = "ADDIU $V0, $A0, 'A'";

        PlpLexer lexer = new PlpLexer();
        List<AsmToken> tokens = lexer.lex(input);

        Assertions.assertEquals(6, tokens.size());
        Assertions.assertEquals(PlpTokenType.INSTRUCTION.name(), tokens.get(0).getTokenType().toString());
        Assertions.assertEquals("ADDIU", tokens.get(0).getValue());
        Assertions.assertEquals(PlpTokenType.REGISTER.name(), tokens.get(1).getTokenType().toString());
        Assertions.assertEquals("$V0", tokens.get(1).getValue());
        Assertions.assertEquals(PlpTokenType.COMMA.name(), tokens.get(2).getTokenType().toString());
        Assertions.assertEquals(",", tokens.get(2).getValue());
        Assertions.assertEquals(PlpTokenType.REGISTER.name(), tokens.get(3).getTokenType().toString());
        Assertions.assertEquals("$A0", tokens.get(3).getValue());
        Assertions.assertEquals(PlpTokenType.COMMA.name(), tokens.get(4).getTokenType().toString());
        Assertions.assertEquals(",", tokens.get(4).getValue());
        Assertions.assertEquals(PlpTokenType.CHARACTER.name(), tokens.get(5).getTokenType().toString());
        Assertions.assertEquals("'A'", tokens.get(5).getValue());

    }

    @Test
    public void testPseudoInstructionLex() {
        String input = "li $v0, error";

        PlpLexer lexer = new PlpLexer();
        List<AsmToken> tokens = lexer.lex(input);

        Assertions.assertEquals(4, tokens.size());
        Assertions.assertEquals(PlpTokenType.PSEUDOINSTRUCTION.name(), tokens.get(0).getTokenType().toString());
        Assertions.assertEquals("li", tokens.get(0).getValue());
        Assertions.assertEquals(PlpTokenType.REGISTER.name(), tokens.get(1).getTokenType().toString());
        Assertions.assertEquals("$v0", tokens.get(1).getValue());
        Assertions.assertEquals(PlpTokenType.COMMA.name(), tokens.get(2).getTokenType().toString());
        Assertions.assertEquals(",", tokens.get(2).getValue());
        Assertions.assertEquals(PlpTokenType.LABELUSAGE.name(), tokens.get(3).getTokenType().toString());
        Assertions.assertEquals("error", tokens.get(3).getValue());
    }

    @Test
    public void testInvlaidTokenLex() {
        String input = "*234 $v0";

        PlpLexer lexer = new PlpLexer();

        Assertions.assertThrows(AsmIllegalTokenException.class,
                () -> lexer.lex(input),
                "ERROR! Invalid Character Found: *234 $v0");

    }

    @Test
    public void testInvlaidTokenInTheMiddleLex() {
        String input = "li 23@4 mylabel";

        PlpLexer lexer = new PlpLexer();

        Assertions.assertThrows(AsmIllegalTokenException.class,
                () -> lexer.lex(input),
                "ERROR! Invalid Character Found: 23@4 mylabel");

    }

    @Test
    public void testInvlaidTokenAtTheEndLex() {
        String input = "b testbranch(";

        PlpLexer lexer = new PlpLexer();

        Assertions.assertThrows(AsmIllegalTokenException.class,
                () -> lexer.lex(input),
                "ERROR! Invalid Character Found: (");

    }
}
