package zlhywlf.javaasm.util;

import java.util.Formatter;
import java.util.function.BiConsumer;

import zlhywlf.javaasm.builder.parser.ClassFileBuilder;
import zlhywlf.javaasm.helper.BytesReader;
import zlhywlf.javaasm.model.ClassFile;

public class ClassFileUtil {

    public static String parse(byte[] bytes, BiConsumer<ClassFile, Formatter> visitor) {
        ClassFile classFile = new ClassFileBuilder(new BytesReader(bytes)).build();
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        visitor.accept(classFile, fm);
        fm.close();
        return sb.toString();
    }

    public static String parse(byte[] bytes) {
        return HexUtil.format(bytes, HexUtil.HexFormat.FORMAT_FF_SPACE_FF_32);
    }

}
