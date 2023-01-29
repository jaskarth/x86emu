package supercoder79.x86emu.simulate.memory;

import supercoder79.x86emu.simulate.MemValue;
import supercoder79.x86emu.simulate.Value;
import supercoder79.x86emu.simulate.ValueType;

public class MemSlice implements MemValue {
    private final HeapMemory memory;
    public final int start;

    public MemSlice(HeapMemory memory, int start) {
        this.memory = memory;
        this.start = start;
    }

    public long m64() {
        byte b0 = memory.get(start);
        byte b1 = memory.get(start + 1);
        byte b2 = memory.get(start + 2);
        byte b3 = memory.get(start + 3);
        byte b4 = memory.get(start + 4);
        byte b5 = memory.get(start + 5);
        byte b6 = memory.get(start + 6);
        byte b7 = memory.get(start + 7);

        return ((b7 & 0xFFL) << 56) | ((b6 & 0xFFL) << 48) | ((b5 & 0xFFL) << 40) | ((b4 & 0xFFL) << 32) | ((b3 & 0xFFL) << 24) | ((b2 & 0xFFL) << 16) | ((b1 & 0xFFL) << 8) | (b0 & 0xFFL);
    }

    public int m32() {
        byte b0 = memory.get(start);
        byte b1 = memory.get(start + 1);
        byte b2 = memory.get(start + 2);
        byte b3 = memory.get(start + 3);

        return ((b3 & 0xFF) << 24) | ((b2 & 0xFF) << 16) | ((b1 & 0xFF) << 8) | (b0 & 0xFF);
    }

    public short m16() {
        byte b0 = memory.get(start);
        byte b1 = memory.get(start + 1);

        return (short) (((b1 & 0xFF) << 8) | (b0 & 0xFF));
    }

    public byte m8() {
        return memory.get(start);
    }

    public void set64(long value) {
        memory.set(start, (byte) value);
        memory.set(start + 1, (byte) (value >> 8));
        memory.set(start + 2, (byte) (value >> 16));
        memory.set(start + 3, (byte) (value >> 24));
        memory.set(start + 4, (byte) (value >> 32));
        memory.set(start + 5, (byte) (value >> 40));
        memory.set(start + 6, (byte) (value >> 48));
        memory.set(start + 7, (byte) (value >> 56));
    }

    public void set32(int value) {
        memory.set(start, (byte) value);
        memory.set(start + 1, (byte) (value >> 8));
        memory.set(start + 2, (byte) (value >> 16));
        memory.set(start + 3, (byte) (value >> 24));
    }

    public void set16(short value) {
        memory.set(start, (byte) value);
        memory.set(start + 1, (byte) (value >> 8));
    }

    public void set8(byte value) {
        memory.set(start, value);
    }

    @Override
    public long v64() {
        return m64();
    }

    @Override
    public int v32() {
        return m32();
    }

    @Override
    public short v16() {
        return m16();
    }

    @Override
    public byte v8() {
        return m8();
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
        return type.name().replace('r', 'm');
    }

    @Override
    public String assemble(ValueType type) {
        // TODO: x(%y)
        return "[" + start + "]";
    }

    @Override
    public long address() {
        return start;
    }
}
