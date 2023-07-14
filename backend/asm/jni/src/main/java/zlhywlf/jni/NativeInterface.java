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
     * 例如：
     * name="NativeMethod" <==> libNativeMethod.so
     */
    String name() default "";

    /**
     * 实现类额外注解，需要使用注解的全限定名，多个注解使用 \n 分隔。
     */
    String anno() default "@org.springframework.stereotype.Component";

    /**
     * 方法执行完后是否卸载动态库
     * 设置为 true 时注意：
     * 方法执行开始时加载动态库，方法执行结束时卸载动态库，性能较低
     * 同时，动态库构建模式需选择 MinSizeRel 或 Debug，如果以 Release 构建，可能无法完成卸载操作
     * 如果必须以 Release 构建，可采用 C 方法进行加载与卸载
     * 特别注意，存在 STB_GNU_UNIQUE 符号的动态库是不可卸载的，但执行卸载操作不会报错
     */
    boolean unload() default false;

    /**
     * 未找到对应动态库时的方法返回值，可在动态库未完成开发时，开发阶段返回默认数据使用
     * 如果加载了动态库而没有与之匹配的本地方法会直接报错
     */
    String defReturn() default "return \"{\\\"code\\\":1,\\\"data\\\":null,\\\"msg\\\":\\\"\"+ e.getMessage() +\"\\\"}\";";

}
