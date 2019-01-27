package es.upm.miw.rest_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(TestsResource.TESTS)
public class TestsResource {

    public static final String TESTS = "/tests";
    public static final String ONES = "/ones";


    @Autowired
    private OneRepository oneRepository;

    @Value("${spring.data.mongodb.database}")
    private String db = null;

    @GetMapping
    public String read() {
        return "OK!!! " + "Database: " + db;
    }

    @PostMapping(value = ONES)
    public void createOne(@RequestBody OneDto oneDto) {
        this.oneRepository.save(new One(oneDto.getValue()));
    }

    @GetMapping(value = ONES)
    public List<OneDto> readAll(){
        return this.oneRepository.findAll().stream().map(OneDto::new).collect(Collectors.toList());
    }

}
