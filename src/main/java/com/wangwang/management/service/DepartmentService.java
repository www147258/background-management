package com.wangwang.management.service;

import com.wangwang.management.common.exception.BusinessException;
import com.wangwang.management.convert.ResponseConvert;
import com.wangwang.management.common.domain.PageResponse;
import com.wangwang.management.common.utils.StringUtils;
import com.wangwang.management.domain.entity.DepartmentEntity;
import com.wangwang.management.domain.request.department.DepartmentAddRequest;
import com.wangwang.management.domain.request.department.DepartmentListRequest;
import com.wangwang.management.domain.response.department.DepartmentListResponse;
import com.wangwang.management.domain.response.department.DepartmentNumberAndNameResponse;
import com.wangwang.management.domain.response.department.DepartmentTreeResponse;
import com.wangwang.management.enums.ResponseCodeAndMsgEnum;
import com.wangwang.management.mapper.DepartmentMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    /**
     * 新增部门
     *
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
            throw new BusinessException(ResponseCodeAndMsgEnum.DEPARTMENT_NAME_EXISTS);
        }

        String parentDepartmentNumber = departmentAddRequest.getParentDepartmentNumber();
        if (StringUtils.isNotBlank(parentDepartmentNumber)) {
            DepartmentEntity parentDepartmentEntity = departmentMapper.selectDepartmentByNumber(parentDepartmentNumber);
            if (Objects.isNull(parentDepartmentEntity)) {
                throw new BusinessException(ResponseCodeAndMsgEnum.DEPARTMENT_NOT_EXISTS);
            }


            addDepartmentEntity.setParentDepartmentNumber(parentDepartmentNumber);

            addDepartmentEntity.setDepartmentPath(parentDepartmentEntity.getDepartmentPath());
        }

        departmentMapper.addDepartmentEntity(addDepartmentEntity);

        DepartmentEntity updateDepartmentEntity = new DepartmentEntity();
        updateDepartmentEntity.setId(addDepartmentEntity.getId());

        updateDepartmentEntity.setDepartmentNumber("D" + String.format("%05d", updateDepartmentEntity.getId()));

        updateDepartmentEntity.setDepartmentPath(StringUtils.defaultString(addDepartmentEntity.getDepartmentPath()) + "/" + updateDepartmentEntity.getDepartmentNumber());

        departmentMapper.updateDepartmentEntityById(updateDepartmentEntity);
    }

    public PageResponse<DepartmentListResponse> getDepartmentPageList(DepartmentListRequest departmentListRequest) {


        String departmentName = departmentListRequest.getDepartmentName();
        String departmentNumber = departmentListRequest.getDepartmentNumber();

        Long count = departmentMapper.selectDepartmentPageCount(departmentName, departmentNumber);

        PageResponse<DepartmentListResponse> pageResponse = new PageResponse<>();
        pageResponse.setPageIndex(departmentListRequest.getPageIndex());
        pageResponse.setPageSize(departmentListRequest.getPageSize());
        pageResponse.setTotal(count);
        if (Objects.isNull(pageResponse.getTotal()) || pageResponse.getTotal() < 1L) {
            return pageResponse;
        }
        Integer pageIndex = departmentListRequest.getPageIndex();
        Integer pageSize = departmentListRequest.getPageSize();

        Integer offSet = (pageIndex - 1) * pageSize;

        List<DepartmentEntity> departmentEntities = departmentMapper.selectDepartmentPageList(departmentName, departmentNumber, offSet, pageSize);

        if (CollectionUtils.isEmpty(departmentEntities)) {
            pageResponse.setRows(Collections.emptyList());
            return pageResponse;
        }


        List<String> departmentNumberList = departmentEntities.stream().map(DepartmentEntity::getParentDepartmentNumber)
                .filter(StringUtils::isNotBlank).distinct().collect(Collectors.toList());

        Map<String, String> departmentNumberRefNameMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(departmentNumberList)) {

            List<DepartmentEntity> departmentEntityList = departmentMapper.selectDepartmentByNumbers(departmentNumberList);
            departmentNumberRefNameMap.putAll(
                    departmentEntityList.stream()
                            .collect(Collectors.toMap(DepartmentEntity::getDepartmentNumber, DepartmentEntity::getDepartmentName))
            );
        }

        pageResponse.setRows(ResponseConvert.convertDepartmentListResponse(departmentEntities, departmentNumberRefNameMap));
        return pageResponse;
    }

    public List<DepartmentNumberAndNameResponse> getDepartmentNumberAadName() {
        return ResponseConvert.convertDepartmentNumberAndNameResponse(departmentMapper.selectAllNameANdNumber());
    }

    public DepartmentTreeResponse getDepartmentTree() {

        List<DepartmentEntity> departmentEntities = departmentMapper.selectAllDepartment();
        if (CollectionUtils.isEmpty(departmentEntities)) {
            return null;
        }

        String root = "root";

        Map<String, DepartmentTreeResponse> departmentTreeResponseMap = new HashMap<>();
        departmentEntities.forEach(department -> {

            String parentDepartmentNumber = department.getParentDepartmentNumber();
            DepartmentTreeResponse departmentTreeResponse = ResponseConvert.convertDepartmentTreeResponse(department);
            departmentTreeResponseMap.put(departmentTreeResponse.getDepartmentNumber(), departmentTreeResponse);

            if (StringUtils.isBlank(parentDepartmentNumber)) {
                departmentTreeResponseMap.put(root, departmentTreeResponse);
            } else {
                DepartmentTreeResponse parentDepartmentTreeResponse = departmentTreeResponseMap.get(parentDepartmentNumber);
                if (Objects.nonNull(parentDepartmentTreeResponse)) {
                    parentDepartmentTreeResponse.getChildDepartments().add(departmentTreeResponse);
                }
            }

        });

        return departmentTreeResponseMap.get(root);


    }
}
