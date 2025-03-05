package com.wangwang.management.common.domain;

import com.wangwang.management.common.interfaces.ResponseInterface;
import com.wangwang.management.enums.ResponseCodeAndMsgEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CommonResponse<T> {

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应码描述
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;



    public static CommonResponse success() {

        return success(null);
    }

    public static CommonResponse success(Object data) {
        return build(ResponseCodeAndMsgEnum.SUCCESS, data);
    }

    public static CommonResponse fail(ResponseInterface responseInterface ) {
        return build(responseInterface, null);
    }


    private static CommonResponse build(ResponseInterface responseInterface , Object data) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(responseInterface.getCode());
        commonResponse.setMsg(responseInterface.getMsg());
        commonResponse.setData(data);
        return commonResponse;
    }
}