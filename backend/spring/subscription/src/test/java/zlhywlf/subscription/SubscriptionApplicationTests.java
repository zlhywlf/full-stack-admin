package zlhywlf.subscription;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zlhywlf.subscription.model.Info;
import zlhywlf.subscription.repository.InfoRepository;
import zlhywlf.subscription.service.InfoService;

@SpringBootTest(args = {"--server.port=8080"})
class SubscriptionApplicationTests {

    @Autowired
    InfoRepository repository;
    @Autowired
    InfoService service;

    @Test
    void getInfo() {
        repository.findAll().forEach(System.out::println);
    }

    @Test
    void addInfo() {
        Info info = new Info();
        info.setId(1);
        info.setCode("ClientId");
        info.setAccessToken("Secret");
        info.setRefreshToken("false");
        repository.save(info);
    }

}
