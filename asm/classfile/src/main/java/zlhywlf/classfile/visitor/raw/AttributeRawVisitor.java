package zlhywlf.classfile.visitor.raw;

import java.util.Formatter;
import java.util.function.BiConsumer;

import zlhywlf.classfile.model.Attribute;
import zlhywlf.classfile.util.FormatUtil;

public class AttributeRawVisitor implements BiConsumer<Formatter, Attribute> {

    @Override
    public void accept(Formatter t, Attribute u) {
        FormatUtil.formatItem(t, u.getId(), u.getAttributeNameIndexBytes(), u.getAttributeLengthBytes(),
                u.getInfoBytes());
    }

}
