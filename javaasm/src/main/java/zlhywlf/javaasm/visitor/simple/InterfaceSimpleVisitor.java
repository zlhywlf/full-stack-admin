package zlhywlf.javaasm.visitor.simple;

import java.util.Formatter;
import java.util.function.BiConsumer;

import lombok.RequiredArgsConstructor;
import zlhywlf.javaasm.model.Interface;
import zlhywlf.javaasm.util.ByteUtil;

@RequiredArgsConstructor
public class InterfaceSimpleVisitor implements BiConsumer<Formatter, Interface> {

    private final BiConsumer<Formatter, Interface> raw;

    @Override
    public void accept(Formatter t, Interface u) {
        raw.accept(t, u);
        t.format("          --> #%03d%n", ByteUtil.toUnsignedInt(u.getBytes()));
    }

}
