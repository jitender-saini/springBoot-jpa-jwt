package in.spring.service;

import in.spring.config.JwtUserDetails;
import in.spring.repo.dao.UserRepository;
import in.spring.repo.dao.domain.Role;
import in.spring.repo.dao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("-----------------------");
        System.out.println(userRepository.findByUsername(username));
        System.out.println("-----------------------");
        User user = userRepository.findByUsername(username);
        if (user != null) {
            List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Role role : user.getRoles()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
            }
            JwtUserDetails userDetails = new JwtUserDetails(user.getId(), user.getEmail(), user.getFirstName(),
                    user.getLastName(), user.getEmail(), user.getPassword(),
                    grantedAuthorities, true, user.getLastPasswordResetDate());
            return userDetails;
        } else {
            return null;
        }
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found: " + username));
//        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Arrays.asList(authority));
//    }

}
