package supercoder79.x86emu.dag;

import supercoder79.x86emu.simulate.ValueType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DotExporter {
    public static void exportQuick(Graph graph) {
        StringBuilder builder = new StringBuilder();
        builder.append("digraph G {\n");

        Map<Graph.Node, Integer> ids = new HashMap<>();
        for (Graph.Node node : graph.getNodes()) {
            ids.put(node, ids.size());
        }

        for (Graph.Node node : graph.getNodes()) {
            builder.append(ids.get(node));
            builder.append(" [label=\"");
            builder.append(node.getInstr().assemble());
            builder.append("\"];\n");
        }

        for (Graph.Node nd : graph.getNodes()) {
            for (Graph.Edge succ : nd.getSuccessors()) {
                builder.append(ids.get(nd));
                builder.append(" -> ");
                builder.append(ids.get(succ.getDestination()));
                builder.append(" [label=\"");
                builder.append(succ.getRegister().canonical(ValueType.r64));
                builder.append("\"];\n");
            }
        }

        builder.append("}\n");

        try {
            Files.writeString(Paths.get(".", "build", "graph.dot"), builder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
