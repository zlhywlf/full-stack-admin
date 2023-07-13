package zlhywlf.classfile.factory.raw;

import zlhywlf.classfile.builder.visitor.ConstantsVisitorStrategyBuilder;
import zlhywlf.classfile.factory.ClassFileVisitorFactory;
import zlhywlf.classfile.visitor.ClassFileVisitor;
import zlhywlf.classfile.visitor.raw.AttributeRawVisitor;
import zlhywlf.classfile.visitor.raw.ClassFileRawVisitor;
import zlhywlf.classfile.visitor.raw.ConstantsRawVisitor;
import zlhywlf.classfile.visitor.raw.InterfaceRawVisitor;
import zlhywlf.classfile.visitor.raw.MemberRawVisitor;

public class ClassFileRawVisitorFactory implements ClassFileVisitorFactory {

    @Override
    public ClassFileVisitor create() {
        return new ClassFileRawVisitor(
                new ConstantsVisitorStrategyBuilder(new ConstantsRawVisitor()).build(),
                new InterfaceRawVisitor(),
                new MemberRawVisitor(),
                new AttributeRawVisitor());
    }

}
