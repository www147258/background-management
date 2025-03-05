package com.wangwang.management.controller;

import com.wangwang.management.common.domain.CommonResponse;
import com.wangwang.management.domain.request.department.DepartmentAddRequest;
import com.wangwang.management.service.DepartmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部门管理
 */
@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    /**
     * 部门列表
     */

    /**
     * 部门树
     */


    /**
     * 添加部门
     */
    @PostMapping("/department/addDepartment")
    public CommonResponse addDepartment(@RequestBody DepartmentAddRequest departmentAddRequest) {
        departmentService.addDepartment(departmentAddRequest);
        return CommonResponse.success();
    }


}
