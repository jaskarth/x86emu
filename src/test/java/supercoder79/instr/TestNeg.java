package supercoder79.instr;

import org.junit.jupiter.api.Test;
import supercoder79.x86emu.instr.Neg;
import supercoder79.x86emu.simulate.register.Register;
import supercoder79.x86emu.simulate.register.RegisterSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static supercoder79.x86emu.simulate.ValueType.*;

public class TestNeg {
    @Test
    public void neg_r64() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();

        rax.set(4);

        new Neg(set, rax, r64).execute();

        assertEquals(-4L, rax.v64());
    }

    @Test
    public void neg_r32() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();

        rax.set(4);

        new Neg(set, rax, r32).execute();

        assertEquals(-4, rax.v32());
    }

    @Test
    public void neg_r16() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();

        rax.set((short) 4);

        new Neg(set, rax, r16).execute();

        assertEquals((short) -4, rax.v16());
    }

    @Test
    public void neg_r8() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();

        rax.set((byte) 4);

        new Neg(set, rax, r8).execute();

        assertEquals((byte) -4, rax.v8());
    }
}
