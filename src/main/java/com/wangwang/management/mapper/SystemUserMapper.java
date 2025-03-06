package com.wangwang.management.mapper;

import com.wangwang.management.domain.entity.SystemUserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemUserMapper {
    SystemUserEntity selectSystemUserByAccount(@Param("systemAccount") String systemAccount);

    SystemUserEntity selectSystemUserByUserPhone(@Param("userPhone") String userPhone);

    Integer insertSystemUser(SystemUserEntity systemUserEntity);

    Long selectSystemUserPageCount(@Param("userState") Integer userState,
                                   @Param("userNickName") String userNickName,
                                   @Param("systemAccount") String systemAccount,
                                   @Param("userPhone") String userPhone);

    List<SystemUserEntity> selectSystemUserPageList(@Param("userState") Integer userState,
                                                    @Param("userNickName") String userNickName,
                                                    @Param("systemAccount") String systemAccount,
                                                    @Param("userPhone") String userPhone,
                                                    @Param("offSet") Integer offSet,
                                                    @Param("pageSize") Integer pageSize);
}
