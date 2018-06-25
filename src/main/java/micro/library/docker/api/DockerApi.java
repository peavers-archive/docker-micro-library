package micro.library.docker.api;

import micro.library.docker.domain.DockerContainer;

public interface DockerApi {

    /**
     * Builds a standard docker execution command using the values passed into the 'DockerContainer'.
     *
     * @param dockerContainer DockerContainer
     * @return int status code of the execution. Anything but 0 will represent an error.
     */
    int run(DockerContainer dockerContainer);

    void stop(DockerContainer dockerContainer);

}
