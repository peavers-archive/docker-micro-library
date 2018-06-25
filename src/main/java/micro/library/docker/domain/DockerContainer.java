package micro.library.docker.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class DockerContainer {

    /**
     * Name of the container '--name'.
     */
    String name;

    /**
     * Image name.
     */
    String image;

    /**
     * If true the container will be deleted when stopped '--rm'.
     */
    Boolean remove;

    /**
     * Extra commands to add to the build
     */
    String command;

    /**
     * List of volumes to mount '-v'.
     */
    private List<String> volume = new ArrayList<>();

    /**
     * List of environment variables to set '-e'.
     */
    private List<String> environment = new ArrayList<>();

    /**
     * Wraps around the volume list to add the '-v' before each mount.
     *
     * @param volume String
     */
    public void addVolume(String volume) {
        List<String> list = this.getVolume();

        list.add("-v");
        list.add(volume);
    }

    /**
     * Wraps around the environment list to add the '-r' before each variable.
     *
     * @param environment String
     */
    public void addEnvironment(String environment) {
        List<String> list = this.getEnvironment();

        list.add("-e");
        list.add(environment);
    }

    /**
     * Set with the 'addVolume' method.
     */
    private void setVolume(List<String> volume) {

    }

    /**
     * Set with the 'addEnvironment' method.
     */
    private void setEnvironment(List<String> environment) {

    }
}
