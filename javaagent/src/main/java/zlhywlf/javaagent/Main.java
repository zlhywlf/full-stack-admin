package zlhywlf.javaagent;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.net.URL;
import java.util.jar.JarFile;

import zlhywlf.javaagent.filter.BigIntegerFilter;
import zlhywlf.javaagent.filter.HttpClientFilter;
import zlhywlf.javaagent.filter.InetAddressFilter;

public class Main {

    public static void main(String[] args) {
        // -javaagent:C:\PROGRA~1\JetBrains\javaagent.jar
        // PROGRA~1 == Program Files
        System.out.println("""
                    -javaagent:C:\\PROGRA~1\\JetBrains\\javaagent.jar
                    --add-opens=java.base/jdk.internal.org.objectweb.asm=ALL-UNNAMED
                    --add-opens=java.base/jdk.internal.org.objectweb.asm.tree=ALL-UNNAMED
                """);
    }

    public static void premain(String args, Instrumentation inst) throws Exception {
        String resourcePath = "/certificate.py";
        String path = Main.class.getResource(resourcePath).getPath();
        path = path.substring(0, path.length() - resourcePath.length() - 1);
        inst.appendToBootstrapClassLoaderSearch(new JarFile(new File(new URL(path).getPath())));
        inst.addTransformer(new BigIntegerFilter(), true);
        inst.addTransformer(new HttpClientFilter(), true);
        inst.addTransformer(new InetAddressFilter(), true);
    }

    public static void agentmain(String args, Instrumentation inst) {
    }

}
