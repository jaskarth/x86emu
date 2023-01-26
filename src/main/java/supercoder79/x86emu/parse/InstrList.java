package supercoder79.x86emu.parse;

import supercoder79.x86emu.instr.trait.Instr;
import supercoder79.x86emu.instr.misc.Opaque;

import java.util.ArrayList;
import java.util.List;

public class InstrList {
    private final List<Instr> instructions = new ArrayList<>();

    public void add(Instr instr) {
        instructions.add(instr);
    }

    public List<Instr> getInstructions() {
        return instructions;
    }

    public void transparent() {
        for (int i = 0; i < instructions.size(); i++) {
            Instr instr = instructions.get(i);
            if (instr instanceof Opaque) {
                instructions.remove(i);
                i--;
            }
        }
    }

    public String assemble() {
        StringBuilder builder = new StringBuilder();

        for (Instr instr : instructions) {
            builder.append(instr.assemble()).append("\n");
        }

        return builder.toString();
    }
}
