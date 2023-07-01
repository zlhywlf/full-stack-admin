package zlhywlf.javaasm.visitor.raw;

import java.util.Formatter;
import java.util.function.BiConsumer;

import zlhywlf.javaasm.model.Interface;
import zlhywlf.javaasm.util.HexUtil;

public class InterfaceRawVisitor implements BiConsumer<Formatter, Interface> {

    @Override
    public void accept(Formatter t, Interface u) {
        t.format("    %03d: %s%n", u.getId(), HexUtil.format(u.getBytes()));
    }

}
