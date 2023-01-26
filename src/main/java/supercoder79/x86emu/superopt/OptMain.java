package supercoder79.x86emu.superopt;

import supercoder79.x86emu.parse.InstrList;
import supercoder79.x86emu.parse.InstrParse;
import supercoder79.x86emu.parse.Parse;
import supercoder79.x86emu.simulate.register.RegisterSet;

public class OptMain {
    public static void main(String[] args) {
        String asm =
        """
        sar $4, %eax
        shl $4, %eax
        """;

        RegisterSet set = new RegisterSet();
        InstrList list = Parse.parse(set, asm);
        set.rax().set(255);
        Opt.trySuperOpt(set, list);
    }
}
