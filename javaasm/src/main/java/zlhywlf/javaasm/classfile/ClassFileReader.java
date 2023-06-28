package zlhywlf.javaasm.classfile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClassFileReader {

    private final byte[] bytes;
    private int index;

    public byte[] next(int size) {
        int start = index;
        index += size;
        return loc(start, size);
    }

    public byte peek() {
        return this.bytes[index];
    }

    public byte[] loc(int start, int size) {
        byte[] array = new byte[size];
        System.arraycopy(this.bytes, start, array, 0, size);
        return array;
    }

}
