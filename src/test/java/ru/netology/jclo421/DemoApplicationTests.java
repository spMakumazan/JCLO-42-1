package ru.netology.jclo421;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    private static final GenericContainer<?> devappContainer = new GenericContainer<>("devapp")
            .withExposedPorts(8080);
    private static final GenericContainer<?> prodappContainer = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        devappContainer.start();
        prodappContainer.start();
    }

    @Test
    void devappTest() {

        String expected = "Current profile is dev";

        String result = restTemplate
                .getForEntity("http://localhost:" + devappContainer.getMappedPort(8080) + "/profile", String.class)
                .getBody();

        Assertions.assertEquals(expected, result);
    }

    @Test
    void prodappTest() {

        String expected = "Current profile is production";

        String result = restTemplate
                .getForEntity("http://localhost:" + prodappContainer.getMappedPort(8081) + "/profile", String.class)
                .getBody();

        Assertions.assertEquals(expected, result);
    }
}