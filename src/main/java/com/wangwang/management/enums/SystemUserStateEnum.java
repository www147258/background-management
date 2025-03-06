package com.wangwang.management.enums;

import java.util.Objects;

public enum SystemUserStateEnum {
    NORMAL(1, "正常"),
    LOCK(2, "锁定"),
    DISABLE(3, "禁用"),
    UNKNOWN(-1, "未知");

    private Integer code;

    private String desc;

    SystemUserStateEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SystemUserStateEnum valueCode(Integer userState) {
        if (Objects.isNull(userState)) {
            return UNKNOWN;
        }
        SystemUserStateEnum[] values = SystemUserStateEnum.values();
        for (SystemUserStateEnum value : values) {
            if (value.getCode().equals(userState)) {
                return value;
            }
        }


        return UNKNOWN;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
