package supercoder79.x86emu.simulate.register;

public class Flag {
    private boolean set = false;

    public boolean isSet() {
        return set;
    }

    public void set(boolean set) {
        this.set = set;
    }

    public boolean isEqual(Flag flag) {
        return this.set == flag.set;
    }
}
