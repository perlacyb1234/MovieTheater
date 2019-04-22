package com.stylefeng.guns.rest.persistence.Vo;

/**
 * Create by John(LiangZhen)
 * Date 2019/4/21 Time 23:13
 */
public class BrandVo {

    int brandId;
    String brandName;
    boolean isActive;

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "BrandVo{" +
                "brandId=" + brandId +
                ", brandName='" + brandName + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
