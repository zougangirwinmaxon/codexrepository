package com.jinan.alarm.domain.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum AlarmTypeEnum {

    ALL("ALL", "全选", true),
    ENERGY_USAGE("ENERGY_USAGE", "用能报警", false),
    CLUSTER_FAULT("CLUSTER_FAULT", "集群故障报警", false),
    ELECTRICITY_PRICE("ELECTRICITY_PRICE", "电价异常", false),
    UNDERVOLTAGE("UNDERVOLTAGE", "欠压", false),
    OVERVOLTAGE("OVERVOLTAGE", "过压", false),
    OVERCURRENT("OVERCURRENT", "过流", false),
    OVERLOAD("OVERLOAD", "过载", false),
    AMOUNT("AMOUNT", "金额报警", false),
    LEAKAGE("LEAKAGE", "漏电", false),
    OVERHEAT("OVERHEAT", "过温", false),
    FAULT("FAULT", "故障", false),
    SMOKE("SMOKE", "烟感", false),
    ARC("ARC", "电弧", false),
    SHORT_CIRCUIT("SHORT_CIRCUIT", "短路", false),
    POWER_OFF("POWER_OFF", "断电", false),
    LOW_TEMPERATURE("LOW_TEMPERATURE", "低温", false),
    GENERAL("GENERAL", "通用", false),
    OFFLINE("OFFLINE", "离线", false),
    OTHER("OTHER", "其他", false),
    TRANSFORMER_RATIO("TRANSFORMER_RATIO", "变比报警", false),
    METER_READING("METER_READING", "抄表报警", false),
    THEFT_CURRENT("THEFT_CURRENT", "偷流报警", false);

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

    AlarmTypeEnum(String code, String label, boolean queryOnly) {
        this.code = code;
        this.label = label;
        this.queryOnly = queryOnly;
    }

    public static boolean isValidPersistedLabel(String label) {
        return Arrays.stream(values())
            .anyMatch(item -> !item.queryOnly && item.label.equals(label));
    }

    public static List<AlarmTypeEnum> list(boolean includeAll) {
        return Arrays.stream(values())
            .filter(item -> includeAll || !item.queryOnly)
            .toList();
    }
}
