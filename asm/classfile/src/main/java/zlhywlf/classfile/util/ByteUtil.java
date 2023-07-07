package zlhywlf.classfile.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public final class ByteUtil {

    public static int toUnsignedInt(byte[] bytes) {
        if (bytes == null || bytes.length < 1) {
            return 0;
        }
        int sum = 0;
        for (byte b : bytes) {
            sum = (sum << 8) + (b & 0xFF);
        }
        return sum;
    }

    public static byte[] merge(byte[]... bytesArr) {
        if (bytesArr == null || bytesArr.length < 1) {
            return null;
        }
        try (ByteArrayOutputStream bao = new ByteArrayOutputStream()) {
            for (byte[] bytes : bytesArr) {
                if (bytes != null && bytes.length > 0) {
                    bao.write(bytes);
                }
            }
            return bao.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toString(byte[] bytes) {
        return new String(bytes);
    }

    public static double toDouble(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Double.BYTES);
        buffer.put(bytes);
        buffer.flip();
        return buffer.getDouble();
    }

    public static float toFloat(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Float.BYTES);
        buffer.put(bytes);
        buffer.flip();
        return buffer.getFloat();
    }

    public static long toLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(bytes);
        buffer.flip();
        return buffer.getLong();
    }

    public static int toInt(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.put(bytes);
        buffer.flip();
        return buffer.getInt();
    }

}
