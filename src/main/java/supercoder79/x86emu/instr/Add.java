package supercoder79.x86emu.instr;

import supercoder79.x86emu.BinaryInstr;
import supercoder79.x86emu.simulate.Value;
import supercoder79.x86emu.simulate.ValueType;
import supercoder79.x86emu.simulate.register.RegisterSet;
import supercoder79.x86emu.util.Bits;
import supercoder79.x86emu.util.Flow;

import static supercoder79.x86emu.simulate.ValueType.*;

public class Add implements BinaryInstr {
    private final ValueType typeA;
    private final ValueType typeB;

    public Add(RegisterSet set, ValueType typeA, ValueType typeB) {
        this.typeA = typeA;
        this.typeB = typeB;
    }

    @Override
    public void execute(RegisterSet set, Value a, Value b) {
        if (typeA == r64) {
            long va = a.v64();

            // Sign extension is ok here
            long vb = switch (typeB) {
                case r64 -> b.v64();
                case r32 -> b.v32();
                case r16 -> b.v16();
                case r8 -> b.v8();
            };

            long result = va + vb;

            b.set(result);
            return;
        } else if (typeA == r32) {
            int va = a.v32();

            // No sign extension
            int vb = switch (typeB) {
                case r32 -> b.v32();
                case r16 -> Bits.u16i(b.v16());
                case r8 -> Bits.u8i(b.v8());
                default -> throw new UnsupportedOperationException("Unknown type: " + typeB);
            };

            int result = va + vb;

            b.set(result);
            return;
        }

        Flow.unimp(typeA, typeB);
    }

    @Override
    public String name() {
        return "add";
    }
}
