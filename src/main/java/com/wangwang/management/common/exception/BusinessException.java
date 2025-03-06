package com.wangwang.management.common.exception;

import com.wangwang.management.common.interfaces.ResponseInterface;

public class BusinessException extends RuntimeException implements ResponseInterface {


    private Integer code;

    private String msg;


    public BusinessException(ResponseInterface responseInterface) {
        super(responseInterface.getMsg());
        this.code = responseInterface.getCode();
        this.msg = responseInterface.getMsg();
    }

    public BusinessException(ResponseInterface responseInterface, Throwable throwable) {
        super(responseInterface.getMsg(), throwable);
        this.code = responseInterface.getCode();
        this.msg = responseInterface.getMsg();
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
