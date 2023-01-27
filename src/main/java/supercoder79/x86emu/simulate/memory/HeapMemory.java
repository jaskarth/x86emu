package supercoder79.x86emu.simulate.memory;

import java.util.HashMap;
import java.util.Objects;

/**
 * Represents memory on the heap.
 */
public class HeapMemory {
    private final HashMap<Integer, Byte> memory = new HashMap<>();

    public byte get(int address) {
        return memory.get(address);
    }

    public void set(int address, byte value) {
        memory.put(address, value);
    }

    public MemSlice slice(int address) {
        return new MemSlice(this, address);
    }

    public boolean isEqual(HeapMemory other) {
        for (Integer m : memory.keySet()) {
            if (!Objects.equals(other.memory.get(m), memory.get(m))) {
                return false;
            }
        }

        for (Integer m : other.memory.keySet()) {
            if (!Objects.equals(other.memory.get(m), memory.get(m))) {
                return false;
            }
        }

        return true;
    }
}
