package supercoder79.x86emu;

import supercoder79.x86emu.simulate.Value;
import supercoder79.x86emu.simulate.register.RegisterSet;

public interface Instr {
    void execute();

    default String name() {
        return mnemonic();
    }

    String mnemonic();

    String assemble();
}
