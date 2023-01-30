package supercoder79.x86emu.instr.reg;

import supercoder79.x86emu.instr.trait.Instr;
import supercoder79.x86emu.simulate.register.RegisterSet;

import java.util.Random;

public interface InstrGen {
    Instr create(RegisterSet set, Random random);
}
