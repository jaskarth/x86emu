package supercoder79.x86emu.simulate.memory;

import java.util.HashMap;

public class HeapMemory {
    private final HashMap<Integer, Byte> memory = new HashMap<>();

    public byte get(int address) {
        return memory.get(address);
    }

    public void set(int address, byte value) {
        memory.put(address, value);
    }
}
