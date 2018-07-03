package micro.library.docker.api;

import micro.library.docker.domain.Container;
import micro.library.docker.domain.Image;

public interface DockerApi {

    /**
     * Builds a standard docker execution command using the values passed into the 'Container'.
     *
     * @param container Container
     * @return int status code of the execution. Anything but 0 will represent an error.
     */
    int run(Container container);

    /**
     * Create a login session on the registry this is running on;
     *
     * @param username String
     * @param password String
     * @param endpoint String
     * @return int status code of the execution. Anything but 0 will represent an error.
     */
    int login(String username, String password, String endpoint);

    /**
     * Pull down a image from repository
     *
     * @param image Image
     * @return int status code of the execution. Anything but 0 will represent an error.
     */
    int pull(Image image);
}
