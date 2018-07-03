package micro.library.docker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    /**
     * Docker repository registry. Defaults to dockerhub.com
     */
    String registry = "index.docker.io";

    /**
     * Actual name of the image without the version
     */
    String name;

    /**
     * Version number to use. Defaults to latest
     */
    String version = "latest";

    /**
     * Overrides the default to return the string docker is expecting
     *
     * @return String registry/name:version
     */
    @Override
    public String toString() {
        return String.format("%s/%s:%s", registry, name, version);
    }
}
