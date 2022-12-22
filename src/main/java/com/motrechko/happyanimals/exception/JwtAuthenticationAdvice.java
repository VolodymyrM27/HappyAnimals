package com.motrechko.happyanimals.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class JwtAuthenticationAdvice {
        @ResponseBody
        @ExceptionHandler(JwtAuthenticationException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public String jwtAuthenticationException(JwtAuthenticationException ex) {
                return ex.getMessage();
        }
}
