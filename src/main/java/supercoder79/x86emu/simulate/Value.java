package supercoder79.x86emu.simulate;

/**
 * Represents a value that can be represented in 64, 32, 16, or 8 bits.
 */
public interface Value {
    long v64();
    int v32();
    short v16();
    byte v8();

    void set(long value);
    void set(int value);
    void set(short value);
    void set(byte value);

    // Returns a small string representation of the value
    String stringify(ValueType type);

    // Turns this value into a string representation for reassembly
    String assemble(ValueType type);
}
