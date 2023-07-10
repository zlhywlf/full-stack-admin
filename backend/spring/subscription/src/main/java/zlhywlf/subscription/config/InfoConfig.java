package zlhywlf.subscription.config;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ds")
@Setter
public class InfoConfig {

    public String authorityUrl;
    public String tokenUrl;
    public String scope;
    public String redirectUrl;
    public String clientId;
    public String secret;

}
