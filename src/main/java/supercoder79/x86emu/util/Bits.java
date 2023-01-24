package supercoder79.x86emu.util;

public class Bits {
    public static int u16i(short s) {
        return Short.toUnsignedInt(s);
    }

    public static int u8i(byte b) {
        return Byte.toUnsignedInt(b);
    }
}
