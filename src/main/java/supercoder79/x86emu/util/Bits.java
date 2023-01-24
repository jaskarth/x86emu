package supercoder79.x86emu.util;

public class Bits {
    public static long u32l(int i) {
        return Integer.toUnsignedLong(i);
    }

    public static int u16i(short s) {
        return Short.toUnsignedInt(s);
    }

    public static long u16l(short s) {
        return Short.toUnsignedLong(s);
    }

    public static int u8i(byte b) {
        return Byte.toUnsignedInt(b);
    }

    public static long u8l(byte b) {
        return u32l(u8i(b));
    }

    public static short u8s(byte b) {
        return (short) Byte.toUnsignedInt(b);
    }
}
