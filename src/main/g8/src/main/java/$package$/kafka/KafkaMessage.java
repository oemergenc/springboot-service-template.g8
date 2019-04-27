package $package$.kafka;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KafkaMessage<PAYLOAD> {
    @JsonProperty("id") public String id;
    @JsonProperty("key") public String key;
    @JsonProperty("type") public String type;
    @JsonProperty("time") public Instant time;
    @JsonProperty("payload") public PAYLOAD payload;

    @JsonCreator
    public KafkaMessage() {
    }
}
