package zlhywlf.javaasm.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtil {
    public static String getFilePath(String relativePath) {
        String dir = FileUtil.class.getResource("/").getPath();
        return dir + relativePath;
    }

    public static byte[] readFileAsBytes(String filepath) {
        File file = new File(filepath);
        if (!file.exists()) {
            throw new IllegalArgumentException("文件不存在: " + filepath);
        }
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));) {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            IOUtil.copy(in, bao);
            return bao.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
