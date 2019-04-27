package $package$.kafka.config;

import $package$.kafka.config.KafkaTimestampExtractor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.Topology;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

import static org.apache.kafka.clients.CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.CommonClientConfigs.SECURITY_PROTOCOL_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG;
import static org.apache.kafka.common.config.SaslConfigs.SASL_MECHANISM;
import static org.apache.kafka.common.config.SslConfigs.*;
import static org.apache.kafka.common.serialization.Serdes.String;
import static org.apache.kafka.streams.StreamsConfig.*;

\$package\$.kafka.config;

@Slf4j
@Configuration
public class KafkaStreamsConfig {
    @Bean
    public Properties streamsConfig(
        @Value("\${kafka.application-id}") String applicationId,
        @Value("\${kafka.broker.address.list}") String bootstrapServers,
        @Value("\${kafka.security.protocol:}") String securityProtocol,
        @Value("\${kafka.sasl.mechanism:}") String saslMechanism,
        @Value("\${kafka.truststore.path:}") String sslTruststoreLocation,
        @Value("\${ssl.truststore.password:}") String sslTruststorePassword,
        @Value("\${kafka.ssl.keystore.path:}") String sslKeystoreLocation,
        @Value("\${ssl.keystore.password:}") String sslKeystorePassword,
        @Value("\${ssl.key.password:}") String sslKeyPassword,
        @Value("\${kafka.consumer.auto-offset-reset}") String autoOffsetReset,
        @Value("\${kafka.state.dir}") String stateDir
    ) {
        val settings = new Properties();
        settings.put(APPLICATION_ID_CONFIG, applicationId);
        settings.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        settings.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, String().getClass()
            .getName());
        settings.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, String().getClass()
            .getName());

        settings.put(AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);

        settings.put(SECURITY_PROTOCOL_CONFIG, securityProtocol);
        settings.put(SASL_MECHANISM, saslMechanism);
        settings.put(SSL_TRUSTSTORE_LOCATION_CONFIG, sslTruststoreLocation);
        settings.put(SSL_TRUSTSTORE_PASSWORD_CONFIG, sslTruststorePassword);
        settings.put(SSL_KEYSTORE_LOCATION_CONFIG, sslKeystoreLocation);
        settings.put(SSL_KEYSTORE_PASSWORD_CONFIG, sslKeystorePassword);
        settings.put(SSL_KEY_PASSWORD_CONFIG, sslKeyPassword);
        settings.put(STATE_DIR_CONFIG, stateDir);
        settings.put(DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, KafkaTimestampExtractor.class);
        settings.put("commit.interval.ms", 1000);

        return settings;
    }

    @Bean
    public KafkaStreams getKafkaStreams(final Topology streamTopology, final Properties streamsConfig) {
        return new KafkaStreams(streamTopology, streamsConfig);
    }

}
