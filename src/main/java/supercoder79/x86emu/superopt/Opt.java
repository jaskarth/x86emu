package supercoder79.x86emu.superopt;

import supercoder79.x86emu.instr.reg.Instr2Imm;
import supercoder79.x86emu.instr.reg.InstrRegistry;
import supercoder79.x86emu.instr.trait.Instr;
import supercoder79.x86emu.parse.InstrList;
import supercoder79.x86emu.parse.Parse;
import supercoder79.x86emu.simulate.register.Register;
import supercoder79.x86emu.simulate.register.RegisterSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Opt {
    private static final String[] R64_NAMES = {"rax", "rbx", "rcx", "rdx", "rsi", "rdi", "rbp", "rsp", "r8", "r9", "r10", "r11", "r12", "r13", "r14", "r15"};
    private static final String[] R32_NAMES = {"eax", "ebx", "ecx", "edx", "esi", "edi", "ebp", "esp", "r8d", "r9d", "r10d", "r11d", "r12d", "r13d", "r14d", "r15d"};

    public static void trySuperOpt(RegisterSet state, InstrList original, RegisterFiller filler) {
        InstrRegistry.freeze();

        RegisterSet scopy = state.clone();
        for (Instr i : original.getInstructions()) {
            i.execute();
        }

        RegisterSet endcpy = state.clone();

        List<String> set = new ArrayList<>();
        Random random = new Random();
        Metrics metrics = new Metrics();

        while (true) {
            RegisterSet copy = scopy.clone();

            int amt = random.nextInt(Math.max(original.size() - 1, 1)) + 1;
            InstrList list = new InstrList();
            for (int j = 0; j < amt; j++) {
                Instr instr = getInstr(random, copy);
                list.add(instr);
            }

            list.execute();

            if (copy.isEqual(endcpy)) {
                String asm = list.assemble();
                if (!rigor(asm, random, scopy, state, original, filler)) {
//                    System.out.println("Rigorous test failed:\n" + asm + "\n==============");
                    continue;
                }

                if (!set.contains(asm)) {
                    set.add(asm);
                    System.out.println("Match found with " + list.size() + " instrs!!!!");
                    System.out.println(asm);
                    System.out.println("==============");
                }
            }

//            metrics.itr();
        }
    }

    private static Instr getInstr(Random random, RegisterSet copy) {
        return InstrRegistry.random(random).generator().create(copy, random);
    }

    private static boolean rigor(String asm, Random random, RegisterSet origState, RegisterSet endState, InstrList origList, RegisterFiller filler) {
        for (int i = 0; i < 100000; i++) {
            RegisterSet copy = origState.clone();
            long fill = filler.fill(random);

            // TODO: needs to calculate liveins
            copy.rax().set(fill);
            endState.rax().set(fill);

            origList.execute();

            InstrList list = Parse.parse(copy, asm);
            list.execute();

            if (!copy.isEqual(endState)) {
                return false;
            }
        }

        return true;
    }
}
