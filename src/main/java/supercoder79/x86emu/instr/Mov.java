package supercoder79.x86emu.instr;

import supercoder79.x86emu.instr.trait.BinaryInstrBase;
import supercoder79.x86emu.simulate.Value;
import supercoder79.x86emu.simulate.ValueType;
import supercoder79.x86emu.simulate.register.RegisterSet;
import supercoder79.x86emu.util.Flow;

import java.util.List;

public class Mov extends BinaryInstrBase {
    public Mov(RegisterSet set, Value a, Value b, ValueType typeA, ValueType typeB) {
        super(set, a, b, typeA, typeB);
    }

    @Override
    public void execute() {
        if (typeB == ValueType.r64) {
            // Sign extension is ok here
            long va = switch (typeA) {
                case r64 -> a.v64();
                case r32 -> a.v32();
                default -> throw new UnsupportedOperationException("Unexpected value: " + typeA);
            };

            b.set(va);
            return;
        } else if (typeB == ValueType.r32) {
            // No sign extension
            int va = switch (typeA) {
                case r32 -> a.v32();
                default -> throw new UnsupportedOperationException("Unexpected value: " + typeA);
            };

            b.set(va);
            return;
        } else if (typeB == ValueType.r16) {
            // No sign extension
            short va = switch (typeA) {
                case r16 -> a.v16();
                default -> throw new UnsupportedOperationException("Unexpected value: " + typeA);
            };

            b.set(va);
            return;
        } else if (typeB == ValueType.r8) {
            // No sign extension
            byte va = switch (typeA) {
                case r8 -> a.v8();
                default -> throw new UnsupportedOperationException("Unexpected value: " + typeA);
            };

            b.set(va);
            return;
        }

        Flow.unimp(typeA, typeB);
    }

    // Only lhs is live for mov

    @Override
    public List<Value> liveins() {
        return List.of(a);
    }

    @Override
    public String mnemonic() {
        return "mov";
    }
}
