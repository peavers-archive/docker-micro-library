package micro.library.docker.domain;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class DockerContainer {

    String name;

    String image;

    Boolean remove;

    List<String> volume;

    List<String> environment;

    public void setVolume(String volume) {
        List<String> list = this.getVolume();

        list.add("-e");
        list.add(volume);

        this.volume.addAll(list);
    }

    public void setEnvironment(String environment) {
        List<String> list = this.getEnvironment();

        list.add("-e");
        list.add(environment);

        this.environment.addAll(list);
    }
}
