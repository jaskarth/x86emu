package supercoder79.x86emu.util;

import supercoder79.x86emu.simulate.ValueType;

public class Flow {
    public static void unimp(ValueType a, ValueType b) {
        throw new UnsupportedOperationException("Unknown type: " + a + ", " + b);
    }
}
