package in.spring.config.logging;

import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

import static in.spring.util.Constants.LOGIN_URI;


public class RequestLoggingFilter extends AbstractRequestLoggingFilter {

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        return logger.isDebugEnabled();
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
//        logger.debug(message);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        if (!request.getRequestURI().equals(LOGIN_URI)) {
            logger.debug(message);
        }
    }
}