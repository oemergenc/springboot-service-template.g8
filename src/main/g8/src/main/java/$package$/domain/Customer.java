package $package$.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
    public String id;
    public String customerUUID;
    public Instant creationDate;
}
