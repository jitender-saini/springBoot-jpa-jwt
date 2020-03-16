package in.spring.config.logging;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RequestLoggingFilterConfig implements WebMvcConfigurer {

    @Bean
    public RequestLoggingFilter logFilter() {
        RequestLoggingFilter filter = new RequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeClientInfo(true);
//        filter.setIncludeHeaders(true);
        filter.setAfterMessagePrefix("REQUEST DATA : ");
        return filter;
    }

    @Bean
    public FilterRegistrationBean<javax.servlet.Filter> loggingFilterRegistration() {
        return new FilterRegistrationBean<javax.servlet.Filter>(logFilter());
    }

}
