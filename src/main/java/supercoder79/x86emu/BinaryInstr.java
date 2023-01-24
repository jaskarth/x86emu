package supercoder79.x86emu;

import supercoder79.x86emu.simulate.Value;
import supercoder79.x86emu.simulate.register.RegisterSet;

public interface BinaryInstr extends Instr {
    void execute(RegisterSet set, Value a, Value b);
}
