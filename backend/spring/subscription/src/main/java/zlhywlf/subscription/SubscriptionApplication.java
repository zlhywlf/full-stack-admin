package zlhywlf.subscription;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import zlhywlf.subscription.util.HttpUtil;

@Slf4j
@ConfigurationPropertiesScan
@EnableScheduling
@SpringBootApplication
public class SubscriptionApplication implements SchedulingConfigurer {

    @Value("${server.port}")
    private String port;

    public static void main(String[] args) {
        SpringApplication.run(SubscriptionApplication.class, args);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(() -> log.info("执行定时任务,当前列表数量{}",
                HttpUtil.get("http://localhost:" + port + "/queryData", HttpUtil::getResponseStringFromConn)),
                triggerContext -> new CronTrigger("0 0 0/2 * * ?").nextExecution(triggerContext));
    }

}
