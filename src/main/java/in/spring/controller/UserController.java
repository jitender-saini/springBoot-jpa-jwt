package in.spring.controller;

import in.spring.repo.dao.UserRepository;
import in.spring.repo.dao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    @Secured("ADMIN")
    public List<User> list() {
        return userRepository.findAll();
    }
}
