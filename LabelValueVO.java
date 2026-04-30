package com.jinan.alarm.domain.vo.alarmquery;

import lombok.Data;

@Data
public class LabelValueVO {

    /**
     * 显示名称
     */
    private String label;

    /**
     * 实际值
     */
    private String value;

    public LabelValueVO() {
    }

    public LabelValueVO(String label, String value) {
        this.label = label;
        this.value = value;
    }
}
