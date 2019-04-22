package com.stylefeng.guns.rest.persistence.Vo;

/**
 * Create by John(LiangZhen)
 * Date 2019/4/21 Time 23:49
 */
public class HalltypeVo {

    int halltypeId;
    String halltypeName;
    boolean isActive;

    public int getHalltypeId() {
        return halltypeId;
    }

    public void setHalltypeId(int halltypeId) {
        this.halltypeId = halltypeId;
    }

    public String getHalltypeName() {
        return halltypeName;
    }

    public void setHalltypeName(String halltypeName) {
        this.halltypeName = halltypeName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "HalltypeVo{" +
                "halltypeId=" + halltypeId +
                ", halltypeName='" + halltypeName + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
