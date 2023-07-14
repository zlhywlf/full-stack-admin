package zlhywlf.jni.model;

import org.springframework.stereotype.Component;

@Component
public class NativeMethodImplProxy implements NativeMethod {
    NativeMethod server = new NativeMethodImpl();

    @Override
    public String demo(String param) {
        return server.demo(param);
    }

}
