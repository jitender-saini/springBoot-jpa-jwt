package in.spring.service;

import in.spring.repo.dao.repository.RoleRepository;
import in.spring.repo.dao.repository.UserRepository;
import in.spring.repo.dao.domain.Role;
import in.spring.repo.dao.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Date;

@Service
@Transactional
public class TestService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public TestService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void createRoles() {
        createRole("ROLE_ADMIN", "ROLE_USER");
    }

    public void createUsers() {
        createUser("admin@gmail.com", "ROLE_ADMIN");
        createUser("user@gmail.com", "ROLE_USER");
    }

    private void createRole(String... roles) {
        for (String role : roles) {
            Role roleEntity = roleRepository.findByRole(role);
            if (roleEntity == null) {
                roleEntity = new Role();
                roleEntity.setRole(role);
                roleRepository.save(roleEntity);
            }
        }
    }

    private void createUser(String username, String role) {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            user = new User();
            user.setEmail(username);
            user.setActive(true);
            user.setFirstName("hey");
            user.setLastName("bye");
            user.setLastPasswordResetDate(new Date());
            Role roleEntity = roleRepository.findByRole(role);
            user.setRoles(Collections.singleton(roleEntity));
            user.setPassword(passwordEncoder.encode("admin"));
            userRepository.save(user);
        }
    }

}
