package supercoder79.x86emu.instr;

import supercoder79.x86emu.instr.trait.ShiftInstr;
import supercoder79.x86emu.simulate.Value;
import supercoder79.x86emu.simulate.ValueType;
import supercoder79.x86emu.simulate.register.RegisterSet;

public class Shr extends ShiftInstr {

    public Shr(RegisterSet set, Value a, Value b, ValueType typeA, ValueType typeB) {
        super(set, a, b, typeA, typeB);
    }

    @Override
    public String mnemonic() {
        return "shr";
    }

    @Override
    protected long shift(long value) {
        return value >>> 1L;
    }

    @Override
    protected int shift(int value) {
        return value >>> 1;
    }

    @Override
    protected short shift(short value) {
        return (short) ((value & 0xffff) >>> 1);
    }

    @Override
    protected byte shift(byte value) {
        return (byte) ((value & 0xff) >>> 1);
    }
}
