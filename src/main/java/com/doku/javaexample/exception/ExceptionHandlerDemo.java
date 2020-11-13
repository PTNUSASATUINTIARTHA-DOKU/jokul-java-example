package com.doku.javaexample.exception;

import com.doku.javaexample.dto.BaseResponse;
import enums.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerDemo {
    @ExceptionHandler(value = {Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<BaseResponse> unknownException(Exception ex) {
        log.info("exception : "+ex.toString());
        log.info("exception : "+ex.getStackTrace()[0]);
        log.info("exception : "+ex.getStackTrace()[1]);
        log.info("exception : "+ex.getStackTrace()[2]);
        BaseResponse response = new BaseResponse();
        response.setResponseCode("500");
        response.setError(ResponseCode.UNKNOWN.message());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
