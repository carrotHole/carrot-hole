package io.github.carrothole.carrot.enums;

import lombok.Getter;

/**
 * 状态枚举
 */
@Getter
public enum StatusEnum {

    NORMAL("正常"), DISABLE("禁用");

    private final String desc;

    StatusEnum(String desc) {
        this.desc = desc;
    }

}
