package com.wangwang.management.controller;

import com.wangwang.management.common.domain.CommonResponse;
import com.wangwang.management.common.domain.PageResponse;
import com.wangwang.management.domain.request.department.DepartmentAddRequest;
import com.wangwang.management.domain.request.department.DepartmentListRequest;
import com.wangwang.management.domain.response.department.DepartmentListResponse;
import com.wangwang.management.domain.response.department.DepartmentNumberAndNameResponse;
import com.wangwang.management.domain.response.department.DepartmentTreeResponse;
import com.wangwang.management.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @GetMapping("/department/getDepartmentPageList")
    public CommonResponse<PageResponse<DepartmentListResponse>> getDepartmentPageList(DepartmentListRequest departmentListRequest) {
        PageResponse<DepartmentListResponse> pageResponse = departmentService.getDepartmentPageList(departmentListRequest);
        return CommonResponse.success(pageResponse);
    }



    /**
     * 部门树
     */
    @GetMapping("/department/getDepartmentTree")
    public CommonResponse<DepartmentTreeResponse> getDepartmentTree() {
        DepartmentTreeResponse departmentTreeResponse = departmentService.getDepartmentTree();
        return CommonResponse.success(departmentTreeResponse);
    }

    /**
     * 添加部门
     */
    @PostMapping("/department/addDepartment")
    public CommonResponse addDepartment(@RequestBody DepartmentAddRequest departmentAddRequest) {
        departmentService.addDepartment(departmentAddRequest);
        return CommonResponse.success();
    }

    /**
     * 获取所有的部门编码和名字
     */
    @GetMapping("/department/getDepartmentNumberAadName")
    public CommonResponse<List<DepartmentNumberAndNameResponse>> getDepartmentNumberAadName() {
        List<DepartmentNumberAndNameResponse>  departmentNumberAndNameResponses= departmentService.getDepartmentNumberAadName();
        return CommonResponse.success(departmentNumberAndNameResponses);
    }




}
