package com.wangwang.management.domain.request.department;

import com.wangwang.management.common.domain.PageRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true)
public class DepartmentListRequest extends PageRequest {

    /**
     * 部门名称，支持前后模糊查询
     */
    private String departmentName;


    /**
     * 部门编码
     */
    private String departmentNumber;
}
