package org.plp.implementations.plpisa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.plp.implementations.plpisa.argument.PlpNumericLiteralArgument;

public class PlpNumericLiteralArgumentTest {
    @Test
    public void testNumericLiteralArgumentEncoding() {
        PlpNumericLiteralArgument argument = new PlpNumericLiteralArgument("123456");
        Assertions.assertEquals(123456L, argument.encode());
        Assertions.assertEquals("123456", argument.raw());
        Assertions.assertEquals(PlpArgumentType.NUMERIC, argument.getType());
    }

    @Test
    public void testBinaryArgumentEncoding() {
        PlpNumericLiteralArgument argument = new PlpNumericLiteralArgument("b111");
        Assertions.assertEquals(7L, argument.encode());
        Assertions.assertEquals("b111", argument.raw());
    }

    @Test
    public void testHexaArgumentEncoding() {
        PlpNumericLiteralArgument argument = new PlpNumericLiteralArgument("0x123afd");
        Assertions.assertEquals(1194749L, argument.encode());
        Assertions.assertEquals("0x123afd", argument.raw());
    }
}
