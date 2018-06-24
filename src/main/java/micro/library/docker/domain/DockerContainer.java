package micro.library.docker.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DockerContainer {

    String name;

    String image;

    Boolean remove;

    private List<String> volume;

    private List<String> environment;

    public void addVolume(String volume) {
        List<String> list = this.getVolume();

        list.add("-e");
        list.add(volume);

        this.volume.addAll(list);
    }

    public void addEnvironment(String environment) {
        List<String> list = this.getEnvironment();

        list.add("-e");
        list.add(environment);

        this.environment.addAll(list);
    }
}
