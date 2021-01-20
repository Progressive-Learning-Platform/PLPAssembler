package org.plp.implementations.plpisa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

public class PlpTokenTypeTest {

    @Test
    void testLabelDefinitionToken() {
        String validLabel1 = "main:";
        String validLabel2 = "test_label:";
        String validLabel3 = "test3_vSDSF976:";
        String validLabel4 = "COMP_BRANCH:";

        String invalidLabel1 = "_asb:";
        String invalidLabel2 = "6789asfsd:";
        String invalidLabel3 = "asdf-asdfs:";
        String invalidLabel4 = "asdf asdfs:";
        String invalidLabel5 = "asdf_asf";

        Pattern labelDefPattern = Pattern.compile(PlpTokenType.LABELDEFINITION.regex());
        Assertions.assertTrue(labelDefPattern.matcher(validLabel1).matches());
        Assertions.assertTrue(labelDefPattern.matcher(validLabel2).matches());
        Assertions.assertTrue(labelDefPattern.matcher(validLabel3).matches());
        Assertions.assertTrue(labelDefPattern.matcher(validLabel4).matches());

        Assertions.assertFalse(labelDefPattern.matcher(invalidLabel1).matches());
        Assertions.assertFalse(labelDefPattern.matcher(invalidLabel2).matches());
        Assertions.assertFalse(labelDefPattern.matcher(invalidLabel3).matches());
        Assertions.assertFalse(labelDefPattern.matcher(invalidLabel4).matches());
        Assertions.assertFalse(labelDefPattern.matcher(invalidLabel5).matches());
    }

    @Test
    void testInstructionToken() {
        Pattern instructionPattern = Pattern.compile(PlpTokenType.INSTRUCTION.regex());

        String validInstruction1 = "addu";
        String validInstruction2 = "Or";
        String validInstruction3 = "LUI";
        String validInstruction4 = "BeQ";
        String validInstruction5 = "j";

        String invalidInstruction1 = ".org";
        String invalidInstruction2 = "$t1";
        String invalidInstruction3 = "0x40";
        String invalidInstruction4 = "test_main:";
        String invalidInstruction5 = "($t2)";
        String invalidInstruction6 = "asdf asdf";
        String invalidInstruction7 = "asdf_asdf";
        String invalidInstruction8 = "as-asdf";
        String invalidInstruction9 = "as89";

        Assertions.assertTrue(instructionPattern.matcher(validInstruction1).matches());
        Assertions.assertTrue(instructionPattern.matcher(validInstruction2).matches());
        Assertions.assertTrue(instructionPattern.matcher(validInstruction3).matches());
        Assertions.assertTrue(instructionPattern.matcher(validInstruction4).matches());
        Assertions.assertTrue(instructionPattern.matcher(validInstruction5).matches());

        Assertions.assertFalse(instructionPattern.matcher(invalidInstruction1).matches());
        Assertions.assertFalse(instructionPattern.matcher(invalidInstruction2).matches());
        Assertions.assertFalse(instructionPattern.matcher(invalidInstruction3).matches());
        Assertions.assertFalse(instructionPattern.matcher(invalidInstruction4).matches());
        Assertions.assertFalse(instructionPattern.matcher(invalidInstruction5).matches());
        Assertions.assertFalse(instructionPattern.matcher(invalidInstruction6).matches());
        Assertions.assertFalse(instructionPattern.matcher(invalidInstruction7).matches());
        Assertions.assertFalse(instructionPattern.matcher(invalidInstruction8).matches());
        Assertions.assertFalse(instructionPattern.matcher(invalidInstruction9).matches());

    }

    @Test
    public void testPseudoInstructionToken() {
        Pattern instructionPattern = Pattern.compile(PlpTokenType.PSEUDOINSTRUCTION.regex());

        String validInstruction1 = "b";
        String validInstruction2 = "MOVE";
        String validInstruction3 = "Pop";
        String validInstruction4 = "push";
        String validInstruction5 = "li";

        String invalidInstruction1 = ".org";
        String invalidInstruction2 = "$t1";
        String invalidInstruction3 = "0x40";
        String invalidInstruction4 = "test_main:";
        String invalidInstruction5 = "($t2)";
        String invalidInstruction6 = "asdf asdf";
        String invalidInstruction7 = "addu";
        String invalidInstruction8 = "as-asdf";
        String invalidInstruction9 = "as89";

        Assertions.assertTrue(instructionPattern.matcher(validInstruction1).matches());
        Assertions.assertTrue(instructionPattern.matcher(validInstruction2).matches());
        Assertions.assertTrue(instructionPattern.matcher(validInstruction3).matches());
        Assertions.assertTrue(instructionPattern.matcher(validInstruction4).matches());
        Assertions.assertTrue(instructionPattern.matcher(validInstruction5).matches());

        Assertions.assertFalse(instructionPattern.matcher(invalidInstruction1).matches());
        Assertions.assertFalse(instructionPattern.matcher(invalidInstruction2).matches());
        Assertions.assertFalse(instructionPattern.matcher(invalidInstruction3).matches());
        Assertions.assertFalse(instructionPattern.matcher(invalidInstruction4).matches());
        Assertions.assertFalse(instructionPattern.matcher(invalidInstruction5).matches());
        Assertions.assertFalse(instructionPattern.matcher(invalidInstruction6).matches());
        Assertions.assertFalse(instructionPattern.matcher(invalidInstruction7).matches());
        Assertions.assertFalse(instructionPattern.matcher(invalidInstruction8).matches());
        Assertions.assertFalse(instructionPattern.matcher(invalidInstruction9).matches());
    }

