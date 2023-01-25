package supercoder79.x86emu.parse;

import supercoder79.x86emu.simulate.Immediate;
import supercoder79.x86emu.simulate.Value;
import supercoder79.x86emu.simulate.ValueType;
import supercoder79.x86emu.simulate.register.RegisterSet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static supercoder79.x86emu.simulate.ValueType.*;

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

    public static InstrList parse(RegisterSet set, String asm) {
        InstrList list = new InstrList();

        String[] split = asm.split("\n");

        for (String line : split) {
            Matcher matcher = PATTERN.matcher(line);
            if (matcher.find()) {
                String instrName = matcher.group(1);

                Value source;
                ValueType typeA = r64;
                if (matcher.group(2) != null) {
                    // Remove $
                    Immediate imm = new Immediate(Long.parseUnsignedLong(matcher.group(2).substring(1)));
                    source = imm;
                    typeA = guess(imm);
                    imm.setType(typeA);
                } else if (matcher.group(3) != null) {
                    // Remove %
                    String reg = matcher.group(3).substring(1);
                    source = set.getReg(reg);
                    typeA = set.regType(reg);
                } else if (matcher.group(5) != null) {
                    source = null;
                } else {
                    throw new IllegalArgumentException("What source does this line have? " + line);
                }

                Value destination;
                ValueType typeB = r64;
                if (matcher.group(9) != null) {
                    // Remove %
                    String reg = matcher.group(9).substring(1);
                    destination = set.getReg(reg);
                    typeB = set.regType(reg);
                } else if (matcher.group(11) != null) {
                    destination = null;
                } else {
                    throw new IllegalArgumentException("What destination does this line have? " + line);
                }

                list.add(InstrParse.makeBinary(instrName, set, source, destination, typeA, typeB));
            } else {
                throw new IllegalArgumentException("Dunno how to parse " + line);
            }

        }

        return list;
    }

    // FIXME: make sure this works with unsigned numbers
    private static ValueType guess(Immediate imm) {
        long l = imm.v64();

        String s = Long.toBinaryString(l);
        if (s.length() <= 8) {
            return r8;
        } else if (s.length() <= 16) {
            return r16;
        } else if (s.length() <= 32) {
            return r32;
        }

        return r64;
    }
}
