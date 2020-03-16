package in.spring.controller;

import in.spring.config.security.TokenService;
import in.spring.config.security.model.JwtTokenRequest;
import in.spring.config.security.model.JwtTokenResponse;
import in.spring.config.security.userdetails.JwtUserDetailsService;
import in.spring.repository.UserRepository;
import in.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static in.spring.util.Constants.LOGIN_URI;

@RestController
public class AuthenticationController {

    @Value("${jwt.http.request.header}")
    private String tokenHeader;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUserDetailsService userDetailsService;

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = LOGIN_URI)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        JwtTokenResponse jwtTokenResponse = tokenService.generateJwtToken(userDetails);
        return ResponseEntity.ok(jwtTokenResponse);
    }


    @RequestMapping(value = "${jwt.refresh.token.uri}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String username = tokenService.getUsernameFromToken(token);
        if (tokenService.canTokenBeRefreshed(token)) {
            String refreshedToken = tokenService.refreshToken(token);
            return ResponseEntity.ok(JwtTokenResponse.bearer(token));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }


    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
