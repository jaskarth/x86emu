package supercoder79;

import java.io.*;
import java.nio.file.Files;

public class ProcHelper {
    public static File compile(String asm) {
        try {
            File asmFile = File.createTempFile("temp", ".asm");
            Files.writeString(asmFile.toPath(), asm);

            File objFile = File.createTempFile("temp", ".obj");

            Process exec = Runtime.getRuntime().exec("as " + asmFile.getAbsolutePath() + " -o " + objFile.getAbsolutePath());

            sout(exec.getErrorStream());
            sout(exec.getInputStream());

            exec.waitFor();

            return objFile;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void sout(InputStream exec) throws IOException {
        BufferedReader serr = new BufferedReader(new InputStreamReader(exec));

        String s;

        while ((s = serr.readLine()) != null) {
            System.out.println(s);
        }
    }
}
