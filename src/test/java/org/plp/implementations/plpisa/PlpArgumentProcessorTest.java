package org.plp.implementations.plpisa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.plp.implementations.plpisa.argument.*;
import org.plp.implementations.plpisa.exceptions.PlpArgumentException;
import org.plp.isa.AsmArgument;

public class PlpArgumentProcessorTest {
    PlpArgumentProcessor argumentProcessor;

    @BeforeAll
    public void setup() {
        argumentProcessor = new PlpArgumentProcessor();
    }

    @Test
    public void testCharArgumentProcessing() {
        PlpToken plpToken = new PlpToken(PlpTokenType.CHARACTER, "'c'");
        AsmArgument argument = argumentProcessor.getArgument(plpToken);
        Assertions.assertTrue(argument instanceof PlpCharLiteralArgument);
    }

    @Test
    public void testLabelArgumentProcessing() {
        PlpToken plpToken = new PlpToken(PlpTokenType.LABELUSAGE, "test_label");
        AsmArgument argument = argumentProcessor.getArgument(plpToken);
        Assertions.assertTrue(argument instanceof PlpLabelArgument);
    }

    @Test
    public void testNumericArgumentProcessing() {
        PlpToken plpToken = new PlpToken(PlpTokenType.NUMERIC, "0x875adc");
        AsmArgument argument = argumentProcessor.getArgument(plpToken);
        Assertions.assertTrue(argument instanceof PlpNumericLiteralArgument);
    }

    @Test
    public void testStringArgumentProcessing() {
        PlpToken plpToken = new PlpToken(PlpTokenType.STRING, "\"this is a test\"");
        AsmArgument argument = argumentProcessor.getArgument(plpToken);
        Assertions.assertTrue(argument instanceof PlpStringLiteralArgument);
    }

    @Test
    public void testRegisterArgumentProcessing() {
        PlpToken plpToken = new PlpToken(PlpTokenType.REGISTER, "$t1");
        AsmArgument argument = argumentProcessor.getArgument(plpToken);
        Assertions.assertTrue(argument instanceof PlpRegisterArgument);
    }

    @Test
    public void testMemoryLocationArgumentProcessing() {
        PlpToken plpToken = new PlpToken(PlpTokenType.MEMORYLOCATION, "45($t1)");
        AsmArgument argument = argumentProcessor.getArgument(plpToken);
        Assertions.assertTrue(argument instanceof PlpMemoryArgument);
    }

    @Test
    public void testIllegalArgumentExceptionProcessing() {
        PlpToken plpToken = new PlpToken(PlpTokenType.LABELDEFINITION, "test_label:");
        Assertions.assertThrows(PlpArgumentException.class, () -> argumentProcessor.getArgument(plpToken), "Illegal or Unsupported Argument");
    }
}