    @Test
    public void testLabelUsageToken() {
        String validLabel1 = "main";
        String validLabel2 = "test_label";
        String validLabel3 = "test3_vSDSF976";
        String validLabel4 = "COMP_BRANCH";

        String invalidLabel1 = "_asb";
        String invalidLabel2 = "6789asfsd";
        String invalidLabel3 = "asdf-asdfs";
        String invalidLabel4 = "asdf asdfs";
        String invalidLabel6 = "main:";

        Pattern labelUsagePattern = Pattern.compile(PlpTokenType.LABELUSAGE.regex());
        Assertions.assertTrue(labelUsagePattern.matcher(validLabel1).matches());
        Assertions.assertTrue(labelUsagePattern.matcher(validLabel2).matches());
        Assertions.assertTrue(labelUsagePattern.matcher(validLabel3).matches());
        Assertions.assertTrue(labelUsagePattern.matcher(validLabel4).matches());

        Assertions.assertFalse(labelUsagePattern.matcher(invalidLabel1).matches());
        Assertions.assertFalse(labelUsagePattern.matcher(invalidLabel2).matches());
        Assertions.assertFalse(labelUsagePattern.matcher(invalidLabel3).matches());
        Assertions.assertFalse(labelUsagePattern.matcher(invalidLabel4).matches());
        Assertions.assertFalse(labelUsagePattern.matcher(invalidLabel6).matches());
    }

    @Test
    void testRegisterToken() {
        Pattern registerPattern = Pattern.compile(PlpTokenType.REGISTER.regex());

        String validRegister1 = "$t1";
        String validRegister2 = "$zero";
        String validRegister3 = "$0";
        String validRegister4 = "$ir";
        String validRegister5 = "$1";

        String invalidRegister2 = "t1";
        String invalidRegister3 = "abc";
        String invalidRegister4 = ".org";
        String invalidRegister5 = "$org:";

        Assertions.assertTrue(registerPattern.matcher(validRegister1).matches());
        Assertions.assertTrue(registerPattern.matcher(validRegister2).matches());
        Assertions.assertTrue(registerPattern.matcher(validRegister3).matches());
        Assertions.assertTrue(registerPattern.matcher(validRegister4).matches());
        Assertions.assertTrue(registerPattern.matcher(validRegister5).matches());

        Assertions.assertFalse(registerPattern.matcher(invalidRegister2).matches());
        Assertions.assertFalse(registerPattern.matcher(invalidRegister3).matches());
        Assertions.assertFalse(registerPattern.matcher(invalidRegister4).matches());
        Assertions.assertFalse(registerPattern.matcher(invalidRegister5).matches());
    }

    @Test
    public void testCommentToken() {
        Pattern commentPattern = Pattern.compile(PlpTokenType.COMMENT.regex());

        String validComment1 = "# This is a valid Comment$%^&*(";
        String validComment2 = "#";
        String validComment3 = "#This is also valid";
        String validComment4 = "#This is also valid # testing";

        String invalidComment1 = "asdfdsf";
        String invalidComment2 = "asdf#asdf";

        Assertions.assertTrue(commentPattern.matcher(validComment1).matches());
        Assertions.assertTrue(commentPattern.matcher(validComment2).matches());
        Assertions.assertTrue(commentPattern.matcher(validComment3).matches());
        Assertions.assertTrue(commentPattern.matcher(validComment4).matches());

        Assertions.assertFalse(commentPattern.matcher(invalidComment1).matches());
        Assertions.assertFalse(commentPattern.matcher(invalidComment2).matches());
    }

