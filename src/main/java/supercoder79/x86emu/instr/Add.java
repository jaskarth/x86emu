package supercoder79.x86emu.instr;

import supercoder79.x86emu.BinaryInstr;
import supercoder79.x86emu.BinaryInstrBase;
import supercoder79.x86emu.simulate.Value;
import supercoder79.x86emu.simulate.ValueType;
import supercoder79.x86emu.simulate.register.RegisterSet;
import supercoder79.x86emu.util.Bits;
import supercoder79.x86emu.util.Flow;

import static supercoder79.x86emu.simulate.ValueType.*;

public class Add extends BinaryInstrBase {

    public Add(RegisterSet set, Value a, Value b, ValueType typeA, ValueType typeB) {
        super(set, a, b, typeA, typeB);
    }

    @Override
    public void execute() {
        // TODO: flag setting

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
                case r64 -> (int) b.v64();
                case r32 -> b.v32();
                case r16 -> Bits.u16i(b.v16());
                case r8 -> Bits.u8i(b.v8());
            };

            int result = va + vb;

            b.set(result);
            return;
        } else if (typeA == r16) {
            short va = a.v16();

            // No sign extension
            short vb = switch (typeB) {
                case r64 -> (short) b.v64();
                case r32 -> (short) b.v32();
                case r16 -> b.v16();
                case r8 -> Bits.u8s(b.v8());
            };

            short result = (short) (va + vb);

            b.set(result);
            return;
        } else if (typeA == r8) {
            byte va = a.v8();

            // No sign extension
            byte vb = switch (typeB) {
                case r64 -> (byte) b.v64();
                case r32 -> (byte) b.v32();
                case r16 -> (byte) b.v16();
                case r8 -> b.v8();
            };

            byte result = (byte) (va + vb);

            b.set(result);
            return;
        }

        Flow.unimp(typeA, typeB);
    }

    @Override
    public String mnemonic() {
        return "add";
    }
}
