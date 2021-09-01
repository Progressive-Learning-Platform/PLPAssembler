package org.plp.implementations.plpisa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.plp.implementations.plpisa.argument.PlpMemoryArgument;

public class PlpMemoryArgumentTest {

    @Test
    public void testMemoryArgument() {
        PlpMemoryArgument memoryArgument = new PlpMemoryArgument("45($s1)");
        Assertions.assertEquals(45L, memoryArgument.encode());
        Assertions.assertEquals("$s1", memoryArgument.getRegisterArgumentOfMemoryArg().raw());
        Assertions.assertEquals(19L, memoryArgument.registerEncode());
        Assertions.assertEquals("45($s1)", memoryArgument.raw());
        Assertions.assertEquals("45", memoryArgument.getOffsetArgumentOfMemoryArg().raw());
        Assertions.assertEquals(PlpArgumentType.MEMORY, memoryArgument.getType());
    }
}
