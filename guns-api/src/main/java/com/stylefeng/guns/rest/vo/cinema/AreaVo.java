package com.stylefeng.guns.rest.vo.cinema;

import java.io.Serializable;

/**
 * Create by John(LiangZhen)
 * Date 2019/4/21 Time 23:48
 */

public class AreaVo implements Serializable {

    int areaId;
    String areaName;
    boolean isActive;

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "AreaVo{" +
                "areaId=" + areaId +
                ", areaName='" + areaName + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
