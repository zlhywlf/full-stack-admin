package zlhywlf.javaasm.builder.parser;

import java.util.function.BiFunction;

import java.util.Map;

import lombok.RequiredArgsConstructor;
import zlhywlf.javaasm.cnst.ClassFileConst;
import zlhywlf.javaasm.helper.BytesReader;
import zlhywlf.javaasm.model.Constant;

/**
 * 构建常量池
 * 
 * @author zlhywlf
 */
@RequiredArgsConstructor
public class ConstantsBuilder {

    private final int count;
    private final BytesReader reader;

    public Constant[] build() {
        Constant[] constants = new Constant[count];
        Map<Byte, BiFunction<Integer, BytesReader, Constant>> strategyMap = new ConstantParserStrategyBuilder().build();
        for (int i = 1; i < count; i++) {
            byte tag = reader.peek();
            constants[i] = strategyMap.getOrDefault(tag, (id, b) -> {
                throw new RuntimeException(String.format("解析第 %d 常量出现错误：无效常量池类型: %d", id, tag));
            }).apply(i, reader);
            // All 8-byte constants take up two entries in the constant_pool table of the
            // class file.
            // https://docs.oracle.com/javase/specs/jvms/se17/html/jvms-4.html#jvms-4.4
            if (tag == ClassFileConst.CONSTANT_DOUBLE || tag == ClassFileConst.CONSTANT_LONG) {
                i++;
            }
        }
        return constants;
    }

}
