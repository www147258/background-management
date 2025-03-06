package com.wangwang.management.domain.response.systemuser;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class SystemUserPageListResponse {

    /**
     * 用户Id
     */
    private Long systemUserId;

    /**
     * 用户系统账号
     */
    private String systemAccount;

    /**
     * 用户手机号 脱敏的
     */
    private String userPhone;

    /**
     * 用户状态
     */
    private Integer userState;

    /**
     * 用户昵称
     */
    private String userNickName;

    /**
     * 用户状态描述
     */
    private String userStateDesc;

    /**
     * 用户的角色
     */
//    private List<String> roleNameList;

    /**
     * 创建人
     */
    private String createByName;

    /**
     * 更新人
     */
    private String updateByName;

    /**
     * 创建时间，时间戳：单位秒
     */
    private Long createTime;

    /**
     * 最近更新时间，时间戳：单位秒
     */
    private Long updateTime;
}
