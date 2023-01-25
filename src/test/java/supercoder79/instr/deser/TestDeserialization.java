package supercoder79.instr.deser;

import org.junit.jupiter.api.Test;
import supercoder79.x86emu.instr.Add;
import supercoder79.x86emu.simulate.Immediate;
import supercoder79.x86emu.simulate.register.RegisterSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static supercoder79.x86emu.simulate.ValueType.*;

public class TestDeserialization {
    @Test
    public void testAdd() {
        RegisterSet set = new RegisterSet();

        assertEquals("add %rax, %rbx", new Add(set, set.rax(), set.rbx(), r64, r64).assemble());
    }

    @Test
    public void testAddCon() {
        RegisterSet set = new RegisterSet();

        assertEquals("add $40, %rbx", new Add(set, new Immediate(40L), set.rbx(), r64, r64).assemble());
    }
}
