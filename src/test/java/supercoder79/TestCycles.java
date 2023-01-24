package supercoder79;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestCycles {
    @Test
    public void test1() {
        File compile = ProcHelper.compile("""
                l:
                    addq %rax, %rbx
                    addq %rbx, %rax
                    dec %r15
                    jnz l
                """);

        assertEquals(2.0, UiCA.run(compile).cyclesPerItr());
    }
}
