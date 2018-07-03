package micro.library.docker.api;

import micro.library.docker.domain.Container;
import micro.library.docker.domain.Image;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DockerApiImplTest {

    private Container container;

    @BeforeEach
    void setUp() {
        this.container = new Container();

        container.setName("TEST_NAME");
        container.setImage(new Image("example.com", "example", "1.0.0"));
        container.setRemove(true);
        container.addEnvironment("TEST_ENVIRONMENT");
        container.addVolume("TEST_VOLUME:TEST_VOLUME");
    }

    @AfterEach
    void tearDown() {
        this.container = null;
    }

    @Test
    void run() {
        final List<String> command = new ArrayList<>();

        command.add("docker");
        command.add("run");
        if (container.getRemove()) command.add("--rm");
        command.add("--name");
        command.add(container.getName());
        command.addAll(container.getEnvironment());
        command.addAll(container.getVolume());
        command.add(container.getImage().toString());

        final ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash").command(command);

        assertEquals("[docker, run, --rm, --name, TEST_NAME, -e, TEST_ENVIRONMENT, -v, TEST_VOLUME:TEST_VOLUME, example.com/example:1.0.0]", processBuilder.command().toString());
    }

    @Test
    void login() {
        final List<String> command = new ArrayList<>();

        command.add("docker");
        command.add("login");
        command.add("--username");
        command.add("TEST_USERNAME");
        command.add("--password");
        command.add("TEST_PASSWORD");
        command.add("example.com");
        final ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash").command(command);

        assertEquals("[docker, login, --username, TEST_USERNAME, --password, TEST_PASSWORD, example.com]", processBuilder.command().toString());
    }

    @Test
    void pull() {
        final List<String> command = new ArrayList<>();

        command.add("docker");
        command.add("pull");
        command.add(container.getImage().toString());

        final ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash").command(command);

        assertEquals("[docker, pull, example.com/example:1.0.0]", processBuilder.command().toString());
    }

}