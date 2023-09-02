package zlhywlf.spring.cloud.member;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
@SpringBootApplication
@ComponentScan("zlhywlf.spring.cloud")
@MapperScan("zlhywlf.spring.cloud.member.mapper")
public class MemberApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MemberApplication.class);
        ConfigurableEnvironment env = app.run(args).getEnvironment();
        log.info("http://localhost:{}{}", env.getProperty("server.port"), env.getProperty("server.servlet.context-path"));
    }
}