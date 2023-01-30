package supercoder79.x86emu.superopt;

public class Metrics {
    private long start;
    private int count = 0;
    public Metrics() {
        start = System.currentTimeMillis();
    }

    public void itr() {
            count++;

            long now = System.currentTimeMillis();
            if (now - start > 1000) {
                System.out.println(count + "/s");
                start = now;
                count = 0;
            }
    }
}
