package supercoder79.x86emu.instr;

import supercoder79.x86emu.instr.trait.Instr;
import supercoder79.x86emu.instr.trait.ShiftInstr;
import supercoder79.x86emu.simulate.Immediate;
import supercoder79.x86emu.simulate.Value;
import supercoder79.x86emu.simulate.ValueType;
import supercoder79.x86emu.simulate.register.Register;
import supercoder79.x86emu.simulate.register.RegisterSet;
import supercoder79.x86emu.superopt.ImmCommon;

import java.util.Random;

import static supercoder79.x86emu.simulate.ValueType.r8;

public class Sar extends ShiftInstr {

    public Sar(RegisterSet set, Value a, Value b, ValueType typeA, ValueType typeB) {
        super(set, a, b, typeA, typeB);
    }

    @Override
    public String mnemonic() {
        return "sar";
    }

    @Override
    protected long shift(long value) {
        return value >> 1L;
    }

    @Override
    protected int shift(int value) {
        return value >> 1;
    }

    @Override
    protected short shift(short value) {
        return (short) (value >> 1);
    }

    @Override
    protected byte shift(byte value) {
        return (byte) (value >> 1);
    }

    public static Instr create(RegisterSet set, Random random) {
        Immediate imm8 = ImmCommon.imm8p(random);
        Register reg = set.random(random);
        ValueType type = ValueType.random(random);

        return new Sar(set, imm8, reg, r8, type);
    }
}
