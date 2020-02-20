package in.spring.controller;

import in.spring.repo.dao.UserRepository;
import in.spring.repo.dao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/index")
    public String getWorld() {
        User user = new User();
        user.setEmail("abc@gmail.com");
        user.setActive(true);
        user.setFirstName("hey");
        user.setLastName("bye");
        user.setLastPasswordResetDate(new Date());
        user.setRoles();
        user.setPassword(passwordEncoder.encode("dummy"));
        userRepository.save(user);
        return "Hello world! : " + user.toString();
    }
}

