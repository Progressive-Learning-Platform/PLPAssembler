package org.plp.implementations.plpisa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.plp.implementations.plpisa.argument.PlpRegisterArgument;

public class PlpRegisterArgumentTest {
    @Test
    public void testRegisterArgumentEncoding() {
        PlpRegisterArgument argument = new PlpRegisterArgument("$t1");
        Assertions.assertEquals(9L, argument.encode());
        Assertions.assertEquals("$t1", argument.raw());
        Assertions.assertEquals(PlpArgumentType.REGISTER, argument.getType());
    }

    @Test
    public void testZeroRegisterArgumentEncoding() {
        PlpRegisterArgument argument = new PlpRegisterArgument("$zero");
        Assertions.assertEquals(0L, argument.encode());
    }
}
