package supercoder79.x86emu.instr;

import supercoder79.x86emu.BinaryInstrBase;
import supercoder79.x86emu.simulate.Value;
import supercoder79.x86emu.simulate.ValueType;
import supercoder79.x86emu.simulate.register.RegisterSet;

import static supercoder79.x86emu.simulate.ValueType.*;

public class Sar extends BinaryInstrBase {

    public Sar(RegisterSet set, Value a, Value b, ValueType typeA, ValueType typeB) {
        super(set, a, b, typeA, typeB);

        if (typeA != r8) {
            throw new IllegalStateException("Must be r8 on lhs");
        }
    }

    @Override
    public void execute() {
        if (typeB == r64) {
            long vb = b.v64();

            byte va = a.v8();
            for (byte i = 0; i < va; i++) {
                vb >>= 1L;
            }

            b.set(vb);
        } else if (typeB == r32) {
            int vb = b.v32();

            byte va = a.v8();
            for (byte i = 0; i < va; i++) {
                vb >>= 1;
            }

            b.set(vb);
        } else if (typeB == r16) {
            short vb = b.v16();

            byte va = a.v8();
            for (byte i = 0; i < va; i++) {
                vb >>= 1;
            }

            b.set(vb);
        } else if (typeB == r8) {
            byte vb = b.v8();

            byte va = a.v8();
            for (byte i = 0; i < va; i++) {
                vb >>= 1;
            }

            b.set(vb);
        }
    }

    @Override
    public String mnemonic() {
        return "sar";
    }
}
