package es.upm.miw.rest_controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TestsResource.TESTS)
public class TestsResource {

    public static final String TESTS = "/tests";

    @GetMapping
    public String read(){
        return "OK!!!";
    }

}
