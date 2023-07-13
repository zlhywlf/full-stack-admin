package zlhywlf.subscription.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Lob
    private String code;
    @Lob
    private String accessToken;
    @Lob
    private String refreshToken;

}
