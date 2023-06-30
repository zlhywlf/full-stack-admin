package zlhywlf.javaasm.parser;

import java.util.function.Supplier;

import lombok.RequiredArgsConstructor;
import zlhywlf.javaasm.BytesReader;
import zlhywlf.javaasm.model.Node;

@RequiredArgsConstructor
public class Magic implements Supplier<Node> {
    private final BytesReader reader;

    @Override
    public Node get() {
        Node node = new Node();

        return node;
    }
}
