package supercoder79.x86emu.dag;

import supercoder79.x86emu.Instr;
import supercoder79.x86emu.instr.misc.Opaque;
import supercoder79.x86emu.parse.InstrList;
import supercoder79.x86emu.simulate.Value;
import supercoder79.x86emu.simulate.ValueType;
import supercoder79.x86emu.simulate.register.Register;
import supercoder79.x86emu.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Graph {
    private final List<Node> nodes;

    public Graph(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public static Graph buildGraph(InstrList list) {
        Map<Instr, Node> nodes = new HashMap<>();

        for (Instr inst : list.getInstructions()) {
            nodes.put(inst, new Node(inst));
        }

        Instr optop = new Opaque("Inputs");
        Instr opend = new Opaque("Outputs");
        nodes.put(optop, new Node(optop));
        nodes.put(opend, new Node(opend));

        Map<Register, Integer> livenums = new HashMap<>();
        Map<Pair<Register, Integer>, Node> writes = new HashMap<>();
        Map<Node, List<Pair<Register, Integer>>> reads = new HashMap<>();

        for (Instr inst : list.getInstructions()) {
            // Capture live ins
            ArrayList<Pair<Register, Integer>> instReads = new ArrayList<>();
            for (Value livein : inst.liveins()) {
                if (livein instanceof Register r) {
                    Integer num = livenums.getOrDefault(r, 0);
                    if (num == 0) {
                        writes.put(new Pair<>(r, 0), nodes.get(optop));
                    }

                    instReads.add(new Pair<>(r, num));
                }
            }

            reads.put(nodes.get(inst), instReads);

            // Capture live outs
            for (Value liveout : inst.liveouts()) {
                if (liveout instanceof Register r) {
                    Integer def = livenums.getOrDefault(r, 0);

                    writes.put(new Pair<>(r, def + 1), nodes.get(inst));
                    livenums.put(r, def + 1);
                }
            }
        }

        for (Map.Entry<Pair<Register, Integer>, Node> entry : writes.entrySet()) {
            Pair<Register, Integer> key = entry.getKey();
            Node start = entry.getValue();

            boolean wroteAny = false;
            for (Map.Entry<Node, List<Pair<Register, Integer>>> e : reads.entrySet()) {
                Node end = e.getKey();

                for (Pair<Register, Integer> p : e.getValue()) {
                    if (p.equals(key)) {
                        Edge edge = new Edge(start, end, key.first());

                        start.getSuccessors().add(edge);
                        end.getPredecessors().add(edge);
                        wroteAny = true;
                    }
                }
            }

            if (!wroteAny) {
                Edge edge = new Edge(start, nodes.get(opend), key.first());

                start.getSuccessors().add(edge);
                nodes.get(opend).getPredecessors().add(edge);
            }
        }

        return new Graph(new ArrayList<>(nodes.values()));
    }

    public static class Node {
        private final Instr instr;
        private final List<Edge> successors = new ArrayList<>();
        private final List<Edge> predecessors = new ArrayList<>();

        public Node(Instr instr) {
            this.instr = instr;
        }

        public List<Edge> getSuccessors() {
            return successors;
        }

        public List<Edge> getPredecessors() {
            return predecessors;
        }

        public Instr getInstr() {
            return instr;
        }
    }

    public static class Edge {
        private final Node source;
        private final Node destination;
        private final Register register;

        public Edge(Node source, Node destination, Register register) {
            this.source = source;
            this.destination = destination;
            this.register = register;
        }

        public Node getSource() {
            return source;
        }

        public Node getDestination() {
            return destination;
        }

        public Register getRegister() {
            return register;
        }
    }
}
