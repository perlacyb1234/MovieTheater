package com.stylefeng.guns.rest.vo.film;

import java.io.Serializable;

/**
 * Created by Cyb
 * Date 2019/4/23/023  Time 14:41
 */
public class YearInfoVo implements Serializable {
    private int yearId;
    private String yearName;
    private boolean isActive;

    public YearInfoVo() {
    }

    public YearInfoVo(int yearId, String yearName, boolean isActive) {
        this.yearId = yearId;
        this.yearName = yearName;
        this.isActive = isActive;
    }

    public int getYearId() {
        return yearId;
    }

    public void setYearId(int yearId) {
        this.yearId = yearId;
    }

    public String getYearName() {
        return yearName;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
