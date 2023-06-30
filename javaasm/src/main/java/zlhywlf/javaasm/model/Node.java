package zlhywlf.javaasm.model;

import lombok.Getter;
import lombok.Setter;

/**
 * ClassFile 节点
 * id: 节点位置索引
 * bytes: 原始字节
 * value: 节点格式化字串
 * 
 * @author zlhywlf
 */
@Getter
@Setter
public class Node {
    private int id;
    private byte[] bytes;
    private String value;
}
