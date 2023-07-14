package zlhywlf.jni.model;

import org.springframework.stereotype.Component;

@Component
public class NativeMethodImpl implements NativeMethod {

    @Override
    public String demo(String param) {
        System.out.println(param);
        return param;
    }

}
