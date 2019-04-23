package com.stylefeng.guns.rest.vo.film;

import java.io.Serializable;

/**
 * Created by Cyb
 * Date 2019/4/23/023  Time 14:38
 */
public class SourceInfoVo implements Serializable {

    private int sourceId;
    private String sourceName;
    private boolean isActive;

    public SourceInfoVo() {
    }

    public SourceInfoVo(int sourceId, String sourceName, boolean isActive) {
        this.sourceId = sourceId;
        this.sourceName = sourceName;
        this.isActive = isActive;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
