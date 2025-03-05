package com.wangwang.management.domain.response.department;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@Getter
public class DepartmentListResponse {

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
     * 父级别部门名称
     */
    private String parentDepartmentName;

    /**
     * 父级别部门编号
     */
    private String parentDepartmentNumber;

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
