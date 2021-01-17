package org.plp.implementations.plpisa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

public class PlpTokenTest {

    @Test
    public void getTokenTypeOfTokenTest() {
        PlpToken plpToken = new PlpToken(PlpTokenType.DIRECTIVE, ".org");
        Assertions.assertEquals(PlpTokenType.DIRECTIVE, plpToken.getTokenType());
    }

    @Test
    public void getActualValueOfToken() {
        PlpToken plpToken = new PlpToken(PlpTokenType.NUMERIC, "0x100000");
        Assertions.assertEquals("0x100000", plpToken.getValue());
    }

    @Test
    public void initiateTokenWithNullTokenTypeTest() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new PlpToken(null, "randomValue");
        });

    }

    @Test
    public void initiateTokenWithNullValueTest() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new PlpToken(PlpTokenType.NUMERIC, null);
        });

    }
}