    @Test
    public void testCommaToken() {
        Pattern commaPattern = Pattern.compile(PlpTokenType.COMMA.regex());

        String validComma = ",";

        String invalidComma1 = "asd,asdf";
        String invalidComma2 = "asdsdf";

        Assertions.assertTrue(commaPattern.matcher(validComma).matches());

        Assertions.assertFalse(commaPattern.matcher(invalidComma1).matches());
        Assertions.assertFalse(commaPattern.matcher(invalidComma2).matches());
    }

    @Test
    public void testParenthesisRegisterToken() {
        Pattern parenthesisRegisterPattern = Pattern.compile(PlpTokenType.PARENTHESISREGISTER.regex());

        String validParenthesisRegister1 = "($zero)";
        String validParenthesisRegister2 = "($t1)";
        String validParenthesisRegister3 = "($at)";
        String validParenthesisRegister4 = "($0)";

        String invalidParenthesisRegister1 = "(asdf)";
        String invalidParenthesisRegister2 = "(1)";
        String invalidParenthesisRegister3 = "$t1";
        String invalidParenthesisRegister4 = "($t1";
        String invalidParenthesisRegister5 = "$t1)";
        String invalidParenthesisRegister6 = "()";
        String invalidParenthesisRegister7 = "(.t1)";

        Assertions.assertTrue(parenthesisRegisterPattern.matcher(validParenthesisRegister1).matches());
        Assertions.assertTrue(parenthesisRegisterPattern.matcher(validParenthesisRegister2).matches());
        Assertions.assertTrue(parenthesisRegisterPattern.matcher(validParenthesisRegister3).matches());
        Assertions.assertTrue(parenthesisRegisterPattern.matcher(validParenthesisRegister4).matches());

        Assertions.assertFalse(parenthesisRegisterPattern.matcher(invalidParenthesisRegister1).matches());
        Assertions.assertFalse(parenthesisRegisterPattern.matcher(invalidParenthesisRegister2).matches());
        Assertions.assertFalse(parenthesisRegisterPattern.matcher(invalidParenthesisRegister3).matches());
        Assertions.assertFalse(parenthesisRegisterPattern.matcher(invalidParenthesisRegister4).matches());
        Assertions.assertFalse(parenthesisRegisterPattern.matcher(invalidParenthesisRegister5).matches());
        Assertions.assertFalse(parenthesisRegisterPattern.matcher(invalidParenthesisRegister6).matches());
        Assertions.assertFalse(parenthesisRegisterPattern.matcher(invalidParenthesisRegister7).matches());

    }

    @Test
    public void testDirectiveToken() {
        Pattern directivePattern = Pattern.compile(PlpTokenType.DIRECTIVE.regex());

        String validDirective1 = ".org";
        String validDirective2 = ".asciiz";
        String validDirective3 = ".include";
        String validDirective4 = ".ORG";
        String validDirective5 = ".Org";

        String invalidDirective1 = ".org12";
        String invalidDirective2 = "org";
        String invalidDirective3 = "test.org";

        Assertions.assertTrue(directivePattern.matcher(validDirective1).matches());
        Assertions.assertTrue(directivePattern.matcher(validDirective2).matches());
        Assertions.assertTrue(directivePattern.matcher(validDirective3).matches());
        Assertions.assertTrue(directivePattern.matcher(validDirective4).matches());
        Assertions.assertTrue(directivePattern.matcher(validDirective5).matches());

        Assertions.assertFalse(directivePattern.matcher(invalidDirective1).matches());
        Assertions.assertFalse(directivePattern.matcher(invalidDirective2).matches());
        Assertions.assertFalse(directivePattern.matcher(invalidDirective3).matches());
    }

    @Test
    public void testStringToken() {
        Pattern stringPattern = Pattern.compile(PlpTokenType.STRING.regex());

        String validString1 = "\"This is an example Test - @#$%^&*(asdf\"";
        String validString2 = "\"\"";

        String invalidString1 = "This is an example Test - %^&*(^&*(asfd";
        String invalidString2 = "";
        String invalidString3 = "''";

        Assertions.assertTrue(stringPattern.matcher(validString1).matches());
        Assertions.assertTrue(stringPattern.matcher(validString2).matches());

        Assertions.assertFalse(stringPattern.matcher(invalidString1).matches());
        Assertions.assertFalse(stringPattern.matcher(invalidString2).matches());
        Assertions.assertFalse(stringPattern.matcher(invalidString3).matches());
    }

