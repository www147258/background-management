package com.wangwang.management.enums;
import com.wangwang.management.common.interfaces.ResponseInterface;

public enum ResponseCodeAndMsgEnum implements ResponseInterface {

    SUCCESS(0, "操作成功"),

    DATA_ENCRYPTION_AND_DECRYPTION(1, "数据加解密异常,请联系相关技术人员"),

    // 部门       100000 ~ 101000
    DEPARTMENT_NAME_EXISTS(100000, "部门名称已存在,请检查数据"),

    DEPARTMENT_NOT_EXISTS(100001, "部门不存在"),

    // 角色       101001 ~ 102000

    // 菜单       102001 ~ 103000

    // 系统用户     103001 ~ 104000
    SYSTEM_USER_ADD_ACCOUNT_OR_PHONE_HAVE_VALUE(103001, "添加系统用户时,账号或者手机必须有一个有值"),
    SYSTEM_USER_ADD_NICK_NAME_VALUE(103002,"添加系统用户时,用户昵称不能为空"),
    SYSTEM_USER_ADD_PASSWORD_VALUE(103003,"添加系统用户时,用户密码不能为空"),

    SYSTEM_USER_ADD_ACCOUNT_EXISTS(103004,"添加系统用户时,用户账号已存在"),

    SYSTEM_USER_ADD_USER_PHONE_EXISTS(103005,"添加系统用户时,用户手机号已存在"),
    SYSTEM_USER_ADD_ACCOUNT_TOO_LONG(103006, "添加系统用户时,账号位数不能超过16个字符"),

    SYSTEM_USER_ADD_USER_PHONE_FORMAT_ERROR(103007, "添加系统用户时,手机号格式错误"),





    ;


    private Integer code;

    private String msg;

    ResponseCodeAndMsgEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}

