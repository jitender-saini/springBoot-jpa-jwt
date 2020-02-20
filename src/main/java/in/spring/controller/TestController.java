package in.spring.controller;

import in.spring.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/seed")
    public String createSeedData() {
        testService.createRoles();
        testService.createUsers();
        return "Created";
    }
}

