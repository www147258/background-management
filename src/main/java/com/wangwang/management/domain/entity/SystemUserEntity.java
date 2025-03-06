package com.wangwang.management.domain.entity;

import com.wangwang.management.common.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString(callSuper = true)
public class SystemUserEntity extends BaseEntity {

    /**
     * 系统账号，一般是字母，大小写组合
     */
    private String systemAccount;

    /**
     * 用户手机号
     */
    private String userPhone;


    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickName;


    /**
     * 用户状态
     * @see com.wangwang.management.enums.SystemUserStateEnum
     */
    private Integer userState;

}
