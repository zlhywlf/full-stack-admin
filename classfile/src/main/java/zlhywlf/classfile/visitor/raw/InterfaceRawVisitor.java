package zlhywlf.classfile.visitor.raw;

import java.util.Formatter;
import java.util.function.BiConsumer;

import zlhywlf.classfile.model.Interface;
import zlhywlf.classfile.util.FormatUtil;

public class InterfaceRawVisitor implements BiConsumer<Formatter, Interface> {

    @Override
    public void accept(Formatter t, Interface u) {
        FormatUtil.formatItem(t, u.getId(), u.getBytes());
    }

}
