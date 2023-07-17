package zlhywlf.jni.model;

import zlhywlf.jni.NativeInterface;

import java.io.IOException;

@NativeInterface(name = "native", anno = {"org.springframework.stereotype.Component"})
public interface NativeMethod {

    void demo(String a0, int a1, char a2, byte a3, short a4, long a5, float a6, double a7, boolean a8, String[] a9) throws IOException, ArrayIndexOutOfBoundsException;

}
