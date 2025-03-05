package com.wangwang.management.common.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

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


    public Integer getPageIndex() {

        pageIndex = Objects.isNull(pageIndex) ? 1: pageIndex;
        pageIndex = pageIndex < 1 ? 1 : pageIndex;

        return pageIndex;

    }

    public Integer getPageSize() {

        pageSize = Objects.isNull(pageSize) ? 10 : pageSize;

        pageSize = pageSize < 1 ? 1 : pageSize;

        pageSize = pageSize > 500 ? 500 : pageSize;

        return pageSize;
    }

}