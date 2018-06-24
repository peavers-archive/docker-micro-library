package micro.library.docker.service;

import java.util.List;

public interface ScriptService {

    /**
     * Runs the commands passed in. Commands are executed with bash so make sure they're valid.
     * Output is read straight to the console using the INFO tag.
     *
     * @param command List<String>
     */
    int executeScript(List<String> command);
}
