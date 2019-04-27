package $package$.kafka;

import com.rewe.omm.order.fixpoint.kafka.KafkaMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;

@Slf4j
public class KafkaTimestampExtractor implements TimestampExtractor {

    @Override
    public long extract(final ConsumerRecord<Object, Object> record, final long previousTimestamp) {

        if (record.timestamp() < 0) {
            log.warn("Received an invalid timestamp for record {}", record.key());
        }

        long timestamp = -1;
        final KafkaMessage kafkaMessage = (KafkaMessage) record.value();
        if (kafkaMessage != null && kafkaMessage.time != null) {
            timestamp = kafkaMessage.time.toEpochMilli();
        }
        if (timestamp < 0) {
            if (previousTimestamp >= 0) {
                return previousTimestamp;
            } else {
                return System.currentTimeMillis();
            }
        }
        return timestamp;
    }

}
