package in.spring.config.security;

public class JwtToken {

    private String access_token;
    private String type;

    public JwtToken(String access_token, String type) {
        this.access_token = access_token;
        this.type = type;
    }

    public static JwtToken bearer(String access_token) {
        return new JwtToken(access_token, "Bearer");
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getType() {
        return type;
    }
}
