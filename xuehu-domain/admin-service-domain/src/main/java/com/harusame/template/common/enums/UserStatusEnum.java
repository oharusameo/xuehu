package com.harusame.template.common.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum UserStatusEnum implements IEnum<Integer> {
    AVAILABLE(1, "可用"),
    UNAVAILABLE(0, "不可用");

    private Integer value;
    private String desc;

    UserStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
