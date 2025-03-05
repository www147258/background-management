package com.wangwang.management.domain.request.department;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DepartmentAddRequest {
    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 上级部门编码
     */
    private String parentDepartmentNumber;
}