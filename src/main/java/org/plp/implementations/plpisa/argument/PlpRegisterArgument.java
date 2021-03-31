package org.plp.implementations.plpisa.argument;

import org.plp.implementations.plpisa.PlpArgumentType;
import org.plp.isa.AsmArgument;
import org.plp.isa.AsmArgumentType;

/**
 * Class representing a Register argument in PLP.
 * Example: $t1
 */
public class PlpRegisterArgument implements AsmArgument {
    private final String argument;

    /**
     * Constructs an {@link AsmArgument} that holds a register
     * @param argument register to be stored
     */
    public PlpRegisterArgument(String argument) {
        this.argument = argument;
    }

    @Override
    public long encode() {
        // strip the $ from the register
        PlpRegister register = PlpRegister.valueOf(argument.substring(1));
        return register.registerNumber;
    }

    @Override
    public String raw() {
        return this.argument;
    }

    @Override
    public AsmArgumentType getType() {
        return PlpArgumentType.REGISTER;
    }

    /**
     * Mappings for the register numbers
     */
    private enum PlpRegister {
        zero(0),
        at(1),
        v0(2),
        v1(3),
        a0(4),
        a1(5),
        a2(6),
        a3(7),
        t0(8),
        t1(9),
        t2(10),
        t3(11),
        t4(12),
        t5(13),
        t6(14),
        t7(15),
        t8(16),
        t9(17),
        s0(18),
        s1(19),
        s2(20),
        s3(21),
        s4(22),
        s5(23),
        s6(24),
        s7(25),
        i0(26),
        i1(27),
        iv(28),
        sp(29),
        ir(30),
        ra(31);

        /**
         * The binary value of the register
         */
        private final int registerNumber;
        PlpRegister(int registerNumber) {
            this.registerNumber = registerNumber;
        }
    }

}

