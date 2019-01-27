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


}
