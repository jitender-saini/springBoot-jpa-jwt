package in.spring.config.security.service;

import in.spring.domain.Role;
import in.spring.domain.User;
import in.spring.model.co.UserCO;
import in.spring.repository.RoleRepository;
import in.spring.repository.UserRepository;
import in.spring.util.Constants;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String createUser(UserCO userCO) {
        if (userRepository.findByEmail(userCO.getEmail()) == null) {
            User user = new User();
            user.setEmail(userCO.getEmail());
            user.setFirstName(userCO.getFirstName());
            user.setLastName(userCO.getLastName());
            user.setPassword(passwordEncoder.encode(userCO.getPassword()));
            user.setLastPasswordResetDate(null);
            user.setActive(true);
            Role roleEntity = roleRepository.findByRole(Constants.USER_ROLE);
            user.setRoles(Collections.singleton(roleEntity));
            userRepository.save(user);
            return "User Created Successfully!";
        }
        return "User Already exist in DB!";
    }
}
