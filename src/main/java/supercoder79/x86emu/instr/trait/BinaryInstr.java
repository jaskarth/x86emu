package supercoder79.x86emu.instr.trait;

import supercoder79.x86emu.simulate.Value;

public interface BinaryInstr extends Instr {
    Value a();

    Value b();
}
