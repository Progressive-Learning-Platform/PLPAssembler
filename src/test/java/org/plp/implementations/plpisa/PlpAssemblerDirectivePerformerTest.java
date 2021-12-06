package org.plp.implementations.plpisa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.plp.implementations.plpisa.argument.PlpNumericLiteralArgument;
import org.plp.implementations.plpisa.assemblerdirective.OrgAssemblerDirectiveAssembler;
import org.plp.implementations.plpisa.assemblerdirective.PlpAssemblerDirectivePerformer;
import org.plp.isa.AsmArgument;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlpAssemblerDirectivePerformerTest {
    @Mock PlpCurrentAddressManager addressManager;
    @Mock OrgAssemblerDirectiveAssembler mockOrgAssemblerDirectiveAssembler;

    @Test
    void testOrgDirectiveExists() {
        PlpAssemblerDirectivePerformer performer = new PlpAssemblerDirectivePerformer(addressManager);

        Assertions.assertTrue(performer.isAssemblerDirectiveExists(".org"));
    }

    @Test
    void testAddingDirective() {
        PlpAssemblerDirectivePerformer performer = new PlpAssemblerDirectivePerformer(addressManager);
        OrgAssemblerDirectiveAssembler testAssembler = new OrgAssemblerDirectiveAssembler(addressManager);

        Assertions.assertFalse(performer.isAssemblerDirectiveExists(".test"));
        performer.addDirectiveAssembler(".test", testAssembler);
        Assertions.assertTrue(performer.isAssemblerDirectiveExists(".test"));
    }

    @Test
    void testPerformDirectiveAssembler() {
        PlpAssemblerDirectivePerformer performer = new PlpAssemblerDirectivePerformer(addressManager);
        List<AsmArgument> arguments = new ArrayList<>();
        PlpNumericLiteralArgument argument = new PlpNumericLiteralArgument("0x10000");
        arguments.add(argument);

        when(mockOrgAssemblerDirectiveAssembler.perform(arguments)).thenReturn("TESTING_SUCCESS");
        performer.addDirectiveAssembler(".org", mockOrgAssemblerDirectiveAssembler);

        String returnValue = performer.perform(".org", arguments);
        Assertions.assertEquals("TESTING_SUCCESS", returnValue);
        verify(mockOrgAssemblerDirectiveAssembler, times(1)).perform(arguments);
    }

}
