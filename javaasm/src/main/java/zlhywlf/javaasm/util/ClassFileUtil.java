package zlhywlf.javaasm.util;

import java.util.Formatter;
import java.util.function.BiConsumer;

import zlhywlf.javaasm.builder.parser.ClassFileBuilder;
import zlhywlf.javaasm.builder.visitor.ClassFileVisitorConsumerBuilder;
import zlhywlf.javaasm.builder.visitor.ConstantsVisitorStrategyBuilder;
import zlhywlf.javaasm.helper.BytesReader;
import zlhywlf.javaasm.model.ClassFile;
import zlhywlf.javaasm.visitor.raw.AttributeRawVisitor;
import zlhywlf.javaasm.visitor.raw.ClassFileRawVisitor;
import zlhywlf.javaasm.visitor.raw.ConstantsRawVisitor;
import zlhywlf.javaasm.visitor.raw.InterfaceRawVisitor;
import zlhywlf.javaasm.visitor.raw.MemberRawVisitor;
import zlhywlf.javaasm.visitor.simple.AttributeSimpleVisitor;
import zlhywlf.javaasm.visitor.simple.ClassFileSimpleVisitor;
import zlhywlf.javaasm.visitor.simple.ConstantsSimpleVisitor;
import zlhywlf.javaasm.visitor.simple.InterfaceSimpleVisitor;
import zlhywlf.javaasm.visitor.simple.MemberSimpleVisitor;

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
        var constantsVisitorStrategy = new ConstantsVisitorStrategyBuilder(new ConstantsRawVisitor()).build();
        ClassFileRawVisitor classFileRawVisitor = new ClassFileRawVisitor(
                constantsVisitorStrategy,
                new InterfaceRawVisitor(),
                new MemberRawVisitor(),
                new AttributeRawVisitor());
        var classFileVisitorConsumer = new ClassFileVisitorConsumerBuilder(classFileRawVisitor).build();
        return parse(bytes, classFileVisitorConsumer);
    }

    public static String parseSimple(byte[] bytes) {
        var constantsVisitorStrategy = new ConstantsVisitorStrategyBuilder(
                new ConstantsSimpleVisitor(new ConstantsRawVisitor())).build();
        ClassFileSimpleVisitor classFileRawVisitor = new ClassFileSimpleVisitor(
                new ClassFileRawVisitor(
                        constantsVisitorStrategy,
                        new InterfaceSimpleVisitor(new InterfaceRawVisitor()),
                        new MemberSimpleVisitor(new MemberRawVisitor(), new AttributeSimpleVisitor((f, n) -> {
                        })),
                        new AttributeSimpleVisitor(new AttributeRawVisitor())));
        var classFileVisitorConsumer = new ClassFileVisitorConsumerBuilder(classFileRawVisitor).build();
        return parse(bytes, classFileVisitorConsumer);
    }

}
