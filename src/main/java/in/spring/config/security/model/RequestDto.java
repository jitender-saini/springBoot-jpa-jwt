package in.spring.config.security.model;

import org.springframework.util.StringUtils;

public class RequestDto {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValid() {
        return !StringUtils.isEmpty(username) && !StringUtils.isEmpty(password);
    }
}
