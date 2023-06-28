package zlhywlf.javaasm.util;

import java.util.Formatter;

import lombok.RequiredArgsConstructor;

public final class HexUtil {

    public static String format(byte[] bytes, HexFormat format) {
        if (bytes == null || bytes.length < 1) {
            return "";
        }
        String separator = format.separator;
        int columns = format.columns;
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        int length = bytes.length;
        for (int i = 1; i <= length; i++) {
            fm.format("%02X", bytes[i - 1] & 0xFF);
            if (columns > 0 && i % columns == 0) {
                fm.format("%n");
            } else if (i != length) {
                fm.format("%s", separator);
            }
        }
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
