package zlhywlf.javaasm.builder;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import zlhywlf.javaasm.cnst.ClassFileConst;
import zlhywlf.javaasm.helper.BytesReader;
import zlhywlf.javaasm.model.Constant;
import zlhywlf.javaasm.model.cp.ConstantClass;

/**
 * 构建常量池解析策略
 * 
 * @author zlhywlf
 */
public class ConstantParserStrategyBuilder {

    public Map<Byte, BiFunction<Integer, BytesReader, Constant>> build() {
        Map<Byte, BiFunction<Integer, BytesReader, Constant>> strategyMap = new HashMap<>(17);
        strategyMap.put(ClassFileConst.CONSTANT_CLASS, constantClassParser);
        return strategyMap;
    }

    private final BiFunction<Integer, BytesReader, Constant> constantClassParser = (id, reader) -> {
        ConstantClass constant = new ConstantClass();
        constant.setId(id);
        constant.setTag(reader.next1());
        constant.setNameIndexBytes(reader.next2());
        return constant;
    };

}
