package micro.library.docker.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContainerTest {

    private Container container;

    @BeforeEach
    void setUp() {
        this.container = new Container();

        container.addEnvironment("TEST_ONE");
        container.addEnvironment("TEST_TWO");

        container.addVolume("TEST_ONE");
        container.addVolume("TEST_TWO");
    }

    @AfterEach
    void tearDown() {
        this.container = null;
    }

    @Test
    void addVolume() {
        List<String> volume = container.getVolume();

        assertEquals("-v", volume.get(0));
        assertEquals("TEST_ONE", volume.get(1));

        assertEquals("-v", volume.get(2));
        assertEquals("TEST_TWO", volume.get(3));
    }

    @Test
    void addEnvironment() {
        List<String> environment = container.getEnvironment();

        assertEquals("-e", environment.get(0));
        assertEquals("TEST_ONE", environment.get(1));

        assertEquals("-e", environment.get(2));
        assertEquals("TEST_TWO", environment.get(3));
    }
}