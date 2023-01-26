package supercoder79.x86emu.simulate;

import supercoder79.x86emu.util.Bits;

import static supercoder79.x86emu.simulate.ValueType.*;

public final class Immediate implements Value {
    private ValueType type;
    private final long value;

    public Immediate(long value) {
        this.type = r64;
        this.value = value;
    }

    public Immediate(int value) {
        this.type = r32;
        this.value = Bits.u32l(value);
    }

    public Immediate(short value) {
        this.type = r16;
        this.value = Bits.u16l(value);
    }

    public Immediate(byte value) {
        this.type = r8;
        this.value = Bits.u8l(value);
    }

    public void setType(ValueType type) {
        this.type = type;
    }

    public ValueType getType() {
        return type;
    }

    @Override
    public long v64() {
        if (type == r64) {
            return value;
        } else {
            throw new UnsupportedOperationException("Cannot get 64 bit value from " + type);
        }
    }

    @Override
    public int v32() {
        if (type == r32) {
            return (int) value;
        } else {
            throw new UnsupportedOperationException("Cannot get 32 bit value from " + type);
        }
    }

    @Override
    public short v16() {
        if (type == r16) {
            return (short) value;
        } else {
            throw new UnsupportedOperationException("Cannot get 16 bit value from " + type);
        }
    }

    @Override
    public byte v8() {
        if (type == r8) {
            return (byte) value;
        } else {
            throw new UnsupportedOperationException("Cannot get 8 bit value from " + type);
        }
    }

    @Override
    public void set(long value) {
        throw new UnsupportedOperationException("Cannot set an immediate value");
    }

    @Override
    public void set(int value) {
        throw new UnsupportedOperationException("Cannot set an immediate value");
    }

    @Override
    public void set(short value) {
        throw new UnsupportedOperationException("Cannot set an immediate value");
    }

    @Override
    public void set(byte value) {
        throw new UnsupportedOperationException("Cannot set an immediate value");
    }

    @Override
    public String stringify(ValueType type) {
        if (type != this.type) {
            throw new UnsupportedOperationException("Cannot stringify myself (" + this.type + ") as " + type);
        }

        return switch (type) {
            case r64 -> "imm64";
            case r32 -> "imm32";
            case r16 -> "imm16";
            case r8 -> "imm8";
        };
    }

    @Override
    public String canonical(ValueType type) {
        return "$" + value;
    }
}
