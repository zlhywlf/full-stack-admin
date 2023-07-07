package zlhywlf.classfile.cnst;

public final class ClassFileConst {

    public static final int MAGIC_VALUE = 0xCAFEBABE;

    public static final String BASE_INDENT = "    ";
    public static final String BASE_FORMAT = "%-20s \t %s";
    public static final String BASE_FORMAT_V = BASE_FORMAT + " --> ";

    public static final byte CONSTANT_CLASS = 7;
    public static final byte CONSTANT_FIELDREF = 9;
    public static final byte CONSTANT_METHODREF = 10;
    public static final byte CONSTANT_INTERFACEMETHODREF = 11;
    public static final byte CONSTANT_STRING = 8;
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

    public static final String ATTRIBUTE_CONSTANTVALUE = "ConstantValue";
    public static final String ATTRIBUTE_CODE = "Code";
    public static final String ATTRIBUTE_STACKMAPTABLE = "StackMapTable";
    public static final String ATTRIBUTE_EXCEPTIONS = "Exceptions";
    public static final String ATTRIBUTE_INNERCLASSES = "InnerClasses";
    public static final String ATTRIBUTE_ENCLOSINGMETHOD = "EnclosingMethod";
    public static final String ATTRIBUTE_SYNTHETIC = "Synthetic";
    public static final String ATTRIBUTE_SIGNATURE = "Signature";
    public static final String ATTRIBUTE_SOURCEFILE = "SourceFile";
    public static final String ATTRIBUTE_SOURCEDEBUGEXTENSION = "SourceDebugExtension";
    public static final String ATTRIBUTE_LINENUMBERTABLE = "LineNumberTable";
    public static final String ATTRIBUTE_LOCALVARIABLETABLE = "LocalVariableTable";
    public static final String ATTRIBUTE_LOCALVARIABLETYPETABLE = "LocalVariableTypeTable";
    public static final String ATTRIBUTE_DEPRECATED = "Deprecated";
    public static final String ATTRIBUTE_RUNTIMEVISIBLEANNOTATIONS = "RuntimeVisibleAnnotations";
    public static final String ATTRIBUTE_RUNTIMEINVISIBLEANNOTATIONS = "RuntimeInvisibleAnnotations";
    public static final String ATTRIBUTE_RUNTIMEVISIBLEPARAMETERANNOTATIONS = "RuntimeVisibleParameterAnnotations";
    public static final String ATTRIBUTE_RUNTIMEINVISIBLEPARAMETERANNOTATIONS = "RuntimeInvisibleParameterAnnotations";
    public static final String ATTRIBUTE_RUNTIMEVISIBLETYPEANNOTATIONS = "RuntimeVisibleTypeAnnotations";
    public static final String ATTRIBUTE_RUNTIMEINVISIBLETYPEANNOTATIONS = "RuntimeInvisibleTypeAnnotations";
    public static final String ATTRIBUTE_ANNOTATIONDEFAULT = "AnnotationDefault";
    public static final String ATTRIBUTE_BOOTSTRAPMETHODS = "BootstrapMethods";
    public static final String ATTRIBUTE_METHODPARAMETERS = "MethodParameters";
    public static final String ATTRIBUTE_MODULE = "Module";
    public static final String ATTRIBUTE_MODULEPACKAGES = "ModulePackages";
    public static final String ATTRIBUTE_MODULEMAINCLASS = "ModuleMainClass";
    public static final String ATTRIBUTE_NESTHOST = "NestHost";
    public static final String ATTRIBUTE_NESTMEMBERS = "NestMembers";
    public static final String ATTRIBUTE_RECORD = "Record";
    public static final String ATTRIBUTE_PERMITTEDSUBCLASSES = "PermittedSubclasses";

}
