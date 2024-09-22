package io.github.carrothole.carrot.enums;

import lombok.Getter;

/**
 * 菜单范围枚举
 *
 * @author moon
 * @since 0.0.1
 */
@Getter
public enum MenuRangeEnum {
    ALL("全部菜单"), CUSTOM("自定义");

    private final String desc;

    MenuRangeEnum(String desc) {
        this.desc = desc;
    }

}
