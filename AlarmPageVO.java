package com.jinan.alarm.domain.vo.alarmquery;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AlarmPageVO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 回路名称
     */
    private String routeName;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 网关识别号
     */
    private String gatewaySn;

    /**
     * 设备型号
     */
    private String deviceModel;

    /**
     * 仪表地址
     */
    private String meterAddress;

    /**
     * 报警类型
     */
    private String alarmType;

    /**
     * 报警等级
     */
    private String alarmLevel;

    /**
     * 详情描述
     */
    private String alarmDetail;

    /**
     * 报警时间
     */
    private LocalDateTime alarmTime;
}
