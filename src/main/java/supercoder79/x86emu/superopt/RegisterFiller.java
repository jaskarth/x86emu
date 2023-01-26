package supercoder79.x86emu.superopt;

import supercoder79.x86emu.simulate.register.Register;

import java.util.Random;

public interface RegisterFiller {
    long fill(Random random);
}
