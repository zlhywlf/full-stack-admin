package zlhywlf.spring.cloud.member.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberRequest {
    @NotBlank(message = "mobile should not null!")
    private String mobile;
}