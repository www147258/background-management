package com.wangwang.management.common.domain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class PageResponse<T> {
    /**
     * 第几页
     */
    private Integer pageIndex;

    /**
     * 一页显示数据
     */
    private Integer pageSize;

    /**
     * 总条数
     */
    private Long total;

    /**
     * 响应数据
     */
    private List<T> rows;

}
