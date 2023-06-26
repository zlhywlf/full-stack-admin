package zlhywlf.javaagent;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import lombok.extern.slf4j.Slf4j;

@TestInstance(Lifecycle.PER_CLASS)
@Slf4j
public class AppTest {
    @Test
    void appTest() {
        log.info("hello world!");
    }
}
