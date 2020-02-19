package in.spring.service;

import in.spring.repo.dao.UserRepository;
import in.spring.repo.dao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("-----------------------");
        System.out.println(userRepository.findByUsername(username));
        System.out.println("-----------------------");
        return userRepository.findByUsername(username);
    }
}
