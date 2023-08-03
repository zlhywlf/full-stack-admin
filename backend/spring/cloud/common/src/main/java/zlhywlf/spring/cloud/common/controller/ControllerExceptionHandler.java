package zlhywlf.spring.cloud.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        log.error("system error", e);
        return e.getMessage();
    }
}