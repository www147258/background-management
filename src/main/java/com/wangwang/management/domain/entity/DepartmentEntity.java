package com.wangwang.management.domain.entity;

import com.wangwang.management.common.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true)
public class DepartmentEntity extends BaseEntity {

    /**
     * 部门编码
     */
    private String departmentNumber;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 上级部门编码
     */
    private String parentDepartmentNumber;

    /**
     * 部门路径
     */
    private String departmentPath;
}
