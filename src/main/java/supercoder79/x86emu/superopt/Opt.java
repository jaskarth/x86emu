package supercoder79.x86emu.superopt;

import supercoder79.x86emu.instr.reg.Instr2Imm;
import supercoder79.x86emu.instr.trait.ImmAcceptor;
import supercoder79.x86emu.instr.trait.Instr;
import supercoder79.x86emu.parse.InstrList;
import supercoder79.x86emu.parse.InstrParse;
import supercoder79.x86emu.parse.Parse;
import supercoder79.x86emu.simulate.Immediate;
import supercoder79.x86emu.simulate.ValueType;
import supercoder79.x86emu.simulate.register.Register;
import supercoder79.x86emu.simulate.register.RegisterSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Opt {
    private static final String[] OPT_OK = {"and", "shl", "shr", "sar"};
    private static final String[] R64_NAMES = {"rax", "rbx", "rcx", "rdx", "rsi", "rdi", "rbp", "rsp", "r8", "r9", "r10", "r11", "r12", "r13", "r14", "r15"};
    private static final String[] R32_NAMES = {"eax", "ebx", "ecx", "edx", "esi", "edi", "ebp", "esp", "r8d", "r9d", "r10d", "r11d", "r12d", "r13d", "r14d", "r15d"};

    public static void trySuperOpt(RegisterSet state, InstrList original, RegisterFiller filler) {
        RegisterSet scopy = state.clone();
        for (Instr i : original.getInstructions()) {
            i.execute();
        }

        RegisterSet endcpy = state.clone();

        List<String> set = new ArrayList<>();
        Random random = new Random();
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
        }
    }

    private static Instr getInstr(Random random, RegisterSet copy) {
        String name = OPT_OK[random.nextInt(OPT_OK.length)];
        Immediate immNext = Instr2Imm.get(name).imm(random);

        Register r1 = getReg(random, copy);
        return InstrParse.makeBinary(name, copy, immNext, r1, immNext.getType(), ValueType.r32);
    }

    private static Register getReg(Random random, RegisterSet copy) {
        if (random.nextBoolean()) {
            return copy.getReg(R64_NAMES[random.nextInt(R64_NAMES.length)]);
        }

        return copy.getReg(R32_NAMES[random.nextInt(R32_NAMES.length)]);
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
