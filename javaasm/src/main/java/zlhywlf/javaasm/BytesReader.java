package zlhywlf.javaasm;

import lombok.RequiredArgsConstructor;

/**
 * 辅助按序解析字节码数据
 * 
 * @author zlhywlf
 */
@RequiredArgsConstructor
public class BytesReader {
    private final byte[] bytes;
    private int index;

    public byte[] next(int size) {
        int start = index;
        index += size;
        byte[] res = new byte[size];
        System.arraycopy(bytes, start, res, 0, size);
        return res;
    }

    public byte[] next1() {
        return next(1);
    }

    public byte[] next2() {
        return next(2);
    }

    public byte[] next4() {
        return next(4);
    }

    public byte peek() {
        return this.bytes[index];
    }

}
