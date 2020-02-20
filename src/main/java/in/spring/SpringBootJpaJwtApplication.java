package in.spring;

import in.spring.service.TestService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootJpaJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJpaJwtApplication.class, args);
    }

    @Bean
    public ApplicationRunner seedData(TestService testService) {
        return args -> {
            testService.createRoles();
            testService.createUsers();
        };
    }
}
