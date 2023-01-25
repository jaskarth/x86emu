package supercoder79.x86emu.simulate.register;

import supercoder79.x86emu.simulate.Value;
import supercoder79.x86emu.simulate.ValueType;
import supercoder79.x86emu.util.Bits;

// Models an x86 64 bit register
public class Register implements Value {
    private long value = 0;

    public long r64() {
        return value;
    }

    public int r32() {
        return (int) value;
    }

    public short r16() {
        return (short) value;
    }

    public byte r8() {
        return (byte) value;
    }

    // only rax, rbx, rcx, and rdx have this so it's better to not simulate it at at all
//    public byte r8h() {
//        return (byte) (value >> 8);
//    }

    public void set64(long value) {
        this.value = value;
    }

    // Clears top half of result!
    public void set32(int value) {
        // Masking is needed to prevent sign extension
        this.value = Bits.u32l(value);
    }

    public void set16(short value) {
        this.value = (this.value & 0xFFFFFFFFFFFF0000L) | (value & 0xFFFFL);
    }

    public void set8(byte value) {
        this.value = (this.value & 0xFFFFFFFFFFFFFF00L) | (value & 0xFFL);
    }

    @Override
    public long v64() {
        return r64();
    }

    @Override
    public int v32() {
        return r32();
    }

    @Override
    public short v16() {
        return r16();
    }

    @Override
    public byte v8() {
        return r8();
    }

    @Override
    public void set(long value) {
        set64(value);
    }

    @Override
    public void set(int value) {
        set32(value);
    }

    @Override
    public void set(short value) {
        set16(value);
    }

    @Override
    public void set(byte value) {
        set8(value);
    }

    @Override
    public String stringify(ValueType type) {
        return type.name();
    }

    public boolean isEqual(Register other) {
        return this.value == other.value;
    }
}
