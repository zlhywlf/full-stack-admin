package zlhywlf.javaasm.classfile.visitor;

import zlhywlf.javaasm.classfile.node.Node;

public final class RawVisitor implements Visitor {

    @Override
    public void visitMagic(Node obj) {
        visitBase(obj, "u4 magic");
    }

    @Override
    public void visitMinorVersion(Node obj) {
        visitBase(obj, "u2 minor_version");
    }

    @Override
    public void visitMajorVersion(Node obj) {
        visitBase(obj, "u2 major_version");
    }

    @Override
    public void visitConstantPoolCount(Node obj) {
        visitBase(obj, "u2 constant_pool_count");
    }

    @Override
    public void visitConstantPool(Node obj) {
        visitBase(obj, "cp_info constant_pool[constant_pool_count-1]");
    }

    private void visitBase(Node obj, String name) {
        obj.getFm().format("    %s: %s %n", name, obj.toHex());
    }

}
