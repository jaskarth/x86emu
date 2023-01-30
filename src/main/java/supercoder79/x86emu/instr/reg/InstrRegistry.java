package supercoder79.x86emu.instr.reg;

import supercoder79.x86emu.instr.*;

import java.util.*;

public class InstrRegistry {
    private static final Map<String, InstrReg> REGISTRY = new HashMap<>();
    private static List<String> KEYS = new ArrayList<>();
    private static boolean frozen = false;

    public static InstrReg get(String instr) {
        if (!REGISTRY.containsKey(instr)) {
            throw new IllegalArgumentException("Unknown instruction: " + instr);
        }

        return REGISTRY.get(instr);
    }

    public static void freeze() {
        frozen = true;

        KEYS.addAll(REGISTRY.keySet());
    }

    public static InstrReg random(Random random) {
        if (!frozen) {
            throw new IllegalStateException("Registry not frozen");
        }

        return REGISTRY.get(KEYS.get(random.nextInt(KEYS.size())));
    }

    static {
        REGISTRY.put("shl", new InstrReg(Shl::create));
        REGISTRY.put("shr", new InstrReg(Shr::create));
        REGISTRY.put("sar", new InstrReg(Shr::create));
        REGISTRY.put("not", new InstrReg(Not::create));
        REGISTRY.put("neg", new InstrReg(Neg::create));
        REGISTRY.put("and", new InstrReg(And::create));
        REGISTRY.put("or", new InstrReg(Or::create));
        REGISTRY.put("xor", new InstrReg(Xor::create));
    }
}
