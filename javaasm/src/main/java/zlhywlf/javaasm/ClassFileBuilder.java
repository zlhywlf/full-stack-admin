package zlhywlf.javaasm;

import java.util.function.Supplier;

import lombok.RequiredArgsConstructor;
import zlhywlf.javaasm.model.ClassFile;
import zlhywlf.javaasm.model.Node;
import zlhywlf.javaasm.parser.Magic;

/**
 * 构建 ClassFile
 * 
 * @author zlhywlf
 */
@RequiredArgsConstructor
public class ClassFileBuilder {

    private final BytesReader reader;
    private ClassFile classFile = new ClassFile();

    public ClassFile build() {
        classFile.setMagic(parse(new Magic(reader)));
        return classFile;
    }

    private Node parse(Supplier<Node> s) {
        return s.get();
    }

}
