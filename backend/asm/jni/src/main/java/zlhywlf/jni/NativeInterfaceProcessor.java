package zlhywlf.jni;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.util.Set;

import static java.lang.String.format;

@SupportedAnnotationTypes("zlhywlf.jni.NativeInterface")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class NativeInterfaceProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> interSets = roundEnvironment.getElementsAnnotatedWith(NativeInterface.class);
        interSets.forEach(inter -> {
            if (inter.getKind() != ElementKind.INTERFACE) {
                throw new RuntimeException(format("注解 @NativeInterface 只用于接口! [%s] 类型是 [%s]", inter, inter.getKind()));
            }
            // 获取注解信息
            NativeInterface interAnno = inter.getAnnotation(NativeInterface.class);
            // 获取接口信息
            String interName = inter.getSimpleName().toString();
        });
        return true;
    }

}
