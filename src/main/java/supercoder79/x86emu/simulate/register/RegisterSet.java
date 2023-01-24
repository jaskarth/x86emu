package supercoder79.x86emu.simulate.register;

public class RegisterSet {
    private final Register rax = new Register();
    private final Register rbx = new Register();
    private final Register rcx = new Register();
    private final Register rdx = new Register();

    private final Register rsi = new Register();
    private final Register rdi = new Register();

    private final Register rbp = new Register();
    private final Register rsp = new Register();

    private final Register r8 = new Register();
    private final Register r9 = new Register();
    private final Register r10 = new Register();
    private final Register r11 = new Register();
    private final Register r12 = new Register();
    private final Register r13 = new Register();
    private final Register r14 = new Register();
    private final Register r15 = new Register();

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
}
