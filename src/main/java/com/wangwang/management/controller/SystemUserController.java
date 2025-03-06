package com.wangwang.management.controller;

import com.wangwang.management.common.domain.CommonResponse;
import com.wangwang.management.common.domain.PageResponse;
import com.wangwang.management.domain.request.systemuser.SystemUserAddRequest;
import com.wangwang.management.domain.request.systemuser.SystemUserPageListRequest;
import com.wangwang.management.domain.response.systemuser.SystemUserPageListResponse;
import com.wangwang.management.service.SystemUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统用户管理
 */
@RestController
public class SystemUserController {


    private final SystemUserService systemUserService;

    public SystemUserController(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    /**
     * 用户列表
     */
    @GetMapping("/system/user/getSystemUserPageList")
    public CommonResponse<PageResponse<SystemUserPageListResponse>> getSystemUserPageList(SystemUserPageListRequest systemUserPageListRequest) {
        PageResponse<SystemUserPageListResponse> pageResponse =  systemUserService.getSystemUserPageList(systemUserPageListRequest);
        return CommonResponse.success(pageResponse);
    }


    /**
     * 用户添加
     */
    @PostMapping("/system/user/addSystemUser")
    public CommonResponse addSystemUser(@RequestBody SystemUserAddRequest systemUserAddRequest) {
        systemUserService.addSystemUser(systemUserAddRequest);
        return CommonResponse.success();

    }



    /**
     * 重置密码
     */

    /**
     * 用户禁用
     */

    /**
     * 用户分配角色
     */


    /**
     * 用户解锁
     */

    /**
     * 获取当前用户信息
     */

    /**
     * 用户登陆
     */


}
