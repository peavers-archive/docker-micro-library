package micro.library.docker.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import micro.library.docker.domain.Container;
import micro.library.docker.domain.Image;

public class DockerApiImpl implements DockerApi {

    /**
     * Builds a standard docker execution command using the values passed into the 'Container'.
     *
     * @param container Container
     * @return int status code of the execution. Anything but 0 will represent an error.
     */
    @Override
    public int run(Container container) {
        final List<String> command = new ArrayList<>();

        return execute(container, command);
    }

    @Override
    public int run(Container container, HashMap<String, String> extraCommands) {
        final List<String> command = new ArrayList<>();

        extraCommands.forEach((k, v) -> command.add(k + " " + v));

        return execute(container, command);
    }

    private int execute(Container container, List<String> command) {
        command.add("docker");
        command.add("run");

        if (container.getRemove()) {
            command.add("--rm");
        }

        command.add("--name");
        command.add(container.getName());
        command.addAll(container.getEnvironment());
        command.addAll(container.getVolume());

        command.add(container.getImage().toString());

        return executeScript(command);
    }

    @Override
    public int login(String username, String password, String endpoint) {
        final List<String> command = new ArrayList<>();

        command.add("docker");
        command.add("login");
        command.add("--username");
        command.add(username);
        command.add("--password");
        command.add(password);
        command.add(endpoint);

        return executeScript(command);
    }

    @Override
    public int pull(Image image) {
        final List<String> command = new ArrayList<>();

        command.add("docker");
        command.add("pull");
        command.add(image.toString());

        return executeScript(command);
    }

    /**
     * Streams the output from the executing script to the console.
     *
     * @param iStream InputStream
     */
    private void readStream(final InputStream iStream) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(iStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs the commands passed in. Commands are executed with bash so make sure they're valid.
     * Output is read straight to the console using the INFO tag.
     *
     * @param command List<String>
     */
    private int executeScript(final List<String> command) {
        try {
            final ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash").command(command);
            processBuilder.redirectErrorStream(true);

            System.out.println("executing: " + processBuilder.command().toString());

            final Process process = processBuilder.start();
            final InputStream inputStream = process.getInputStream();
            final InputStream errorStream = process.getErrorStream();

            readStream(inputStream);
            readStream(errorStream);

            return process.waitFor();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return -1;
    }

}
