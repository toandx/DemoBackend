package toandx.render.demoBackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SignUpReq {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;
}
