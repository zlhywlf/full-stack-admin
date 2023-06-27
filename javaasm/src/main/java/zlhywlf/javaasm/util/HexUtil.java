package zlhywlf.javaasm.util;

import java.util.Formatter;

import lombok.RequiredArgsConstructor;

public class HexUtil {

    public static String format(byte[] bytes, HexFormat format) {
        if (bytes == null || bytes.length < 1) {
            return "";
        }
        String separator = format.separator;
        int columns = format.columns;
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        int length = bytes.length;
        for (int i = 0; i < length - 1; i++) {
            fm.format("%02X", bytes[i] & 0xFF);
            if (columns > 0 && (i + 1) % columns == 0) {
                fm.format("%n");
            } else {
                fm.format("%s", separator);
            }
        }
        fm.format("%02X", bytes[length - 1] & 0xFF);
        fm.close();
        return sb.toString();
    }

    @RequiredArgsConstructor
    public static enum HexFormat {
        FORMAT_FF_FF("", 0),
        FORMAT_FF_SPACE_FF_32(" ", 32);

        public final String separator;
        public final int columns;
    }
}
