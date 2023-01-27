package supercoder79.x86emu.dag;

import supercoder79.x86emu.instr.trait.Instr;
import supercoder79.x86emu.parse.InstrList;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Topsort {
    public static InstrList topsort(Graph graph) {
        InstrList list = new InstrList();

        Deque<Instr> stack = new ArrayDeque<>();
        Set<Graph.Node> visited = new HashSet<>();
        topsort2(stack, visited, graph.getTop());

        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }

        System.out.println(list.assemble());

        list.removeOpaque();

        return list;
    }

    private static void topsort2(Deque<Instr> stack, Set<Graph.Node> visited, Graph.Node node) {
        Deque<Graph.Node> nodes = new ArrayDeque<>();
        nodes.push(node);

        while (!nodes.isEmpty()) {
            Graph.Node nd = nodes.pop();
            if (visited.contains(nd)) {
                continue;
            }

            visited.add(nd);

            for (Graph.Edge suc : nd.getSuccessors()) {
                nodes.push(suc.getDestination());
            }

            for (Graph.Edge suc : nd.getPredecessors()) {
                nodes.push(suc.getSource());
            }

            stack.push(nd.getInstr());
        }
    }

    private static void topsort(Deque<Instr> stack, Set<Graph.Node> visited, Graph.Node node) {
        for (Graph.Edge suc : node.getSuccessors()) {
            Graph.Node dest = suc.getDestination();

            if (!visited.contains(dest)) {
                topsort(stack, visited, dest);
                visited.add(dest);
            }
        }

        stack.push(node.getInstr());
    }
}
