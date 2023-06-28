package zlhywlf.javaasm.util;

public final class ByteUtil {

    public static int toInt(byte[] bytes) {
        if (bytes == null || bytes.length < 1) {
            return 0;
        }
        int sum = 0;
        for (byte b : bytes) {
            sum = (sum << 8) + (b & 0xFF);
        }
        return sum;
    }

}
