package zlhywlf.javaasm.classfile.node;

import java.util.Formatter;

import zlhywlf.javaasm.classfile.ClassFileReader;
import zlhywlf.javaasm.classfile.node.field.FieldInfo;
import zlhywlf.javaasm.classfile.visitor.Visitor;
import zlhywlf.javaasm.util.ByteUtil;

/**
 * field_info fields[fields_count];
 * 
 * @author zlhywlf
 */
public class Fields extends Node {

    private FieldInfo[] fieldInfos;

    public Fields(ClassFileReader reader, Formatter fm) {
        super(12, reader, fm);
    }

    @Override
    public void accept(Visitor v) {
        v.visitFields(this.init());
        for (FieldInfo f : fieldInfos) {
            f.accept(v);
        }
    }

    @Override
    protected Node init() {
        int count = ByteUtil.toInt(reader.previous(2, 2));
        fieldInfos = new FieldInfo[count];
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                fieldInfos[i] = new FieldInfo(i + 1, reader, fm);
            }
        }
        return this;
    }

}
