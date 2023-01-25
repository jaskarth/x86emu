package supercoder79.instr.deser;

import org.junit.jupiter.api.Test;
import supercoder79.x86emu.parse.InstrList;
import supercoder79.x86emu.parse.Parse;
import supercoder79.x86emu.simulate.register.RegisterSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRoundtrip {
    @Test
    public void test1() {
        String asm =
        """
        mov %eax, %ebx
        mov $200, %ecx
        add %ebx, %ecx
        """;

        assertRoundtrip(asm);
    }

    @Test
    public void test2() {
        String asm =
        """
        mov %eax, %ebx
        mov $200, %ecx
        sar $4, %eax
        shl $4, %ecx
        """;

        assertRoundtrip(asm);
    }

    private static void assertRoundtrip(String asm) {
        RegisterSet set = new RegisterSet();
        InstrList list = Parse.parse(set, asm);

        assertEquals(asm, list.assemble());
    }
}
