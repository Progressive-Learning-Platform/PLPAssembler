package org.plp.implementations.plpisa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.plp.implementations.plpisa.argument.PlpCharLiteralArgument;

public class PlpCharLiteralArgumentTest {

    @Test
    public void testCharArgumentEncoding() {
        PlpCharLiteralArgument argument = new PlpCharLiteralArgument("'C'");
        Assertions.assertEquals(67L, argument.encode());
        Assertions.assertEquals("'C'", argument.raw());
        Assertions.assertEquals(PlpArgumentType.CHAR, argument.getType());
    }
}
