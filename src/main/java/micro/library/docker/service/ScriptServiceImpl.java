package micro.library.docker.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


public class ScriptServiceImpl implements ScriptService {

    /**
     * Streams the output from the executing script to the console.
     *
     * @param iStream InputStream
     * @throws IOException if there is a problem reading the output from the stream
     */
    private void readStream(final InputStream iStream) throws Exception {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(iStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    /**
     * Runs the commands passed in. Commands are executed with bash so make sure they're valid.
     * Output is read straight to the console using the INFO tag.
     *
     * @param command List<String>
     */
    @Override
    public int executeScript(final List<String> command) {
        try {
            final ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash").command(command);
            processBuilder.redirectErrorStream(true);

            final Process process = processBuilder.start();
            final InputStream inputStream = process.getInputStream();
            final InputStream errorStream = process.getErrorStream();

            readStream(inputStream);
            readStream(errorStream);

            return process.waitFor();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }
}
