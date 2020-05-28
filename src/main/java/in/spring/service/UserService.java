package in.spring.service;

import in.spring.domain.Role;
import in.spring.domain.User;
import in.spring.enums.SearchOperation;
import in.spring.model.co.UserCO;
import in.spring.model.co.UserSearchCO;
import in.spring.model.vo.UserListVO;
import in.spring.model.vo.UserVO;
import in.spring.repository.RoleRepository;
import in.spring.repository.UserRepository;
import in.spring.repository.UserSpecification;
import in.spring.util.Constants;
import in.spring.util.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;

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

    public UserListVO fetchUserList(UserSearchCO userSearchCO) {
        UserListVO userListVO = new UserListVO();

        Page<User> page = userRepository.findAll(createUserSpecification(userSearchCO), getUserPaging(userSearchCO));
        if (page.hasContent()) {
            userListVO.setUsers(page.getContent().stream().map(UserVO::new).collect(Collectors.toList()));
            userListVO.setPageCount(page.getTotalPages());
            userListVO.setUserCount(page.getTotalElements());
        }

        return null;
    }

    private UserSpecification createUserSpecification(UserSearchCO ticketSearchCO) {
        UserSpecification userSpecification = new UserSpecification();
        String q = ticketSearchCO.getQ();
        if (ticketSearchCO.getStatus() != null) {
            userSpecification.add(new SearchCriteria("active", ticketSearchCO.getStatus(), SearchOperation.EQUAL));
        }
        if (q != null) {
            userSpecification.add(new SearchCriteria("email", q, SearchOperation.EQUAL));
        }
        return userSpecification;
    }

    private Pageable getUserPaging(UserSearchCO searchCO) {
        int size = searchCO.getSize() == null ? 10 : searchCO.getSize();
        int page = searchCO.getPage() == null ? 0 : searchCO.getPage();
        String sortBy = searchCO.getSortBy() == null ? "id" : searchCO.getSortBy();
        return PageRequest.of(page, size, Sort.by(sortBy).descending());
    }


}
