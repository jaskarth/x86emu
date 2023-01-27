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

    public void removeOpaque() {
        for (int i = 0; i < instructions.size(); i++) {
            Instr instr = instructions.get(i);
            if (instr instanceof Opaque) {
                instructions.remove(i);
                i--;
            }
        }
    }

    public int size() {
        return instructions.size();
    }

    public void execute() {
        for (Instr instr : instructions) {
            instr.execute();
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
