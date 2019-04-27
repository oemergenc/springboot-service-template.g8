package $package$.componenttests

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class AbstractComponentSpec extends Specification {
    private int port

    protected PollingConditions pollingConditions = new PollingConditions(timeout: 10)


    def setupSpec() {

    }

    def setup() {

    }

    def clean() {

    }

    def cleanupSpec() {

    }
}
