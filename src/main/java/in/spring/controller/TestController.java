package in.spring.controller;

import in.spring.repo.dao.UserRepository;
import in.spring.repo.dao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/index")
    public String getWorld() {
        User user = new User();
        user.setName("abc");
        user.setIsEnabled(true);
        PasswordEncoder passwordEncoder = passwordEncoder();
        user.setPassword(passwordEncoder.encode("dummy"));
        user.setUsername("test2");
        userRepository.save(user);
        return "Hello world! : " + user.toString();
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

