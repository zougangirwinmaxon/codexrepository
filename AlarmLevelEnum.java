package com.jinan.alarm.domain.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum AlarmLevelEnum {

    ALL("ALL", "全选", true),
    NORMAL("NORMAL", "一般", false),
    EMERGENCY("EMERGENCY", "紧急", false),
    SEVERE("SEVERE", "严重", false);

    /**
     * 枚举编码
     */
    private final String code;

    /**
     * 枚举名称
     */
    private final String label;

    /**
     * 是否仅用于查询条件
     */
    private final boolean queryOnly;

    AlarmLevelEnum(String code, String label, boolean queryOnly) {
        this.code = code;
        this.label = label;
        this.queryOnly = queryOnly;
    }

    public static boolean isValidPersistedLabel(String label) {
        return Arrays.stream(values())
            .anyMatch(item -> !item.queryOnly && item.label.equals(label));
    }

    public static List<AlarmLevelEnum> list(boolean includeAll) {
        return Arrays.stream(values())
            .filter(item -> includeAll || !item.queryOnly)
            .toList();
    }
}
