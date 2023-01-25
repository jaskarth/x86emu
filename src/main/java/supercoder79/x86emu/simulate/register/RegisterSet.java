package supercoder79.x86emu.simulate.register;

public class RegisterSet {
    private final Register rax = new Register("rax", "eax", "ax", "al");
    private final Register rbx = new Register("rbx", "ebx", "bx", "bl");
    private final Register rcx = new Register("rcx", "ecx", "cx", "cl");
    private final Register rdx = new Register("rdx", "edx", "dx", "dl");

    private final Register rsi = new Register("rsi", "esi", "si", "sil");
    private final Register rdi = new Register("rdi", "edi", "di", "dil");

    private final Register rbp = new Register("rbp", "ebp", "bp", "bpl");
    private final Register rsp = new Register("rsp", "esp", "sp", "spl");

    private final Register r8 = new Register("r8", "r8d", "r8w", "r8b");
    private final Register r9 = new Register("r9", "r9d", "r9w", "r9b");
    private final Register r10 = new Register("r10", "r10d", "r10w", "r10b");
    private final Register r11 = new Register("r11", "r11d", "r11w", "r11b");
    private final Register r12 = new Register("r12", "r12d", "r12w", "r12b");
    private final Register r13 = new Register("r13", "r13d", "r13w", "r13b");
    private final Register r14 = new Register("r14", "r14d", "r14w", "r14b");
    private final Register r15 = new Register("r15", "r15d", "r15w", "r15b");

    private final Flag cf = new Flag();
    private final Flag pf = new Flag();
    private final Flag af = new Flag();
    private final Flag zf = new Flag();
    private final Flag sf = new Flag();
    private final Flag tf = new Flag();
    private final Flag if_ = new Flag();
    private final Flag df = new Flag();
    private final Flag of = new Flag();

    public RegisterSet() {

    }

    public Register rax() {
        return rax;
    }

    public Register rbx() {
        return rbx;
    }

    public Register rcx() {
        return rcx;
    }

    public Register rdx() {
        return rdx;
    }

    public Register rsi() {
        return rsi;
    }

    public Register rdi() {
        return rdi;
    }

    public Register rbp() {
        return rbp;
    }

    public Register rsp() {
        return rsp;
    }

    public Register r8() {
        return r8;
    }

    public Register r9() {
        return r9;
    }

    public Register r10() {
        return r10;
    }

    public Register r11() {
        return r11;
    }

    public Register r12() {
        return r12;
    }

    public Register r13() {
        return r13;
    }

    public Register r14() {
        return r14;
    }

    public Register r15() {
        return r15;
    }

    public Flag cf() {
        return cf;
    }

    public Flag pf() {
        return pf;
    }

    public Flag af() {
        return af;
    }

    public Flag zf() {
        return zf;
    }

    public Flag sf() {
        return sf;
    }

    public Flag tf() {
        return tf;
    }

    public Flag if_() {
        return if_;
    }

    public Flag df() {
        return df;
    }

    public Flag of() {
        return of;
    }

    public void reset() {
        rax.set64(0);
        rbx.set64(0);
        rcx.set64(0);
        rdx.set64(0);
        rsi.set64(0);
        rdi.set64(0);
        rbp.set64(0);
        rsp.set64(0);
        r8.set64(0);
        r9.set64(0);
        r10.set64(0);
        r11.set64(0);
        r12.set64(0);
        r13.set64(0);
        r14.set64(0);
        r15.set64(0);

        cf.set(false);
        pf.set(false);
        af.set(false);
        zf.set(false);
        sf.set(false);
        tf.set(false);
        if_.set(false);
        df.set(false);
        of.set(false);
    }

    public boolean isEqual(RegisterSet other) {
        return rax.isEqual(other.rax())
                && rbx.isEqual(other.rbx())
                && rcx.isEqual(other.rcx())
                && rdx.isEqual(other.rdx())
                && rsi.isEqual(other.rsi())
                && rdi.isEqual(other.rdi())
                && rbp.isEqual(other.rbp())
                && rsp.isEqual(other.rsp())
                && r8.isEqual(other.r8())
                && r9.isEqual(other.r9())
                && r10.isEqual(other.r10())
                && r11.isEqual(other.r11())
                && r12.isEqual(other.r12())
                && r13.isEqual(other.r13())
                && r14.isEqual(other.r14())
                && r15.isEqual(other.r15())
                && cf.isEqual(other.cf())
                && pf.isEqual(other.pf())
                && af.isEqual(other.af())
                && zf.isEqual(other.zf())
                && sf.isEqual(other.sf())
                && tf.isEqual(other.tf())
                && if_.isEqual(other.if_())
                && df.isEqual(other.df())
                && of.isEqual(other.of());
    }
}
