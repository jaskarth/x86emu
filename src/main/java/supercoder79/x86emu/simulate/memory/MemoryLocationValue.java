package supercoder79.x86emu.simulate.memory;

import supercoder79.x86emu.simulate.Immediate;
import supercoder79.x86emu.simulate.MemValue;
import supercoder79.x86emu.simulate.Value;
import supercoder79.x86emu.simulate.ValueType;
import supercoder79.x86emu.simulate.register.Register;
import supercoder79.x86emu.simulate.register.RegisterSet;

import java.util.Random;

import static supercoder79.x86emu.simulate.ValueType.r64;

// disp(%base, %index, scale)
// disp + base + (index * scale)
public final class MemoryLocationValue implements MemValue {
    private final HeapMemory heap;
    private MemSlice backing = null;
    private final int disp;
    private final Value base;
    private final Value index;
    private final int scale;

    public MemoryLocationValue(HeapMemory heap, Value base, Value index) {
        this(heap, 0, base, index, 1);
    }

    public MemoryLocationValue(HeapMemory heap, int disp, Value base, Value index) {
        this(heap, disp, base, index, 1);
    }

    public MemoryLocationValue(HeapMemory heap, int disp, Value base) {
        this(heap, disp, base, new Immediate(0), 1);
    }

    public MemoryLocationValue(HeapMemory heap, Value base) {
        this(heap, 0, base, new Immediate(0), 1);
    }

    public MemoryLocationValue(HeapMemory heap, int disp, Value base, Value index, int scale) {
        this.heap = heap;
        this.disp = disp;
        this.base = base;
        this.index = index;
        this.scale = scale;
    }

    private int value() {
        // TODO: is v32 ok?
        int base = this.base.v32();
        int index = this.index.v32();
        return base + (index * scale) + disp;
    }

    private void setupSlice() {
        if (backing == null) {
            backing = new MemSlice(heap, value());
        }
    }

    @Override
    public long v64() {
        setupSlice();
        return backing.v64();
    }

    @Override
    public int v32() {
        setupSlice();
        return backing.v32();
    }

    @Override
    public short v16() {
        setupSlice();
        return backing.v16();
    }

    @Override
    public byte v8() {
        setupSlice();
        return backing.v8();
    }

    @Override
    public void set(long value) {
        setupSlice();
        backing.set(value);
    }

    @Override
    public void set(int value) {
        setupSlice();
        backing.set(value);
    }

    @Override
    public void set(short value) {
        setupSlice();
        backing.set(value);
    }

    @Override
    public void set(byte value) {
        setupSlice();
        backing.set(value);
    }

    @Override
    public String stringify(ValueType type) {
        return type.name().replace('r', 'm');
    }

    @Override
    public String assemble(ValueType type) {
        StringBuilder builder = new StringBuilder();
        if (disp != 0) {
            builder.append(disp);
        }
        builder.append("(");
        builder.append(base.assemble(r64));
        if (index instanceof Register) {
            builder.append(", ");
            builder.append(index.assemble(r64));
            if (scale != 1) {
                builder.append(", ");
                builder.append(scale);
            }
        }
        builder.append(")");

        return builder.toString();
    }

    @Override
    public long address() {
        setupSlice();
        return backing.address();
    }

    public static MemoryLocationValue create(HeapMemory heap, RegisterSet set, Random random) {
        int sel = random.nextInt(4);

        return switch (sel) {
            case 0 -> new MemoryLocationValue(heap, set.random(random));
            case 1 -> new MemoryLocationValue(heap, set.random(random), set.random(random));
            case 2 -> new MemoryLocationValue(heap, random.nextInt(64) * 4,  set.random(random), set.random(random));
            case 3 -> new MemoryLocationValue(heap, random.nextInt(64) * 4,  set.random(random), set.random(random), (int) Math.pow(2, random.nextInt(4)));
            default -> throw new IllegalStateException("Unexpected value: " + sel);
        };
    }
}
