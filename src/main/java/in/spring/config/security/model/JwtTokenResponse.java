package in.spring.config.security.model;

public class JwtTokenResponse {

    private String access_token;
    private String type;

    public JwtTokenResponse(String access_token, String type) {
        this.access_token = access_token;
        this.type = type;
    }

    public static JwtTokenResponse bearer(String access_token) {
        return new JwtTokenResponse(access_token, "Bearer");
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getType() {
        return type;
    }
}
