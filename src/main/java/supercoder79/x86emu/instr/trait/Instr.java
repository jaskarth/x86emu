package supercoder79.x86emu.instr.trait;

import supercoder79.x86emu.simulate.Value;
import supercoder79.x86emu.simulate.register.RegisterSet;

import java.util.List;

/**
 * A CPU instruction.
 */
public interface Instr {
    /**
     * Executes this instruction.
     */
    void execute();

    default String name() {
        return mnemonic();
    }

    /**
     * Returns the mnemonic of this instruction.
     *
     * @return the mnemonic
     */
    String mnemonic();

    // Live inputs into this instruction
    List<Value> liveins();

    // Live outputs from this instruction
    List<Value> liveouts();

    /**
     * Returns the assembly representation of this instruction.
     *
     * @return the assembly representation
     */
    String assemble();
}
