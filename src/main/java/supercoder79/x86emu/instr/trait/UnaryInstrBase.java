package supercoder79.x86emu.instr.trait;

import supercoder79.x86emu.simulate.Value;
import supercoder79.x86emu.simulate.ValueType;
import supercoder79.x86emu.simulate.register.RegisterSet;

import java.util.List;

public abstract class UnaryInstrBase implements UnaryInstr {
    protected final RegisterSet set;
    protected final Value value;
    protected final ValueType type;

    public UnaryInstrBase(RegisterSet set, Value value, ValueType type) {
        this.set = set;
        this.value = value;
        this.type = type;
    }

    @Override
    public Value value() {
        return value;
    }

    @Override
    public List<Value> liveins() {
        return List.of(value);
    }

    @Override
    public List<Value> liveouts() {
        return List.of(value);
    }

    @Override
    public String toString() {
        return name() + "_" + value.stringify(type);
    }

    @Override
    public String assemble() {
        return mnemonic() + " " + value.stringify(type);
    }
}
