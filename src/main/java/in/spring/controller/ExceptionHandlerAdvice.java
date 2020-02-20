package in.spring.controller;

import in.spring.model.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    private static Logger log = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDto> accessDeniedHandle(HttpServletRequest request, AccessDeniedException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new ErrorDto(ex.getMessage(), request.getRequestURI()), HttpStatus.FORBIDDEN);
    }
}
