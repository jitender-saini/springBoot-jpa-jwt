package in.spring.service;

import in.spring.repository.RoleRepository;
import in.spring.repository.UserRepository;
import in.spring.domain.Role;
import in.spring.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Date;

import static in.spring.util.Constants.ADMIN_ROLE;
import static in.spring.util.Constants.USER_ROLE;

@Service
@Transactional
public class BootstrapService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public BootstrapService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void createRoles() {
        createRole(ADMIN_ROLE, USER_ROLE);
    }

    public void createUsers() {
        createUser("admin@gmail.com", ADMIN_ROLE);
        createUser("user@gmail.com", USER_ROLE);
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
