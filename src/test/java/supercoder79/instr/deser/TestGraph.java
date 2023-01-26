package supercoder79.instr.deser;

import org.junit.jupiter.api.Test;
import supercoder79.x86emu.dag.DotExporter;
import supercoder79.x86emu.dag.Graph;
import supercoder79.x86emu.dag.Topsort;
import supercoder79.x86emu.parse.Parse;
import supercoder79.x86emu.simulate.register.RegisterSet;

public class TestGraph {
    @Test
    public void test() {
        String asm =
        """
        mov %ebx, %eax
        mov $200, %ecx
        sar $4, %eax
        shl $4, %ecx
        add %eax, %ecx
        add %eax, %ebx
        """;

        RegisterSet set = new RegisterSet();
        Graph graph = Graph.buildGraph(Parse.parse(set, asm));
        DotExporter.exportQuick(graph);
    }
}
