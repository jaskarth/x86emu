package supercoder79.x86emu.superopt;

import supercoder79.x86emu.simulate.Immediate;

import java.util.Random;

public final class ImmCommon {
    public static Immediate flagBearingImm(Random random) {
        if (random.nextInt(4) == 0) {
            return new Immediate(random.nextInt());
        }

        int shift = random.nextInt(32);
        int value = 1 << shift;
        int ctrl = random.nextInt(3);

        if (ctrl == 0) {
            value = -value;
        } else if (ctrl == 1) {
            value = value - 1;
        } else {
            value = ~value;
        }

        return new Immediate(value);
    }

    public static Immediate imm8p(Random random) {
        return new Immediate((byte) (random.nextInt(random.nextInt(127) + 1) + 1));
    }
}
