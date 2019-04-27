package $package$.componenttests;

import org.apache.kafka.streams.KafkaStreams
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class AbstractComponentSpec extends Specification {
    @Value("${local.server.port}")
    private int port

    protected PollingConditions pollingConditions = new PollingConditions(timeout: 10)

    @Autowired
    private KafkaStreams kafkaStreams;


    def setupSpec() {

    }

    def setup() {

    }

    def clean() {

    }

    def cleanupSpec() {

    }
}
