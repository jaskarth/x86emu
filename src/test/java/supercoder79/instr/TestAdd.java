package supercoder79.instr;

import org.junit.jupiter.api.Test;
import supercoder79.x86emu.instr.Add;
import supercoder79.x86emu.simulate.register.Register;
import supercoder79.x86emu.simulate.register.RegisterSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static supercoder79.x86emu.simulate.ValueType.*;

public class TestAdd {
    @Test
    public void add_r64_r64() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();
        Register rbx = set.rbx();

        rax.set(10L);
        rbx.set(20L);

        new Add(set, r64, r64).execute(set, rax, rbx);

        assertEquals(30L, rbx.v64());
    }

    @Test
    public void add_r32_r32() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();
        Register rbx = set.rbx();

        rax.set(10);
        rbx.set(20);

        new Add(set, r32, r32).execute(set, rax, rbx);

        assertEquals(30, rbx.v32());
    }
}
