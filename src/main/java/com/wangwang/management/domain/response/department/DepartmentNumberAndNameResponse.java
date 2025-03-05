package com.wangwang.management.domain.response.department;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@Getter
public class DepartmentNumberAndNameResponse {

    /**
     * 编码
     */
    private String number;

    /**
     * 名字
     */
    private String name;
}
