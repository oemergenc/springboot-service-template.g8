package $package$.componenttests

class ComponentTest extends AbstractComponentSpec {

    def "correct is correct"() {
        given:
        def correct

        when:
        correct = true

        then:
        correct
    }
}
