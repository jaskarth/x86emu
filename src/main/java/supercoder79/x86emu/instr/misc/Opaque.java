package supercoder79.x86emu.instr.misc;

import supercoder79.x86emu.instr.trait.Instr;
import supercoder79.x86emu.simulate.Value;

import java.util.List;

public class Opaque implements Instr {
    private final String characteristic;

    public Opaque(String characteristic) {

        this.characteristic = characteristic;
    }

    @Override
    public void execute() {
        // no-op
    }

    @Override
    public String mnemonic() {
        return "opaque";
    }

    @Override
    public List<Value> liveins() {
        return List.of();
    }

    @Override
    public List<Value> liveouts() {
        return List.of();
    }

    @Override
    public String assemble() {
        return this.characteristic;
    }
}
