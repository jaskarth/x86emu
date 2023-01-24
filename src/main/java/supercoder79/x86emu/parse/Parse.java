package supercoder79.x86emu.parse;

import java.util.regex.Pattern;

public class Parse {
    // Extremely funny pattern that can parse all* of x86, AT&T syntax
    // *almost everything. "repne scasb" and such doesn't work (yet?)
    private static final Pattern PATTERN = Pattern.compile("(\\w+)\\s+(?:(\\$\\d+)|(%\\w+)|(?:(\\d*)\\((%\\w+)(?:,(%\\w+),(\\d+))?\\))|(\\..+))(?:,\\s+(%\\w+)|,\\s+(?:(\\d*)\\((%\\w+)(?:,(%\\w+),(\\d+))?\\)))*");
    // Group 1: Instruction name
    // The next groups are not guaranteed to exist!
    // Group 2: Source: Constant immediate
    // Group 3: Source: Direct register
    // Group 5: Source: Register pointer to memory
    // Group 4: Source: Constant memory offset
    // Group 6: Source: 2nd Register pointer to memory
    // Group 7: Source: Scalar of 2nd register pointer to memory
    // Group 8: Label pointer
    // Group 9: Destination: Direct register
    // Group 11: Destination: Register pointer to memory
    // Group 10: Destination: Constant memory offset
    // Group 12: Destination: 2nd Register pointer to memory
    // Group 13: Destination: Scalar of 2nd register pointer to memory


}
