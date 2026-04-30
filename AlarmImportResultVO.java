package com.jinan.alarm.domain.vo.alarmquery;

import lombok.Data;

@Data
public class AlarmImportResultVO {

    /**
     * 导入总记录数
     */
    private Integer totalCount;

    /**
     * 新增记录数
     */
    private Integer insertedCount;

    /**
     * 更新记录数
     */
    private Integer updatedCount;
}
