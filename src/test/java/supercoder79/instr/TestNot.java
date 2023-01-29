package supercoder79.instr;

import org.junit.jupiter.api.Test;
import supercoder79.x86emu.instr.Not;
import supercoder79.x86emu.simulate.register.Register;
import supercoder79.x86emu.simulate.register.RegisterSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static supercoder79.x86emu.simulate.ValueType.*;

public class TestNot {
    @Test
    public void not_r64_1() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();

        rax.set(0);

        new Not(set, rax, r64).execute();

        assertEquals(-1L, rax.v64());
    }

    @Test
    public void not_r64_2() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();

        rax.set(1);

        new Not(set, rax, r64).execute();

        assertEquals(-2L, rax.v64());
    }

    @Test
    public void not_r32() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();

        rax.set(0);

        new Not(set, rax, r32).execute();

        assertEquals(-1, rax.v32());
    }

    @Test
    public void not_r16() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();

        rax.set((short) 0);

        new Not(set, rax, r16).execute();

        assertEquals((short) -1, rax.v16());
    }

    @Test
    public void not_r8() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();

        rax.set((byte) 0);

        new Not(set, rax, r8).execute();

        assertEquals((byte) -1, rax.v8());
    }
}
