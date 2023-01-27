package supercoder79;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UiCA {
    private static final Pattern PATTERN = Pattern.compile("Throughput \\(in cycles per iteration\\): (\\d+\\.\\d+)", Pattern.MULTILINE);
    private static final String UICA_EXEC = System.getProperty("UICA_EXEC");
    public record UiCAOutput(double cyclesPerItr) {}

    public static UiCAOutput run(File obj) {
        if (UICA_EXEC == null) {
            throw new IllegalStateException("UICA_EXEC not set");
        }

        try {
            Process exec = Runtime.getRuntime().exec(UICA_EXEC + " " + obj.getAbsolutePath() + " -arch RKL");

            BufferedReader serr = new BufferedReader(new InputStreamReader(exec.getInputStream()));

            String s;
            StringBuilder b = new StringBuilder();

            while ((s = serr.readLine()) != null) {
                b.append(s).append("\n");
            }

            String output = b.toString();
            Matcher matcher = PATTERN.matcher(output);
            matcher.find();
            String parsed = matcher.group(1);

            exec.waitFor();

            return new UiCAOutput(Double.parseDouble(parsed));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
