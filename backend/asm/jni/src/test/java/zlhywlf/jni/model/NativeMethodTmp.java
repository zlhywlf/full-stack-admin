package zlhywlf.jni.model;

import java.io.IOException;

public class NativeMethodTmp implements NativeMethod {


    @Override
    public native void demo(String param, int a1, char a2, byte a3, short a4, long a5, float a6, double a7, boolean a8, String[] a9) throws IOException, ArrayIndexOutOfBoundsException;
}
