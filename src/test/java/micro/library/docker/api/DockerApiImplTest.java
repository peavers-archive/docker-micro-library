package micro.library.docker.api;

import micro.library.docker.domain.DockerContainer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DockerApiImplTest {

    private DockerContainer dockerContainer;

    @BeforeEach
    void setUp() {
        this.dockerContainer = new DockerContainer();

        dockerContainer.setName("TEST_NAME");
        dockerContainer.setImage("TEST_IMAGE");
        dockerContainer.setRemove(true);
        dockerContainer.addEnvironment("TEST_ENVIRONMENT");
        dockerContainer.addVolume("TEST_VOLUME");
    }

    @AfterEach
    void tearDown() {
        this.dockerContainer = null;
    }

    @Test
    void run() {
        final List<String> command = new ArrayList<>();

        command.add("docker");
        command.add("run");
        if (dockerContainer.getRemove()) command.add("--rm");
        command.add("--name");
        command.add(dockerContainer.getName());
        command.addAll(dockerContainer.getEnvironment());
        command.addAll(dockerContainer.getVolume());
        command.add(dockerContainer.getImage());

        // Should be mocked
        final ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash").command(command);

        assertEquals("[docker, run, --rm, --name, TEST_NAME, -e, TEST_ENVIRONMENT, -v, TEST_VOLUME, TEST_IMAGE]", processBuilder.command().toString());
    }

    @Test
    void stop() {
    }
}