package supercoder79.x86emu.instr.reg;

import supercoder79.x86emu.instr.trait.ImmAcceptor;
import supercoder79.x86emu.superopt.ImmCommon;

import java.util.HashMap;
import java.util.Map;

public class Instr2Imm {
    private static final Map<String, ImmAcceptor> IMM = new HashMap<>();

    public static ImmAcceptor get(String instr) {
        return IMM.get(instr);
    }

    static {
        IMM.put("shl", ImmCommon::imm8p);
        IMM.put("shr", ImmCommon::imm8p);
        IMM.put("sar", ImmCommon::imm8p);
        IMM.put("and", ImmCommon::flagBearingImm);
    }
}
