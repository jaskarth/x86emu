package supercoder79.x86emu.instr.trait;

import supercoder79.x86emu.simulate.Immediate;

import java.util.Random;

public interface ImmAcceptor {
    Immediate imm(Random random);
}
