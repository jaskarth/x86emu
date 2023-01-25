package supercoder79.x86emu.parse;

import supercoder79.x86emu.Instr;

import java.util.ArrayList;
import java.util.List;

public class InstrList {
    private final List<Instr> instructions = new ArrayList<>();

    public void add(Instr instr) {
        instructions.add(instr);
    }

    public String assemble() {
        StringBuilder builder = new StringBuilder();

        for (Instr instr : instructions) {
            builder.append(instr.assemble()).append("\n");
        }

        return builder.toString();
    }
}
