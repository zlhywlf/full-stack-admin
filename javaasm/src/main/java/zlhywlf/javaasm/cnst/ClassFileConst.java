package zlhywlf.javaasm.cnst;

public final class ClassFileConst {

    public static final int MAGIC_VALUE = 0xCAFEBABE;
    public static final int MAGIC_SIZE = 4;
    public static final int MINOR_VERSION_SIZE = 2;
    public static final int MAJOR_VERSION_SIZE = 2;
    public static final int CONSTANT_POOL_COUNT_SIZE = 2;

    public static final byte CONSTANT_CLASS = 7;
    public static final byte CONSTANT_FIELDREF = 9;
    public static final byte CONSTANT_METHODREF = 10;
    public static final byte CONSTANT_INTERFACEMETHODREF = 11;
    public static final byte CONSTANT_String = 8;
    public static final byte CONSTANT_INTEGER = 3;
    public static final byte CONSTANT_FLOAT = 4;
    public static final byte CONSTANT_LONG = 5;
    public static final byte CONSTANT_DOUBLE = 6;
    public static final byte CONSTANT_NAMEANDTYPE = 12;
    public static final byte CONSTANT_UTF8 = 1;
    public static final byte CONSTANT_METHODHANDLE = 15;
    public static final byte CONSTANT_METHODTYPE = 16;
    public static final byte CONSTANT_DYNAMIC = 17;
    public static final byte CONSTANT_INVOKEDYNAMIC = 18;
    public static final byte CONSTANT_MODULE = 19;
    public static final byte CONSTANT_PACKAGE = 20;

}
