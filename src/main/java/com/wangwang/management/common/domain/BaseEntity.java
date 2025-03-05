package com.wangwang.management.common.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BaseEntity {

    /**
     * 主键Id
     */
    private Long id;

    /**
     * 创建人Id
     */
    private Long createById;

    /**
     * 创建人名称
     */
    private String createByName;

    /**
     * 创建时间  时间戳，单位秒
     */
    private Long createTime;

    /**
     * 更新人Id
     */
    private Long updateById;

    /**
     * 更新人名称
     */
    private String updateByName;

    /**
     * 更新时间  时间戳，单位秒
     */
    private Long updateTime;
}
