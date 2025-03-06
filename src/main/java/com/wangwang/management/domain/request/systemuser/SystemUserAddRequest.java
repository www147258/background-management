package com.wangwang.management.domain.request.systemuser;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SystemUserAddRequest {

    /**
     * 系统账号
     * 最大长度16
     */
    private String systemAccount;

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户密码
     */
    private String password;
}
