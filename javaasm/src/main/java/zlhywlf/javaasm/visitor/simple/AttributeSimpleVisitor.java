package zlhywlf.javaasm.visitor.simple;

import java.util.Formatter;
import java.util.function.BiConsumer;

import lombok.RequiredArgsConstructor;
import zlhywlf.javaasm.builder.visitor.ConstantsVisitorStrategyBuilder;
import zlhywlf.javaasm.model.Attribute;
import zlhywlf.javaasm.model.Constant;
import zlhywlf.javaasm.model.cp.ConstantUtf8;
import zlhywlf.javaasm.util.ByteUtil;

@RequiredArgsConstructor
public class AttributeSimpleVisitor implements BiConsumer<Formatter, Attribute> {

    private final BiConsumer<Formatter, Attribute> raw;

    @Override
    public void accept(Formatter t, Attribute u) {
        raw.accept(t, u);
        int index = ByteUtil.toUnsignedInt(u.getAttributeNameIndexBytes());
        Constant[] constantPool = u.getConstantPool();
        ConstantUtf8 constant = ConstantsVisitorStrategyBuilder.cast(constantPool[index], ConstantUtf8.class);
        String name = ByteUtil.toString(constant.getBytes());
        t.format("          attributeName --> #%03d(%s), length = %d%n", index, name,
                ByteUtil.toUnsignedInt(u.getAttributeLengthBytes()));

    }

}
