package micro.library.docker.api;

import micro.library.docker.domain.DockerContainer;

public interface DockerApi {

    int run(DockerContainer dockerContainer);

    void stop(DockerContainer dockerContainer);

}
