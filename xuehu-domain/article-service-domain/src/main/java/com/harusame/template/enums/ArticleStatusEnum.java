package com.harusame.template.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum ArticleStatusEnum implements IEnum<Integer> {
    PENDING(1, "待审核"),
    APPROVED(2, "审核通过"),
    MANUAL_AUDIT_REQUIRED(3, "需要人工复审"),
    FAILED_AUDIT(4, "审核不通过"),

    ;

    private Integer value;
    private String desc;

    ArticleStatusEnum(Integer value, String desc) {
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
