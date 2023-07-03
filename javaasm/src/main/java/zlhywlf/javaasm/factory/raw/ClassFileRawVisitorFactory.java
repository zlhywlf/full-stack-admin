package zlhywlf.javaasm.factory.raw;

import zlhywlf.javaasm.builder.visitor.ConstantsVisitorStrategyBuilder;
import zlhywlf.javaasm.factory.ClassFileVisitorFactory;
import zlhywlf.javaasm.visitor.ClassFileVisitor;
import zlhywlf.javaasm.visitor.raw.AttributeRawVisitor;
import zlhywlf.javaasm.visitor.raw.ClassFileRawVisitor;
import zlhywlf.javaasm.visitor.raw.ConstantsRawVisitor;
import zlhywlf.javaasm.visitor.raw.InterfaceRawVisitor;
import zlhywlf.javaasm.visitor.raw.MemberRawVisitor;

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
