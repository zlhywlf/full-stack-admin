package zlhywlf.classfile.visitor.simple;

import java.util.Formatter;
import java.util.function.BiConsumer;

import lombok.RequiredArgsConstructor;
import zlhywlf.classfile.model.Interface;
import zlhywlf.classfile.util.FormatUtil;

@RequiredArgsConstructor
public class InterfaceSimpleVisitor implements BiConsumer<Formatter, Interface> {

    private final BiConsumer<Formatter, Interface> raw;

    @Override
    public void accept(Formatter t, Interface u) {
        raw.accept(t, u);
        FormatUtil.formatIndex(t, "nameIndex", u.getBytes());
    }

}
