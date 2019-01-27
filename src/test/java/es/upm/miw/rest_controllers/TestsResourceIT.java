package es.upm.miw.rest_controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ApiTestConfig
class TestsResourceIT {

    @Value("${local.server.port}")
    private int port = 0;

    @Autowired
    private TestsResource testsResource;

    @Test
    void testRead() {
        assertTrue(new RestBuilder<String>(port).clazz(String.class)
                .path(TestsResource.TESTS).get().build().length() > 0);
    }

    @Test
    void testCreateAndReadAll() {
        new RestBuilder<>(port).path(TestsResource.TESTS).path(TestsResource.ONES).body(new OneDto("1"))
                .post().build();
        new RestBuilder<>(port).path(TestsResource.TESTS).path(TestsResource.ONES).body(new OneDto("2"))
                .post().build();
        assertTrue(new RestBuilder<One[]>(port).clazz(One[].class)
                .path(TestsResource.TESTS).path(TestsResource.ONES).get().build().length > 1);
    }

}
