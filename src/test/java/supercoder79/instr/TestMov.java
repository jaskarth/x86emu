package supercoder79.instr;

import org.junit.jupiter.api.Test;
import supercoder79.x86emu.instr.And;
import supercoder79.x86emu.instr.Mov;
import supercoder79.x86emu.simulate.Immediate;
import supercoder79.x86emu.simulate.memory.HeapMemory;
import supercoder79.x86emu.simulate.memory.MemSlice;
import supercoder79.x86emu.simulate.register.Register;
import supercoder79.x86emu.simulate.register.RegisterSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static supercoder79.x86emu.simulate.ValueType.*;

public class TestMov {
    @Test
    public void mov_r64_r64() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();
        Register rbx = set.rbx();

        rax.set(10L);
        rbx.set(20L);

        new Mov(set, rax, rbx, r64, r64).execute();

        assertEquals(10L, rbx.v64());
    }

    @Test
    public void mov_r32_r32() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();
        Register rbx = set.rbx();

        rax.set(10);
        rbx.set(20);

        new Mov(set, rax, rbx, r32, r32).execute();

        assertEquals(10, rbx.v32());
    }

    @Test
    public void mov_r16_r16() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();
        Register rbx = set.rbx();

        rax.set((short) 10);
        rbx.set((short) 20);

        new Mov(set, rax, rbx, r16, r16).execute();

        assertEquals((short) 10, rbx.v16());
    }

    @Test
    public void mov_imm64_r64() {
        RegisterSet set = new RegisterSet();
        Register rbx = set.rbx();

        rbx.set(20L);

        new Mov(set, new Immediate(10L), rbx, r64, r64).execute();

        assertEquals(10L, rbx.v64());
    }

    @Test
    public void mov_imm32_r64() {
        RegisterSet set = new RegisterSet();
        Register rbx = set.rbx();

        rbx.set(20L);

        new Mov(set, new Immediate(10), rbx, r32, r64).execute();

        assertEquals(10, rbx.v32());
    }

    @Test
    public void mov_imm32_r32() {
        RegisterSet set = new RegisterSet();
        Register rbx = set.rbx();

        rbx.set(20);

        new Mov(set, new Immediate(10), rbx, r32, r32).execute();

        assertEquals(10, rbx.v32());
    }

    @Test
    public void mov_imm16_r16() {
        RegisterSet set = new RegisterSet();
        Register rbx = set.rbx();

        rbx.set((short) 20);

        new Mov(set, new Immediate((short) 10), rbx, r16, r16).execute();

        assertEquals((short) 10, rbx.v16());
    }

    @Test
    public void and_m64_r64() {
        RegisterSet set = new RegisterSet();
        HeapMemory memory = new HeapMemory();
        MemSlice slice = memory.slice(0);
        Register rbx = set.rbx();

        slice.set(10L);
        rbx.set(20L);

        new Mov(set, slice, rbx, r64, r64).execute();

        assertEquals(10L, rbx.v64());
    }

    @Test
    public void and_r64_m64() {
        RegisterSet set = new RegisterSet();
        HeapMemory memory = new HeapMemory();
        MemSlice slice = memory.slice(0);
        Register rbx = set.rbx();

        rbx.set(10L);
        slice.set(20L);

        new Mov(set, rbx, slice, r64, r64).execute();

        assertEquals(10L, slice.v64());
    }
}
