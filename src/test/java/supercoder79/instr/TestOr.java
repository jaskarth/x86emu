package supercoder79.instr;

import org.junit.jupiter.api.Test;
import supercoder79.x86emu.instr.Or;
import supercoder79.x86emu.simulate.Immediate;
import supercoder79.x86emu.simulate.register.Register;
import supercoder79.x86emu.simulate.register.RegisterSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static supercoder79.x86emu.simulate.ValueType.*;

public class TestOr {
    @Test
    public void and_r64_r64() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();
        Register rbx = set.rbx();

        rax.set(2L);
        rbx.set(13L);

        new Or(set, rax, rbx, r64, r64).execute();

        assertEquals(15L, rbx.v64());
    }

    @Test
    public void and_r64_imm32() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();

        rax.set(13);

        new Or(set, new Immediate(2), rax, r32, r64).execute();

        assertEquals(15L, rax.v64());
    }

    @Test
    public void and_r64_imm8() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();

        rax.set(13);

        new Or(set, new Immediate((byte) 2), rax, r8, r64).execute();

        assertEquals(15L, rax.v64());
    }

    @Test
    public void and_r32_r32() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();
        Register rbx = set.rbx();

        rax.set(2);
        rbx.set(13);

        new Or(set, rax, rbx, r32, r32).execute();

        assertEquals(15, rbx.v32());
    }

    @Test
    public void and_r32_imm32() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();

        rax.set(13);

        new Or(set, new Immediate(2), rax, r32, r32).execute();

        assertEquals(15, rax.v32());
    }

    @Test
    public void and_r32_imm8() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();

        rax.set(13);

        new Or(set, new Immediate((byte) 2), rax, r8, r32).execute();

        assertEquals(15, rax.v32());
    }

    @Test
    public void and_r16_r16() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();
        Register rbx = set.rbx();

        rax.set((short) 2);
        rbx.set((short) 13);

        new Or(set, rax, rbx, r16, r16).execute();

        assertEquals((short) 15, rbx.v16());
    }

    @Test
    public void and_r16_imm16() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();

        rax.set((short) 13);

        new Or(set, new Immediate((short) 2), rax, r16, r16).execute();

        assertEquals((short) 15, rax.v16());
    }

    @Test
    public void and_r16_imm8() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();

        rax.set((short) 13);

        new Or(set, new Immediate((byte) 2), rax, r8, r16).execute();

        assertEquals((short) 15, rax.v16());
    }

    @Test
    public void and_r8_r8() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();
        Register rbx = set.rbx();

        rax.set((byte) 2);
        rbx.set((byte) 13);

        new Or(set, rax, rbx, r8, r8).execute();

        assertEquals((byte) 15, rbx.v8());
    }
}