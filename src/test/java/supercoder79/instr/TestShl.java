package supercoder79.instr;

import org.junit.jupiter.api.Test;
import supercoder79.x86emu.instr.And;
import supercoder79.x86emu.instr.Shl;
import supercoder79.x86emu.simulate.Immediate;
import supercoder79.x86emu.simulate.register.Register;
import supercoder79.x86emu.simulate.register.RegisterSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static supercoder79.x86emu.simulate.ValueType.*;

public class TestShl {
    @Test
    public void shl_imm8_r64() {
        RegisterSet set = new RegisterSet();
        Register rbx = set.rbx();

        rbx.set(1L);

        new Shl(set, new Immediate((byte) 2), rbx, r8, r64).execute();

        assertEquals(4L, rbx.v64());
    }

    @Test
    public void shl_imm8_r32() {
        RegisterSet set = new RegisterSet();
        Register rbx = set.rbx();

        rbx.set(1);

        new Shl(set, new Immediate((byte) 2), rbx, r8, r32).execute();

        assertEquals(4, rbx.v32());
    }

    @Test
    public void shl_imm8_r16() {
        RegisterSet set = new RegisterSet();
        Register rbx = set.rbx();

        rbx.set((short) 1);

        new Shl(set, new Immediate((byte) 2), rbx, r8, r16).execute();

        assertEquals(4, rbx.v16());
    }
}
