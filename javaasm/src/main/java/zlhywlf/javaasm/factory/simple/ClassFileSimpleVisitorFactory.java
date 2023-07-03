package zlhywlf.javaasm.factory.simple;

import zlhywlf.javaasm.builder.visitor.ConstantsVisitorStrategyBuilder;
import zlhywlf.javaasm.factory.ClassFileVisitorFactory;
import zlhywlf.javaasm.visitor.ClassFileVisitor;
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

public class ClassFileSimpleVisitorFactory implements ClassFileVisitorFactory {

    @Override
    public ClassFileVisitor create() {
        return new ClassFileSimpleVisitor(
                new ClassFileRawVisitor(
                        new ConstantsVisitorStrategyBuilder(
                                new ConstantsSimpleVisitor(new ConstantsRawVisitor()))
                                .build(),
                        new InterfaceSimpleVisitor(new InterfaceRawVisitor()),
                        new MemberSimpleVisitor(new MemberRawVisitor(),
                                new AttributeSimpleVisitor((f, n) -> {
                                })),
                        new AttributeSimpleVisitor(new AttributeRawVisitor())));
    }

}