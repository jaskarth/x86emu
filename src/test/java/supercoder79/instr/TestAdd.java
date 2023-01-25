package supercoder79.instr;

import org.junit.jupiter.api.Test;
import supercoder79.x86emu.instr.Add;
import supercoder79.x86emu.simulate.Immediate;
import supercoder79.x86emu.simulate.memory.HeapMemory;
import supercoder79.x86emu.simulate.memory.MemSlice;
import supercoder79.x86emu.simulate.register.Register;
import supercoder79.x86emu.simulate.register.RegisterSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static supercoder79.x86emu.simulate.ValueType.*;

public class TestAdd {
    //<editor-fold desc="Tests making sure that the addition works">
    @Test
    public void add_r64_r64() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();
        Register rbx = set.rbx();

        rax.set(10L);
        rbx.set(20L);

        new Add(set, rax, rbx, r64, r64).execute();

        assertEquals(30L, rbx.v64());
    }

    @Test
    public void add_r32_r32() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();
        Register rbx = set.rbx();

        rax.set(10);
        rbx.set(20);

        new Add(set, rax, rbx, r32, r32).execute();

        assertEquals(30, rbx.v32());
    }

    @Test
    public void add_r16_r16() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();
        Register rbx = set.rbx();

        rax.set((short) 10);
        rbx.set((short) 20);

        new Add(set, rax, rbx, r16, r16).execute();

        assertEquals((short) 30, rbx.v16());
    }

    @Test
    public void add_r8_r8() {
        RegisterSet set = new RegisterSet();
        Register rax = set.rax();
        Register rbx = set.rbx();

        rax.set((byte) 10);
        rbx.set((byte) 20);

        new Add(set, rax, rbx, r8, r8).execute();

        assertEquals((byte) 30, rbx.v8());
    }

    @Test
    public void add_r64_imm32() {
        RegisterSet set = new RegisterSet();
        Register rbx = set.rbx();

        Immediate imm32 = new Immediate(10);
        rbx.set(20L);

        new Add(set, imm32, rbx, r32, r64).execute();

        assertEquals(30L, rbx.v64());
    }

    @Test
    public void add_r32_imm32() {
        RegisterSet set = new RegisterSet();
        Register rbx = set.rbx();

        Immediate imm32 = new Immediate(10);
        rbx.set(20);

        new Add(set, imm32, rbx, r32, r32).execute();

        assertEquals(30, rbx.v32());
    }

    @Test
    public void add_r16_imm16() {
        RegisterSet set = new RegisterSet();
        Register rbx = set.rbx();

        Immediate imm16 = new Immediate((short) 10);
        rbx.set((short) 20);

        new Add(set, imm16, rbx, r16, r16).execute();

        assertEquals((short) 30, rbx.v16());
    }

    @Test
    public void add_r8_imm8() {
        RegisterSet set = new RegisterSet();
        Register rbx = set.rbx();

        Immediate imm8 = new Immediate((byte) 10);
        rbx.set((byte) 20);

        new Add(set, imm8, rbx, r8, r8).execute();

        assertEquals((byte) 30, rbx.v8());
    }

    @Test
    public void add_m64_r64() {
        RegisterSet set = new RegisterSet();
        HeapMemory memory = new HeapMemory();
        MemSlice slice = new MemSlice(memory, 0);
        slice.set(10L);

        Register rbx = set.rbx();
        rbx.set(20L);

        new Add(set, slice, rbx, r64, r64).execute();

        assertEquals(30L, rbx.v64());
    }

    @Test
    public void add_r64_m64() {
        RegisterSet set = new RegisterSet();
        HeapMemory memory = new HeapMemory();
        MemSlice slice = new MemSlice(memory, 0);

        Register rax = set.rax();
        rax.set(10L);

        slice.set(20L);

        new Add(set, rax, slice, r64, r64).execute();

        assertEquals(30L, slice.v64());
    }
    //</editor-fold>


}
