[![](https://jitpack.io/v/peavers/docker-micro-library.svg)](https://jitpack.io/#peavers/docker-micro-library)
[![Maintainability](https://api.codeclimate.com/v1/badges/76b29a1ae3df88377907/maintainability)](https://codeclimate.com/github/peavers/docker-micro-library/maintainability)

# Docker Micro Library
A very tiny wrapper around a `ProcessBuilder` for running docker commands.

## Installation
Since we're making good use of JitPack, this is simple. 

```groovy
	allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
	
	dependencies {
	        implementation 'com.github.peavers:docker-micro-library:-SNAPSHOT'
	}	
```
Click the JitPack build badge for more examples with maven, sbt example. 

## Spring boot example

Wire up the `DockerApi` and the `DockerContainer` 
```java
@Configuration
public class DockerConfig {

    @Bean
    public DockerApi dockerApi() {
        return new DockerApiImpl();
    }

    @Bean
    public DockerContainer dockerContainer() {
        return new DockerContainer();
    }

}
```

Load up the `DockerContainer` and execute
```java
@Component
public class CreateContainer {

    private final DockerContainer dockerContainer;

    private final DockerApi dockerApi;

    public CreateContainer(DockerContainer dockerContainer, DockerApi dockerApi) {
        this.dockerContainer = dockerContainer;
        this.dockerApi = dockerApi;
    }
    
    private void create() {
        dockerContainer.setName("Name of the container");
        dockerContainer.setImage("createContainer:latest");

        dockerApi.run(dockerContainer);
    }
}
```
