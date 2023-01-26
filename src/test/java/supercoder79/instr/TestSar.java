package supercoder79.instr;

import org.junit.jupiter.api.Test;
import supercoder79.x86emu.instr.Sar;
import supercoder79.x86emu.simulate.Immediate;
import supercoder79.x86emu.simulate.register.Register;
import supercoder79.x86emu.simulate.register.RegisterSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static supercoder79.x86emu.simulate.ValueType.*;

public class TestSar {
    @Test
    public void sar_imm8_r64() {
        RegisterSet set = new RegisterSet();
        Register rbx = set.rbx();

        rbx.set(4L);

        new Sar(set, new Immediate((byte) 2), rbx, r8, r64).execute();

        assertEquals(1L, rbx.v64());
    }

    @Test
    public void sar_imm8_r32() {
        RegisterSet set = new RegisterSet();
        Register rbx = set.rbx();

        rbx.set(4);

        new Sar(set, new Immediate((byte) 2), rbx, r8, r32).execute();

        assertEquals(1L, rbx.v32());
    }

    @Test
    public void sar_imm8_r16() {
        RegisterSet set = new RegisterSet();
        Register rbx = set.rbx();

        rbx.set((short) 4);

        new Sar(set, new Immediate((byte) 2), rbx, r8, r16).execute();

        assertEquals(1L, rbx.v16());
    }

    @Test
    public void sar_imm8_r64_n() {
        RegisterSet set = new RegisterSet();
        Register rbx = set.rbx();

        rbx.set(-4L);

        new Sar(set, new Immediate((byte) 2), rbx, r8, r64).execute();

        assertEquals(-1L, rbx.v64());
    }

    @Test
    public void sar_imm8_r32_n() {
        RegisterSet set = new RegisterSet();
        Register rbx = set.rbx();

        rbx.set(-4);

        new Sar(set, new Immediate((byte) 2), rbx, r8, r32).execute();

        assertEquals(-1, rbx.v32());
    }

    @Test
    public void sar_imm8_r16_n() {
        RegisterSet set = new RegisterSet();
        Register rbx = set.rbx();

        rbx.set((short) -4);

        new Sar(set, new Immediate((byte) 2), rbx, r8, r16).execute();

        assertEquals((short) -1, rbx.v16());
    }

    @Test
    public void sar_imm8_r8_n() {
        RegisterSet set = new RegisterSet();
        Register rbx = set.rbx();

        rbx.set((byte) -4);

        new Sar(set, new Immediate((byte) 2), rbx, r8, r8).execute();

        assertEquals((byte) -1, rbx.v8());
    }
}
