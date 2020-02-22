package in.spring.controller;

import in.spring.model.co.UserCO;
import in.spring.model.vo.UserVO;
import in.spring.repository.UserRepository;
import in.spring.domain.User;
import in.spring.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<UserVO> list() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private UserVO convertToDto(User user) {
        return new UserVO(user);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public HashMap<String, String> register(@RequestBody UserCO userCO) {
        HashMap<String, String> responseMap = new HashMap<>();
        responseMap.put("status", "SUCCESS");
        if (userCO.getEmail() != null) {
            String message = userService.createUser(userCO);
            responseMap.put("message", message);
        } else {
            responseMap.put("message", "User Creation Failed!");
        }
        return responseMap;
    }
}
