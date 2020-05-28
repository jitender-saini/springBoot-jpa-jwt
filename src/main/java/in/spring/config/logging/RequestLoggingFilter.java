package in.spring.config.logging;

import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

import static in.spring.util.Constants.LOGIN_URI;


public class RequestLoggingFilter extends AbstractRequestLoggingFilter {

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        String reqUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        if (reqUri.equals(contextPath + LOGIN_URI) ||
                reqUri.equals(contextPath + "/v1/user/update-password") ||
                reqUri.equals(contextPath + "/v1/user/account/reset-password") ||
                reqUri.equals(contextPath + "/actuator/health")) {
            return false;
        }
        return true;
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        logger.info(message);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        logger.info(message);
    }
}