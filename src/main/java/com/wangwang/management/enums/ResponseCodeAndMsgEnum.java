package com.wangwang.management.enums;
import com.wangwang.management.common.interfaces.ResponseInterface;

public enum ResponseCodeAndMsgEnum implements ResponseInterface {

    SUCCESS(0, "操作成功"),


    ;


    private Integer code;

    private String msg;

    ResponseCodeAndMsgEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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