    @Test
    public void testCharacterToken() {
        Pattern characterPattern = Pattern.compile(PlpTokenType.CHARACTER.regex());

        String validCharacter1 = "'c'";
        String validCharacter2 = "'0'";
        String validCharacter3 = "' '";
        String validCharacter4 = "'A'";
        String validCharacter5 = "'_'";
        String validCharacter6 = "'''";

        String invalidCharacter1 = "c";
        String invalidCharacter2 = "A";
        String invalidCharacter3 = "'ty'";
        String invalidCharacter4 = "'t";
        String invalidCharacter5 = "T'";
        String invalidCharacter6 = "''";
        String invalidCharacter7 = "''''";

        Assertions.assertTrue(characterPattern.matcher(validCharacter1).matches());
        Assertions.assertTrue(characterPattern.matcher(validCharacter2).matches());
        Assertions.assertTrue(characterPattern.matcher(validCharacter3).matches());
        Assertions.assertTrue(characterPattern.matcher(validCharacter4).matches());
        Assertions.assertTrue(characterPattern.matcher(validCharacter5).matches());
        Assertions.assertTrue(characterPattern.matcher(validCharacter6).matches());

        Assertions.assertFalse(characterPattern.matcher(invalidCharacter1).matches());
        Assertions.assertFalse(characterPattern.matcher(invalidCharacter2).matches());
        Assertions.assertFalse(characterPattern.matcher(invalidCharacter3).matches());
        Assertions.assertFalse(characterPattern.matcher(invalidCharacter4).matches());
        Assertions.assertFalse(characterPattern.matcher(invalidCharacter5).matches());
        Assertions.assertFalse(characterPattern.matcher(invalidCharacter6).matches());
        Assertions.assertFalse(characterPattern.matcher(invalidCharacter7).matches());

    }

    @Test
    public void testNumericToken() {
        Pattern numericPattern = Pattern.compile(PlpTokenType.NUMERIC.regex());

        String validNumber1 = "1234";
        String validNumber2 = "-78";
        String validNumber3 = "0x78ad";
        String validNumber4 = "0xfeed";
        String validNumber5 = "0x12546ABCDEF";
        String validNumber6 = "0b010111";
        String validNumber7 = "0";

        String invalidNumber1 = "feed";
        String invalidNumber2 = "1234abd";
        String invalidNumber3 = "0x1232ASD";
        String invalidNumber4 = "0b010012";
        String invalidNumber5 = "-0b0121";
        String invalidNumber6 = "-0x1232aef";
        String invalidNumber7 = "-0";

        Assertions.assertTrue(numericPattern.matcher(validNumber1).matches());
        Assertions.assertTrue(numericPattern.matcher(validNumber2).matches());
        Assertions.assertTrue(numericPattern.matcher(validNumber3).matches());
        Assertions.assertTrue(numericPattern.matcher(validNumber4).matches());
        Assertions.assertTrue(numericPattern.matcher(validNumber5).matches());
        Assertions.assertTrue(numericPattern.matcher(validNumber6).matches());
        Assertions.assertTrue(numericPattern.matcher(validNumber7).matches());

        Assertions.assertFalse(numericPattern.matcher(invalidNumber1).matches());
        Assertions.assertFalse(numericPattern.matcher(invalidNumber2).matches());
        Assertions.assertFalse(numericPattern.matcher(invalidNumber3).matches());
        Assertions.assertFalse(numericPattern.matcher(invalidNumber4).matches());
        Assertions.assertFalse(numericPattern.matcher(invalidNumber5).matches());
        Assertions.assertFalse(numericPattern.matcher(invalidNumber6).matches());
        Assertions.assertFalse(numericPattern.matcher(invalidNumber7).matches());
    }

    @Test
    public void testNewlineToken() {
        Pattern newlinePattern = Pattern.compile(PlpTokenType.NEWLINE.regex());

        String validNewLineToken1 = "\n";
        String validNewLineToken2 = "\t\t";
        String validNewLineToken3 = "\r\f";
        String validNewLineToken4 = "\n\n\t\t\t\r\f";
        String validNewLineToken5 = "  ";

        String invalidNewLineToken1 = "asdf\t\tasf\n";
        String invalidNewLineToken2 = "a\n";

        Assertions.assertTrue(newlinePattern.matcher(validNewLineToken1).matches());
        Assertions.assertTrue(newlinePattern.matcher(validNewLineToken2).matches());
        Assertions.assertTrue(newlinePattern.matcher(validNewLineToken3).matches());
        Assertions.assertTrue(newlinePattern.matcher(validNewLineToken4).matches());
        Assertions.assertTrue(newlinePattern.matcher(validNewLineToken5).matches());

        Assertions.assertFalse(newlinePattern.matcher(invalidNewLineToken1).matches());
        Assertions.assertFalse(newlinePattern.matcher(invalidNewLineToken2).matches());
    }

}
