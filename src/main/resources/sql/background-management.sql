CREATE TABLE `t_department` (
    `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `department_number` varchar(32)  NOT NULL DEFAULT '' COMMENT '部门编码，D开头',
    `department_name` varchar(32)  NOT NULL DEFAULT '' COMMENT '部门名称，不能重复',
    `parent_department_number` varchar(32)  NOT NULL DEFAULT '' COMMENT '上级部门编码，没有标示顶级部门',
    `department_path` varchar(255)  NOT NULL DEFAULT '' COMMENT '部门的路径,包含当前部门',
    `create_by_id` bigint NOT NULL DEFAULT '0' COMMENT '创建人Id',
    `create_by_name` varchar(32)  NOT NULL DEFAULT '' COMMENT '创建人名字',
    `create_time` bigint NOT NULL DEFAULT '0' COMMENT '创建时间，时间戳单位秒',
    `update_by_id` bigint NOT NULL DEFAULT '0' COMMENT '更新人Id',
    `update_by_name` varchar(32)  NOT NULL DEFAULT '' COMMENT '更新人名字',
    `update_time` bigint NOT NULL DEFAULT '0' COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;