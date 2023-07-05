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
    }

    public static void premain(String args, Instrumentation inst) throws Exception {
        System.out.println("premain args : " + args);
        String resourcePath = "/simplelogger.properties";
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
