package zlhywlf.subscription;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zlhywlf.subscription.model.Info;
import zlhywlf.subscription.repository.InfoRepository;

@SpringBootTest
class SubscriptionApplicationTests {

    @Autowired
    InfoRepository repository;

    @Test
    void getInfo() {
        repository.findAll().forEach(System.out::println);
    }

    @Test
    void addInfo() {
        Info info = new Info();
        info.setClientId("ClientId");
        info.setSecret("Secret");
        info.setValid(false);
        repository.save(info);
    }

}
