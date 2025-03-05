package com.wangwang.management.service;

import com.wangwang.management.common.utils.StringUtils;
import com.wangwang.management.domain.entity.DepartmentEntity;
import com.wangwang.management.domain.request.department.DepartmentAddRequest;
import com.wangwang.management.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DepartmentService {

    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    /**
     * 新增部门
     * @param departmentAddRequest
     */
    public void addDepartment(DepartmentAddRequest departmentAddRequest) {

        String departmentName = departmentAddRequest.getDepartmentName();
        // 检查名称是否存在
        DepartmentEntity department = departmentMapper.selectDepartmentByName(departmentName);


        DepartmentEntity addDepartmentEntity = new DepartmentEntity();
        addDepartmentEntity.setCreateById(0L);
        addDepartmentEntity.setCreateByName("SYSTEM");
        addDepartmentEntity.setCreateTime(System.currentTimeMillis() / 1000);

        addDepartmentEntity.setUpdateById(0L);
        addDepartmentEntity.setUpdateByName("SYSTEM");
        addDepartmentEntity.setUpdateTime(System.currentTimeMillis() / 1000);
        addDepartmentEntity.setDepartmentName(departmentName);
        if (Objects.nonNull(department)) {
            throw new RuntimeException("部门名字重复，请重新修改名字添加");
        }

        String parentDepartmentNumber = departmentAddRequest.getParentDepartmentNumber();
        if (StringUtils.isNotBlank(parentDepartmentNumber)) {
            DepartmentEntity parentDepartmentEntity = departmentMapper.selectDepartmentByNnumber(parentDepartmentNumber);
            if (Objects.isNull(parentDepartmentEntity)) {
                throw new RuntimeException("上级部门不存在");
            }


            addDepartmentEntity.setParentDepartmentNumber(parentDepartmentNumber);

            addDepartmentEntity.setDepartmentPath(parentDepartmentEntity.getDepartmentPath());
        }

        departmentMapper.addDepartmentEntity(addDepartmentEntity);

        DepartmentEntity updateDepartmentEntity = new DepartmentEntity();
        updateDepartmentEntity.setId(addDepartmentEntity.getId());

        updateDepartmentEntity.setDepartmentNumber("D"+String.format("%05d", updateDepartmentEntity.getId()));

        updateDepartmentEntity.setDepartmentPath(StringUtils.defaultString(addDepartmentEntity.getDepartmentPath()) +"/" + updateDepartmentEntity.getDepartmentNumber());

        departmentMapper.updateDepartmentEntityById(updateDepartmentEntity);
    }
}
