package zlhywlf.javaagent;

import java.lang.instrument.Instrumentation;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.debug("main args : {}", Arrays.toString(args));
    }

    public static void premain(String args, Instrumentation inst) {
        log.debug("premain args : {}", args);
    }

    public static void agentmain(String args, Instrumentation inst) {
        log.debug("agentmain args : {}", args);
    }
}
