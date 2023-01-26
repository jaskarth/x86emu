package supercoder79.x86emu.superopt;

import supercoder79.x86emu.instr.reg.Instr2Imm;
import supercoder79.x86emu.instr.trait.ImmAcceptor;
import supercoder79.x86emu.instr.trait.Instr;
import supercoder79.x86emu.parse.InstrList;
import supercoder79.x86emu.parse.InstrParse;
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

    public static void trySuperOpt(RegisterSet state, InstrList original) {
        RegisterSet scopy = state.clone();
        for (Instr i : original.getInstructions()) {
            i.execute();
        }

        List<String> set = new ArrayList<>();
        Random random = new Random();
        Immediate immNext;
        for (int i = 0; i < 100000; i++) {
            RegisterSet copy = scopy.clone();
            String name = OPT_OK[random.nextInt(OPT_OK.length)];
            immNext = Instr2Imm.get(name).imm(random);

            Register r1 = copy.getReg(R32_NAMES[random.nextInt(R32_NAMES.length)]);
            Instr instr = InstrParse.makeBinary(name, copy, immNext, r1, immNext.getType(), ValueType.r32);

            instr.execute();

            if (copy.isEqual(state)) {
                String asm = instr.assemble();
                if (!set.contains(asm)) {
                    set.add(asm);
                    System.out.println(asm);
                    System.out.println("Match found!!!");
                }
            }
        }
    }
}
