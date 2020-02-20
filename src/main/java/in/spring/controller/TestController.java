package in.spring.controller;

import in.spring.repo.dao.RoleRepository;
import in.spring.repo.dao.UserRepository;
import in.spring.repo.dao.domain.Role;
import in.spring.repo.dao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/index")
    public String getWorld() {
        if (userRepository.count() == 0) {
            User user = new User();
            user.setEmail("abc@gmail.com");
            user.setActive(true);
            user.setFirstName("hey");
            user.setLastName("bye");
            user.setLastPasswordResetDate(new Date());
            Role adminRole = roleRepository.findByRole("ADMIN");
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(adminRole);
            user.setRoles(roleSet);
            user.setPassword(passwordEncoder.encode("dummy"));
            userRepository.save(user);
            return "Hello world! : " + user.toString();
        }
        return "Already created";
    }

    @RequestMapping(value = "roles", method = RequestMethod.GET)
    public String createRoles() {
        if (roleRepository.count() == 0) {
            Role adminRole = new Role();
            adminRole.setRole("ADMIN");
            roleRepository.save(adminRole);
            Role userRole = new Role();
            userRole.setRole("USER");
            roleRepository.save(userRole);
            getWorld();
            return "Created Successfully";
        }
        return "Already Created.";
    }
}

