package com.wangwang.management.domain.request.systemuser;

import com.wangwang.management.common.domain.PageRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true)
public class SystemUserPageListRequest extends PageRequest {

    /**
     * 用户状态
     */
    private Integer userState;

    /**
     * 用户手机号 脱敏的
     */
    private String userPhone;

    /**
     * 用户系统账号
     */
    private String systemAccount;

    /**
     * 用户昵称 模糊查询
     */
    private String userNickName;
}
