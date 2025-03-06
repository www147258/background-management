package com.wangwang.management.service;

import com.wangwang.management.common.domain.PageResponse;
import com.wangwang.management.common.exception.BusinessException;
import com.wangwang.management.common.utils.StringUtils;
import com.wangwang.management.config.SystemConfig;
import com.wangwang.management.convert.ResponseConvert;
import com.wangwang.management.domain.entity.SystemUserEntity;
import com.wangwang.management.domain.request.systemuser.SystemUserAddRequest;
import com.wangwang.management.domain.request.systemuser.SystemUserPageListRequest;
import com.wangwang.management.domain.response.department.DepartmentListResponse;
import com.wangwang.management.domain.response.systemuser.SystemUserPageListResponse;
import com.wangwang.management.enums.ResponseCodeAndMsgEnum;
import com.wangwang.management.enums.SystemUserStateEnum;
import com.wangwang.management.mapper.SystemUserMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class SystemUserService {

    private final SystemConfig systemConfig;

    private final SystemUserMapper systemUserMapper;

    public SystemUserService(SystemConfig systemConfig, SystemUserMapper systemUserMapper) {
        this.systemConfig = systemConfig;
        this.systemUserMapper = systemUserMapper;
    }


    public void addSystemUser(SystemUserAddRequest systemUserAddRequest) {

        // 检查参数
        checkAddSystemUserParam(systemUserAddRequest);

        // 检查用户的账号或者手机号是否已经存在表里
        checkAddSystemUserAccountOrPhoneIsExists(systemUserAddRequest);

        // 保存用户
        systemUserMapper.insertSystemUser(buildSystemUserEntity(systemUserAddRequest));

    }

    private SystemUserEntity buildSystemUserEntity(SystemUserAddRequest systemUserAddRequest) {
        SystemUserEntity systemUserEntity = new SystemUserEntity();

        systemUserEntity.setCreateById(0L);
        systemUserEntity.setCreateByName("SYSTEM");
        systemUserEntity.setCreateTime(System.currentTimeMillis() / 1000);

        systemUserEntity.setUpdateById(0L);
        systemUserEntity.setUpdateByName("SYSTEM");
        systemUserEntity.setUpdateTime(System.currentTimeMillis() / 1000);

        systemUserEntity.setSystemAccount(StringUtils.defaultString(systemUserAddRequest.getSystemAccount()));
        systemUserEntity.setUserPhone(StringUtils.defaultString(systemUserAddRequest.getUserPhone()));
        systemUserEntity.setUserState(SystemUserStateEnum.NORMAL.getCode());
        // 暂时不加密
        systemUserEntity.setPassword(systemUserAddRequest.getPassword());
        systemUserEntity.setNickName(systemUserAddRequest.getNickName());
        return systemUserEntity;
    }

    private void checkAddSystemUserAccountOrPhoneIsExists(SystemUserAddRequest systemUserAddRequest) {

        if (StringUtils.isNotBlank(systemUserAddRequest.getSystemAccount())) {
            String systemAccount = StringUtils.encrypt(systemUserAddRequest.getSystemAccount(), systemConfig.getSecretKey());
            // 查询表是否存在
            if (Objects.nonNull(systemUserMapper.selectSystemUserByAccount(systemAccount))) {
                // 账号已经存在
                throw new BusinessException(ResponseCodeAndMsgEnum.SYSTEM_USER_ADD_ACCOUNT_EXISTS);
            }
            systemUserAddRequest.setSystemAccount(systemAccount);
        }

        if (StringUtils.isNotBlank(systemUserAddRequest.getUserPhone())) {
            String userPhone = StringUtils.encrypt(systemUserAddRequest.getUserPhone(), systemConfig.getSecretKey());
            // 查询表是否存在
            if (Objects.nonNull(systemUserMapper.selectSystemUserByUserPhone(userPhone))) {
                // 手机号已经存在
                throw new BusinessException(ResponseCodeAndMsgEnum.SYSTEM_USER_ADD_USER_PHONE_EXISTS);
            }
            systemUserAddRequest.setUserPhone(userPhone);
        }
    }

    private void checkAddSystemUserParam(SystemUserAddRequest systemUserAddRequest) {
        String systemAccount = systemUserAddRequest.getSystemAccount();

        String userPhone = systemUserAddRequest.getUserPhone();

        if (StringUtils.isBlank(systemAccount) && StringUtils.isBlank(userPhone)) {
            // 用户账号和手机号  必须一个有值
            throw new BusinessException(ResponseCodeAndMsgEnum.SYSTEM_USER_ADD_ACCOUNT_OR_PHONE_HAVE_VALUE);
        }

        if (StringUtils.isNotBlank(systemAccount) && systemAccount.length() > 16) {
            throw new BusinessException(ResponseCodeAndMsgEnum.SYSTEM_USER_ADD_ACCOUNT_TOO_LONG);
        }

        if (StringUtils.isNotBlank(userPhone) && userPhone.length() != 11) {
            throw new BusinessException(ResponseCodeAndMsgEnum.SYSTEM_USER_ADD_USER_PHONE_FORMAT_ERROR);
        }

        String password = systemUserAddRequest.getPassword();
        if (StringUtils.isBlank(password)) {
            // 密码必须有
            throw new BusinessException(ResponseCodeAndMsgEnum.SYSTEM_USER_ADD_PASSWORD_VALUE);

        }
        String nickName = systemUserAddRequest.getNickName();
        if (StringUtils.isBlank(nickName)) {
            // 昵称必须有
            throw new BusinessException(ResponseCodeAndMsgEnum.SYSTEM_USER_ADD_NICK_NAME_VALUE);

        }
    }

    public PageResponse<SystemUserPageListResponse> getSystemUserPageList(SystemUserPageListRequest systemUserPageListRequest) {
        String systemAccount = systemUserPageListRequest.getSystemAccount();
        String userPhone = systemUserPageListRequest.getUserPhone();
        Integer userState = systemUserPageListRequest.getUserState();
        String userNickName = systemUserPageListRequest.getUserNickName();
        systemAccount = StringUtils.isNotBlank(systemAccount)? StringUtils.encrypt(systemAccount,systemConfig.getSecretKey()) : systemAccount;
        userPhone = StringUtils.isNotBlank(userPhone) ? StringUtils.encrypt(userPhone, systemConfig.getSecretKey()) : userPhone ;

        Long count = systemUserMapper.selectSystemUserPageCount(userState, userNickName, systemAccount , userPhone);

        PageResponse<SystemUserPageListResponse> pageResponse = new PageResponse<>();
        pageResponse.setPageIndex(systemUserPageListRequest.getPageIndex());
        pageResponse.setPageSize(systemUserPageListRequest.getPageSize());
        pageResponse.setTotal(count);
        if (Objects.isNull(pageResponse.getTotal()) || pageResponse.getTotal() < 1L) {
            return pageResponse;
        }

        Integer offSet = (systemUserPageListRequest.getPageIndex() - 1) * systemUserPageListRequest.getPageSize();
        List<SystemUserEntity> systemUserEntities = systemUserMapper.selectSystemUserPageList(userState, userNickName, systemAccount, userPhone,
                offSet, systemUserPageListRequest.getPageSize());
        if (CollectionUtils.isNotEmpty(systemUserEntities)) {
            systemUserEntities.forEach(systemUserEntity -> {
                if (StringUtils.isNotBlank(systemUserEntity.getSystemAccount())) {
                    systemUserEntity.setSystemAccount(StringUtils.decrypt(systemUserEntity.getSystemAccount(), systemConfig.getSecretKey()));
                }
                if (StringUtils.isNotBlank(systemUserEntity.getUserPhone())) {
                    systemUserEntity.setUserPhone(StringUtils.decrypt(systemUserEntity.getUserPhone(), systemConfig.getSecretKey()));
                }

            });
        }
        pageResponse.setRows(ResponseConvert.convertSystemUserPageListResponse(systemUserEntities));
        return pageResponse;
    }
}
