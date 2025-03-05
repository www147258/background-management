package com.wangwang.management.convert;

import com.wangwang.management.common.utils.StringUtils;
import com.wangwang.management.domain.entity.DepartmentEntity;
import com.wangwang.management.domain.response.department.DepartmentListResponse;
import com.wangwang.management.domain.response.department.DepartmentNumberAndNameResponse;
import com.wangwang.management.domain.response.department.DepartmentTreeResponse;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class ResponseConvert {
    public static List<DepartmentListResponse> convertDepartmentListResponse(List<DepartmentEntity> departmentEntities,
                                                                             Map<String, String> departmentNumberRefNameMap) {

        if (CollectionUtils.isEmpty(departmentEntities)) {
            return Collections.emptyList();
        }


        return departmentEntities.stream().map(departmentEntity -> convertDepartmentListResponse(departmentEntity, departmentNumberRefNameMap))
                .filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static DepartmentListResponse convertDepartmentListResponse (DepartmentEntity department ,
                                                                 Map<String, String> departmentNumberRefNameMap) {
        DepartmentListResponse departmentListResponse = new DepartmentListResponse();
        departmentListResponse.setDepartmentId(department.getId());
        departmentListResponse.setDepartmentName(department.getDepartmentName());
        departmentListResponse.setDepartmentNumber(department.getDepartmentNumber());
        departmentListResponse.setParentDepartmentNumber(department.getParentDepartmentNumber());
        String parentDepartmentName = null;
        if (Objects.nonNull(departmentNumberRefNameMap) && !departmentNumberRefNameMap.isEmpty()) {
            parentDepartmentName = departmentNumberRefNameMap.get(departmentListResponse.getParentDepartmentNumber());
        }
        departmentListResponse.setParentDepartmentName(StringUtils.defaultString(parentDepartmentName));
        departmentListResponse.setCreateByName(department.getCreateByName());
        departmentListResponse.setUpdateByName(department.getUpdateByName());
        departmentListResponse.setCreateTime(department.getCreateTime());
        departmentListResponse.setUpdateTime(department.getUpdateTime());
        return departmentListResponse;



    }

    public static List<DepartmentNumberAndNameResponse>  convertDepartmentNumberAndNameResponse(List<DepartmentEntity> departmentEntities) {
        if (CollectionUtils.isEmpty(departmentEntities)) {
            return Collections.emptyList();
        }

        return departmentEntities.stream().map(department -> {
            DepartmentNumberAndNameResponse response = new DepartmentNumberAndNameResponse();

            response.setName(StringUtils.defaultString(department.getDepartmentName()));
            response.setNumber(StringUtils.defaultString(department.getDepartmentNumber()));
            return response;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static DepartmentTreeResponse convertDepartmentTreeResponse(DepartmentEntity department) {
        DepartmentTreeResponse departmentTreeResponse = new DepartmentTreeResponse();
        departmentTreeResponse.setDepartmentId(department.getId());
        departmentTreeResponse.setDepartmentName(department.getDepartmentName());
        departmentTreeResponse.setDepartmentNumber(department.getDepartmentNumber());
        departmentTreeResponse.setChildDepartments(new ArrayList<>());
        return departmentTreeResponse;
    }
}
