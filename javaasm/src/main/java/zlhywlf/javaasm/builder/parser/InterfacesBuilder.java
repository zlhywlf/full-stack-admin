package zlhywlf.javaasm.builder.parser;

import lombok.RequiredArgsConstructor;
import zlhywlf.javaasm.helper.BytesReader;
import zlhywlf.javaasm.model.Node;

/**
 * 构建接口
 * 
 * @author zlhywlf
 */
@RequiredArgsConstructor
public class InterfacesBuilder {

    private final int count;
    private final BytesReader reader;

    public Node[] build() {
        Node[] nodes = new Node[count];
        for (int i = 0; i < count; i++) {
            Node node = new Node();
            node.setBytes(reader.next2());
            nodes[i] = node;
        }
        return nodes;
    }

}
