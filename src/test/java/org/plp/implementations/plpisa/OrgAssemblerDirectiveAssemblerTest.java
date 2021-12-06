package org.plp.implementations.plpisa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.plp.implementations.plpisa.argument.PlpNumericLiteralArgument;
import org.plp.implementations.plpisa.argument.PlpStringLiteralArgument;
import org.plp.implementations.plpisa.assemblerdirective.OrgAssemblerDirectiveAssembler;
import org.plp.isa.exceptions.AsmAssemblerException;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrgAssemblerDirectiveAssemblerTest {
    @Mock
    PlpCurrentAddressManager mockAddressManager;

    OrgAssemblerDirectiveAssembler orgAssemblerDirectiveAssembler;

    @BeforeEach
    public void setup() {
        orgAssemblerDirectiveAssembler = new OrgAssemblerDirectiveAssembler(mockAddressManager);
    }

    @Test
    public void testOrgAssemblerDirectiveAssembler() {
        Assertions.assertEquals(1, orgAssemblerDirectiveAssembler.getArgumentTypesOfAssembler().size());
        Assertions.assertEquals(PlpArgumentType.NUMERIC, orgAssemblerDirectiveAssembler.getArgumentTypesOfAssembler().get(0));

        PlpNumericLiteralArgument number = new PlpNumericLiteralArgument("0x10000");
        Assertions.assertEquals("__ASM_ORG__", orgAssemblerDirectiveAssembler.perform(Arrays.asList(number)));
        verify(mockAddressManager).setCurrentAddress(0x10000L);
    }

    @Test
    public void testOrgAssemblerDirectiveArgumentError() {
        PlpStringLiteralArgument argument1 = new PlpStringLiteralArgument("this is test");
        PlpNumericLiteralArgument argument2 = new PlpNumericLiteralArgument("0x10000");
        Assertions.assertThrows(AsmAssemblerException.class, () -> orgAssemblerDirectiveAssembler.perform(Arrays.asList(argument1)));
        Assertions.assertThrows(AsmAssemblerException.class, () -> orgAssemblerDirectiveAssembler.perform(Arrays.asList(argument2, argument1)));
        Assertions.assertThrows(AsmAssemblerException.class, () -> orgAssemblerDirectiveAssembler.perform(new ArrayList<>()));

    }

}
