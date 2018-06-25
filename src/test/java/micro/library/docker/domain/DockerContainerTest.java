package micro.library.docker.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DockerContainerTest {

    private DockerContainer dockerContainer;

    @BeforeEach
    void setUp() {
        this.dockerContainer = new DockerContainer();

        dockerContainer.addEnvironment("TEST_ONE");
        dockerContainer.addEnvironment("TEST_TWO");

        dockerContainer.addVolume("TEST_ONE");
        dockerContainer.addVolume("TEST_TWO");
    }

    @AfterEach
    void tearDown() {
        this.dockerContainer = null;
    }

    @Test
    void addVolume() {
        List<String> volume = dockerContainer.getVolume();

        assertEquals("-v", volume.get(0));
        assertEquals("TEST_ONE", volume.get(1));

        assertEquals("-v", volume.get(2));
        assertEquals("TEST_TWO", volume.get(3));
    }

    @Test
    void addEnvironment() {
        List<String> environment = dockerContainer.getEnvironment();

        assertEquals("-e", environment.get(0));
        assertEquals("TEST_ONE", environment.get(1));

        assertEquals("-e", environment.get(2));
        assertEquals("TEST_TWO", environment.get(3));
    }
}