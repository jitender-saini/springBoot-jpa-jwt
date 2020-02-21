package in.spring.controller;

import in.spring.repository.UserRepository;
import in.spring.domain.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public List<User> list() {
        return userRepository.findAll();
    }
}
