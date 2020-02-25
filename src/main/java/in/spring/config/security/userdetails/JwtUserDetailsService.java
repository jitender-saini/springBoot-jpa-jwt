package in.spring.config.security.userdetails;

import in.spring.repository.UserRepository;
import in.spring.domain.Role;
import in.spring.domain.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user != null) {
            List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Role role : user.getRoles()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
            }
            return new JWTUser(user.getId(), user.getEmail(), user.getFirstName(),
                    user.getLastName(), user.getEmail(), user.getPassword(),
                    grantedAuthorities, true, user.getLastPasswordResetDate());
        } else {
            // Must throw exception
            throw new UsernameNotFoundException(username + "doesn't exists in the system");
        }
    }

}
