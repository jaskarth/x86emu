package supercoder79.x86emu;

import supercoder79.x86emu.simulate.Value;
import supercoder79.x86emu.simulate.ValueType;
import supercoder79.x86emu.simulate.register.RegisterSet;

public abstract class BinaryInstrBase implements BinaryInstr {
    protected final RegisterSet set;
    protected final Value a;
    protected final Value b;
    protected final ValueType typeA;
    protected final ValueType typeB;

    public BinaryInstrBase(RegisterSet set, Value a, Value b, ValueType typeA, ValueType typeB) {

        this.set = set;
        this.a = a;
        this.b = b;
        this.typeA = typeA;
        this.typeB = typeB;
    }

    @Override
    public Value a() {
        return a;
    }

    @Override
    public Value b() {
        return b;
    }

    @Override
    public String toString() {
        return name() + "_" + a.stringify(typeA) + "_" + b.stringify(typeB);
    }
}
