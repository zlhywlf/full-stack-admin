package zlhywlf.hd;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HadoopTest {

    Configuration configuration;
    FileSystem fs;

    @BeforeAll
    void init() throws URISyntaxException, IOException, InterruptedException {
        configuration = new Configuration();
        fs = FileSystem.get(new URI("hdfs://192.168.50.54:9000"), configuration, "root");
    }

    @AfterAll
    void close() throws IOException {
        fs.close();
    }

    @Test
    @DisplayName("创建目录")
    void mkdirs() throws IOException {
        boolean isOk = fs.mkdirs(new Path("/test/dir"));
        log.info("创建目录: {}", isOk);
    }

}
