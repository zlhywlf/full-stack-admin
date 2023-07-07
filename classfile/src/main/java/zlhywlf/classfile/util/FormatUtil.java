package zlhywlf.classfile.util;

import java.util.Formatter;

import zlhywlf.classfile.cnst.ClassFileConst;
import zlhywlf.classfile.model.Constant;

public class FormatUtil {
    private static final String INDENT = ClassFileConst.BASE_INDENT + ClassFileConst.BASE_INDENT + " ";

    public static void baseFormat(Formatter f, String valueFormat, Object... obj) {
        f.format(INDENT + ClassFileConst.BASE_FORMAT_V + valueFormat, obj);
    }

    public static void format(Formatter f, Object... obj) {
        baseFormat(f, "%s%n", obj);
    }

    public static void formatConstant(Formatter f, Constant c, String name) {
        format(f, "tag", HexUtil.format(c.getTag()), name);
    }

    public static void formatIndex(Formatter f, String name, byte[] bytes) {
        baseFormat(f, "#%03d%n", name,
                HexUtil.format(bytes), ByteUtil.toUnsignedInt(bytes));
    }

    public static void formatItem(Formatter f, int id, byte[]... bytes) {
        f.format(ClassFileConst.BASE_INDENT + "%03d: %s%n", id, HexUtil.format(ByteUtil.merge(bytes)));
    }

    public static void formatString(Formatter f, String name, byte[] bytes) {
        format(f, name, HexUtil.format(bytes), ByteUtil.toString(bytes));
    }

    public static void formatString(Formatter f, String name, String str) {
        f.format(INDENT + ClassFileConst.BASE_FORMAT + "%n", name, str);
    }

    public static <T> void formatNumber(Formatter f, String name, byte[] bytes, T value) {
        format(f, name, HexUtil.format(bytes), String.valueOf(value));
    }

    public static <T> void formatNumber(Formatter f, byte[] bytes, T value) {
        formatNumber(f, "value", bytes, value);
    }

    public static void formatBinary(Formatter f, String name, byte[] bytes) {
        format(f, name, HexUtil.format(bytes),
                "0b" + Integer.toBinaryString(1 << 16 | ByteUtil.toUnsignedInt(bytes)).substring(1));
    }

}
