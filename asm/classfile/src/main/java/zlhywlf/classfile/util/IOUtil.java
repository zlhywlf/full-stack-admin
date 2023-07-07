package zlhywlf.classfile.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class IOUtil {

    public static final int EOF = -1;

    private static final int DEFAULT_BUFFER_SIZE = 1024 * 8;

    public static void copy(final InputStream input, final OutputStream output) throws IOException {
        copy(input, output, new byte[DEFAULT_BUFFER_SIZE]);
    }

    public static void copy(final InputStream input, final OutputStream output, final byte[] buffer)
            throws IOException {
        long count = 0;
        int n;
        while (EOF != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
    }

}
