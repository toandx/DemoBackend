package toandx.render.demoBackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Note {
    @JsonProperty("title")
    private String title;

    @JsonProperty("content")
    private String content;
}
