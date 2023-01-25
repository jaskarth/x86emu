package supercoder79.x86emu;

import supercoder79.x86emu.simulate.Value;
import supercoder79.x86emu.simulate.register.RegisterSet;

import java.util.List;

public interface Instr {
    void execute();

    default String name() {
        return mnemonic();
    }

    String mnemonic();

    List<Value> liveins();
    List<Value> liveouts();

    String assemble();
}
