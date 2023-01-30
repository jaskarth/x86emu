package supercoder79.x86emu.instr;

import supercoder79.x86emu.instr.trait.Instr;
import supercoder79.x86emu.instr.trait.UnaryInstrBase;
import supercoder79.x86emu.simulate.Value;
import supercoder79.x86emu.simulate.ValueType;
import supercoder79.x86emu.simulate.register.Register;
import supercoder79.x86emu.simulate.register.RegisterSet;

import java.util.Random;

import static supercoder79.x86emu.simulate.ValueType.r64;

public class Neg extends UnaryInstrBase {
    public Neg(RegisterSet set, Value value, ValueType type) {
        super(set, value, type);
    }

    @Override
    public void execute() {
        if (this.type == r64) {
            this.value.set(-this.value.v64());
        } else if (this.type == ValueType.r32) {
            this.value.set(-this.value.v32());
        } else if (this.type == ValueType.r16) {
            this.value.set((short) -this.value.v16());
        } else if (this.type == ValueType.r8) {
            this.value.set((byte) -this.value.v8());
        }
    }

    @Override
    public String mnemonic() {
        return "neg";
    }

    public static Instr create(RegisterSet set, Random random) {
        Register reg = set.random(random);
        ValueType type = ValueType.random(random);

        return new Neg(set, reg, type);
    }
}
