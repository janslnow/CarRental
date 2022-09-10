package com.demo.rental.exception;

import com.demo.rental.common.UserContext;
import com.demo.rental.model.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final Integer BUSINESS_EXCEPTION = 200;
    public static final Integer SYSTEM_EXCEPTION = 418;

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<HttpResult<?>> systemException(HttpServletRequest request, Exception e) {

        String url = request.getRequestURL().toString();

        log.error("系统系统: url:{}, user:{}", url, UserContext.getUserId(), e);

        return ResponseEntity.status(SYSTEM_EXCEPTION).body(HttpResult.fail(e.getMessage()));
    }


    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<HttpResult<?>> businessException(HttpServletRequest request, RuntimeException e) {

        String url = request.getRequestURL().toString();

        log.error("系统系统: url:{}, user:{}", url, UserContext.getUserId(), e);

        return ResponseEntity.status(BUSINESS_EXCEPTION).body(HttpResult.fail(e.getMessage()));
    }
}
