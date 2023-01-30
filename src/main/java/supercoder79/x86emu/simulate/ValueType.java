package supercoder79.x86emu.simulate;

import java.util.Random;

public enum ValueType {
    r64,
    r32,
    r16,
    r8;

    public static final ValueType[] VALUES = values();

    public static ValueType random(Random random) {
        return VALUES[random.nextInt(VALUES.length)];
    }

    public static ValueType randomHigh(Random random) {
        return random.nextBoolean() ? r64 : r32;
    }
}
