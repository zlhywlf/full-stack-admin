package zlhywlf.javaasm.classfile.node;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.node.method.MethodInfo;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * method_info methods[methods_count];
 * 
 * @author zlhywlf
 */
public class Methods extends Node {

    private MethodInfo[] methodInfos;

    public Methods(ClassFileReader reader, Formatter fm) {
        super(14, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitMethods(this.init());
        for (MethodInfo m : methodInfos) {
            m.accept(v);
        }
    }

    @Override
    protected Node init() {
        int count = ByteUtil.toInt(reader.previous(2, 2));
        methodInfos = new MethodInfo[count];
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                methodInfos[i] = new MethodInfo(i + 1, reader, fm);
            }
        }
        return this;
    }

}
