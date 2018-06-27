package micro.library.docker.api;

import micro.library.docker.domain.DockerContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DockerApiImpl implements DockerApi {

    /**
     * Builds a standard docker execution command using the values passed into the 'DockerContainer'.
     *
     * @param dockerContainer DockerContainer
     * @return int status code of the execution. Anything but 0 will represent an error.
     */
    @Override
    public int run(DockerContainer dockerContainer) {
        final List<String> command = new ArrayList<>();

        command.add("docker");
        command.add("run");

        if (dockerContainer.getRemove()) command.add("--rm");

        command.add("--name");
        command.add(dockerContainer.getName());
        command.addAll(dockerContainer.getEnvironment());
        command.addAll(dockerContainer.getVolume());

        command.add(dockerContainer.getImage());

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
