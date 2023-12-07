package com.harusame.template.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum CreateCategoryEnum implements IEnum<Integer> {
    CREATE_BY_SYSTEM(1, "系统创建"),
    CREATE_BY_USER(2, "用户创建")
    ;

    private Integer value;
    private String desc;

    CreateCategoryEnum(Integer value, String desc) {
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
