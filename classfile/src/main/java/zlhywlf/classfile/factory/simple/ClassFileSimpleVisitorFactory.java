package zlhywlf.classfile.factory.simple;

import zlhywlf.classfile.builder.visitor.ConstantsVisitorStrategyBuilder;
import zlhywlf.classfile.factory.ClassFileVisitorFactory;
import zlhywlf.classfile.visitor.ClassFileVisitor;
import zlhywlf.classfile.visitor.raw.AttributeRawVisitor;
import zlhywlf.classfile.visitor.raw.ClassFileRawVisitor;
import zlhywlf.classfile.visitor.raw.ConstantsRawVisitor;
import zlhywlf.classfile.visitor.raw.InterfaceRawVisitor;
import zlhywlf.classfile.visitor.raw.MemberRawVisitor;
import zlhywlf.classfile.visitor.simple.AttributeSimpleVisitor;
import zlhywlf.classfile.visitor.simple.ClassFileSimpleVisitor;
import zlhywlf.classfile.visitor.simple.ConstantsSimpleVisitor;
import zlhywlf.classfile.visitor.simple.InterfaceSimpleVisitor;
import zlhywlf.classfile.visitor.simple.MemberSimpleVisitor;

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