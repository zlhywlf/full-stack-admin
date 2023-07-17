package zlhywlf.jni;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface NativeInterface {

    /**
     * 动态库名称，默认为接口名
     */
    String name() default "";

    /**
     * 实现类额外注解，需要使用全限定名。
     */
    String[] anno() default {};

}
