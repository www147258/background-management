package com.wangwang.management.controller;

import com.wangwang.management.common.domain.CommonResponse;
import com.wangwang.management.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CommonControllerAdvice {

    @ExceptionHandler(BusinessException.class)
    public CommonResponse handlerBusinessException(BusinessException e) {
        log.warn("handlerBusinessException " , e);
        return CommonResponse.fail(e);
    }
}
