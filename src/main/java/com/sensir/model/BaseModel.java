package com.sensir.model;

import java.util.Date;

/**
 * Created by leon on 16-11-3.
 */
public class BaseModel {
    public static final int DEF_NUM = 1;
    /**
     * 主键(sql自增，不用处理)
     */
    protected Long id;
    /**
     * 状态 1正常
     */
    protected Integer dataStatus = DEF_NUM;
    /**
     * 创建时间
     */
    protected Date createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
