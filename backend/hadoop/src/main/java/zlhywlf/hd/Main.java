package zlhywlf.hd;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public class Main {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Properties prop = Property.getProp();
                    Set<String> keys = prop.stringPropertyNames();
                    System.out.println("=========================================");
                    keys.forEach(k -> log.info("{}: {}", k, prop.getProperty(k)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 0, 2 * 1000);
    }

}

class Property {

    private static volatile Properties prop = null;

    public static Properties getProp() throws IOException {
        if (prop == null) {
            synchronized ("lock") {
                if (prop == null) {
                    prop = new Properties();
                    prop.load(Property.class.getClassLoader().getResourceAsStream("log4j.properties"));
                }
            }
        }
        return prop;
    }

}
