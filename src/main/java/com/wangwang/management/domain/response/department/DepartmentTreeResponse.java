package com.wangwang.management.domain.response.department;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@ToString
@Getter
public class DepartmentTreeResponse {

    /**
     * 部门Id
     */
    private Long departmentId;


    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 部门编码
     */
    private String departmentNumber;

    /**
     * 子部门
     */
    private List<DepartmentTreeResponse> childDepartments;

}
