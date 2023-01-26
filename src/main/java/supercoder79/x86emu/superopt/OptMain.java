package supercoder79.x86emu.superopt;

import supercoder79.x86emu.parse.InstrList;
import supercoder79.x86emu.parse.InstrParse;
import supercoder79.x86emu.parse.Parse;
import supercoder79.x86emu.simulate.register.RegisterSet;
import supercoder79.x86emu.util.Bits;

import java.util.Random;

public class OptMain {
    public static void main(String[] args) {
        String asm =
        """
        sar $4, %eax
        shl $4, %eax
        """;

        System.out.println("Trying to generate automatic superopt for:");
        System.out.println(asm);
        System.out.println("======================");

        RegisterSet set = new RegisterSet();
        InstrList list = Parse.parse(set, asm);
        set.rax().set(65535);
        Opt.trySuperOpt(set, list, random -> {
            if (random.nextBoolean()) {
                return Bits.u32l(ImmCommon.flagBearingImm(random).v32());
            }

            return Bits.u32l(random.nextInt());
        });
    }
}
