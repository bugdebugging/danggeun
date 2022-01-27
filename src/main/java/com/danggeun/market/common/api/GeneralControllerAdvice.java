package com.danggeun.market.common.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralControllerAdvice {
    @ExceptionHandler(value = {IllegalStateException.class,
            IllegalArgumentException.class})
    public ResponseEntity<ApiResult> handleBusinessException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiUtils.fail(404, e.getMessage()));
    }
}
