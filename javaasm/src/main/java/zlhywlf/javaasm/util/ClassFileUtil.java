package zlhywlf.javaasm.util;

import java.util.Formatter;
import java.util.function.BiConsumer;

import zlhywlf.javaasm.builder.parser.ClassFileBuilder;
import zlhywlf.javaasm.builder.visitor.ClassFileVisitorConsumerBuilder;
import zlhywlf.javaasm.factory.raw.ClassFileRawVisitorFactory;
import zlhywlf.javaasm.factory.simple.ClassFileSimpleVisitorFactory;
import zlhywlf.javaasm.helper.BytesReader;
import zlhywlf.javaasm.model.ClassFile;

public class ClassFileUtil {

    public static String parse(byte[] bytes, BiConsumer<ClassFile, Formatter> visitorConsumer) {
        ClassFile classFile = new ClassFileBuilder(new BytesReader(bytes)).build();
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        visitorConsumer.accept(classFile, fm);
        fm.close();
        return sb.toString();
    }

    public static String parse(byte[] bytes) {
        return HexUtil.format(bytes, HexUtil.HexFormat.FORMAT_FF_SPACE_FF_32);
    }

    public static String parseRaw(byte[] bytes) {
        return parse(bytes, new ClassFileVisitorConsumerBuilder(new ClassFileRawVisitorFactory().create())
                .build());
    }

    public static String parseSimple(byte[] bytes) {
        return parse(bytes, new ClassFileVisitorConsumerBuilder(new ClassFileSimpleVisitorFactory().create())
                .build());
    }

}
