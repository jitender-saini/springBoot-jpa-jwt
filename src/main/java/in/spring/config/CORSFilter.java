package in.spring.config;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(value = -1)
@Component
public class CORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        //response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "Accept,Authorization,WWW-Authenticate," +
                        "Connection,X-Requested-With," +
                        "Pragma," +
                        "Cache-Control," +
                        "Accept-Language," +
                        "Content-Type," +
                        "DNT," +
                        "If-Modified-Since," +
                        "Keep-Alive," +
                        "Origin," +
                        "User-Agent," +
                        "X-Mx-ReqToken," +
                        "X-Requested-With," +
                        "app-type," +
                        "login-type," +
                        "secret-link," +
                        "sessionId," +
                        "source");
        chain.doFilter(req, res);

    }

    @Override
    public void destroy() {
    // TODO Auto-generated method stub

    }


}