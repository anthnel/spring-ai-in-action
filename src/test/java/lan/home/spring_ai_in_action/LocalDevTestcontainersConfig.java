package lan.home.spring_ai_in_action;

import org.springframework.boot.test.context.TestConfiguration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class LocalDevTestcontainersConfig {

    static {
        GenericContainer<?> qdrant = new GenericContainer<>(DockerImageName.parse("qdrant/qdrant:latest"))
                .withExposedPorts(6334);

        qdrant.start();

        configureQdrantProperties(qdrant);
    }

    private static void configureQdrantProperties(GenericContainer<?> qdrantContainer) {
        System.setProperty("qdrant.host", qdrantContainer.getHost());
        System.setProperty("qdrant.port", qdrantContainer.getMappedPort(6334).toString());
    }

}
