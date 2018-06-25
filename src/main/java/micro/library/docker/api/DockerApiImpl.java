package micro.library.docker.api;

import micro.library.docker.domain.DockerContainer;
import micro.library.docker.service.ScriptService;
import micro.library.docker.service.ScriptServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class DockerApiImpl implements DockerApi {

    private final ScriptService scriptService;

    public DockerApiImpl() {
        this.scriptService = new ScriptServiceImpl();
    }

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
        command.add(dockerContainer.getCommand());

        return scriptService.executeScript(command);
    }

    @Override
    public void stop(DockerContainer dockerContainer) {
    }
}
