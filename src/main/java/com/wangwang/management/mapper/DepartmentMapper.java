package com.wangwang.management.mapper;

import com.wangwang.management.domain.entity.DepartmentEntity;
import org.apache.ibatis.annotations.Param;

public interface DepartmentMapper {

    DepartmentEntity selectDepartmentByName(@Param("departmentName") String departmentName);

    DepartmentEntity selectDepartmentByNnumber(@Param("departmentNumber") String departmentNumber);

    void addDepartmentEntity(DepartmentEntity addDepartmentEntity);

    void updateDepartmentEntityById(DepartmentEntity updateDepartmentEntity);


}
