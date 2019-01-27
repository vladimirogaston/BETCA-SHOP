package es.upm.miw.rest_controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TestsResource.TESTS)
public class TestsResource {

    public static final String TESTS = "/tests";

    @Value("${spring.data.mongodb.database}")
    private String db = null;

    @GetMapping
    public String read() {
        return "OK!!! " + "Database: " + db;
    }

}
