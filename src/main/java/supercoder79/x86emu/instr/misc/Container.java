package supercoder79.x86emu.instr.misc;

import supercoder79.x86emu.instr.trait.Instr;
import supercoder79.x86emu.simulate.Value;

import java.util.ArrayList;
import java.util.List;

public class Container implements Instr {
    private final Instr[] inner;

    public Container(Instr... inner) {

        this.inner = inner;
    }

    @Override
    public void execute() {
        for (Instr instr : inner) {
            instr.execute();
        }
    }

    @Override
    public String mnemonic() {
        return "cont";
    }

    @Override
    public List<Value> liveins() {
        List<Value> liveins = new ArrayList<>();
        for (Instr instr : inner) {
            liveins.addAll(instr.liveins());
        }

        return liveins;
    }

    @Override
    public List<Value> liveouts() {
        List<Value> liveouts = new ArrayList<>();
        for (Instr instr : inner) {
            liveouts.addAll(instr.liveouts());
        }

        return liveouts;
    }

    @Override
    public String assemble() {
        StringBuilder builder = new StringBuilder();
        for (Instr instr : inner) {
            builder.append(instr.assemble()).append("\n");
        }

        return builder.toString();
    }
}
