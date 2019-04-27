package $package$.domain;

import $package$.kafka.JsonPojoDeserializer;
import $package$.kafka.JsonPojoSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.apache.kafka.streams.kstream.Consumed.with;

@Configuration
@Slf4j
public class KafkaStreamsCustomerTopology {
    public static final Serde<KafkaCustomerMessage> CUSTOMER_SERDE = getSerde(KafkaCustomerMessage.class);

    @Bean
    public Topology topologyBuilder() {

        StreamsBuilder builder = new StreamsBuilder();
        builder.stream("customer", with(Serdes.String(), CUSTOMER_SERDE))
            .peek((key, value) -> log.info("Received customer message {}", key))
            .to("customer2", Produced.with(Serdes.String(), CUSTOMER_SERDE));

        return builder.build();
    }

    private static <T> Serde<T> getSerde(Class<T> type) {
        return new Serdes.WrapperSerde<T>(new JsonPojoSerializer<T>(), new JsonPojoDeserializer<>(type));
    }
}
