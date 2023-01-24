package supercoder79.x86emu.simulate;

public interface Value {
    long v64();
    int v32();
    short v16();
    byte v8();

    void set(long value);
    void set(int value);
    void set(short value);
    void set(byte value);

    String stringify(ValueType type);
}
