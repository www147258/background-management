package com.wangwang.management.mapper;

import com.wangwang.management.domain.entity.DepartmentEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper {

    DepartmentEntity selectDepartmentByName(@Param("departmentName") String departmentName);

    DepartmentEntity selectDepartmentByNumber(@Param("departmentNumber") String departmentNumber);

    void addDepartmentEntity(DepartmentEntity addDepartmentEntity);

    void updateDepartmentEntityById(DepartmentEntity updateDepartmentEntity);


    Long selectDepartmentPageCount(@Param("departmentName") String departmentName, @Param("departmentNumber") String departmentNumber);

    List<DepartmentEntity> selectDepartmentPageList(@Param("departmentName") String departmentName,
                                                    @Param("departmentNumber") String departmentNumber,
                                                    @Param("offSet") Integer offSet,
                                                    @Param("pageSize") Integer pageSize);

    List<DepartmentEntity> selectDepartmentByNumbers(@Param("departmentNumberList") List<String> departmentNumberList);

    List<DepartmentEntity> selectAllNameANdNumber();

    List<DepartmentEntity> selectAllDepartment();
}
