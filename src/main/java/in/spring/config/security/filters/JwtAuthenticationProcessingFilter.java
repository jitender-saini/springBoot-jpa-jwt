package in.spring.config.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.spring.config.security.model.RequestDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private ObjectMapper mapper;

    public JwtAuthenticationProcessingFilter(AuthenticationManager authenticationManager,
                                             AuthenticationSuccessHandler loginSuccessHandler,
                                             AuthenticationFailureHandler failureHandler) {
        super(new AntPathRequestMatcher("/login", "POST"));
        setAuthenticationManager(authenticationManager);
        setAuthenticationSuccessHandler(loginSuccessHandler);
        setAuthenticationFailureHandler(failureHandler);
        mapper = new ObjectMapper();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        try {
            RequestDto requestDto = mapper.readValue(request.getInputStream(), RequestDto.class);

            if (requestDto != null && requestDto.isValid()) {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        requestDto.getUsername(), requestDto.getPassword()
                );
                return getAuthenticationManager().authenticate(token);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }
}
