package com.wangwang.management.common.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PageRequest {

    /**
     * 第几页
     */
    private Integer pageIndex;

    /**
     * 一页显示数据
     */
    private Integer pageSize;
}