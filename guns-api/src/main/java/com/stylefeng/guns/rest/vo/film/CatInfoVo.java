package com.stylefeng.guns.rest.vo.film;

import java.io.Serializable;

/**
 * Created by Cyb
 * Date 2019/4/23/023  Time 14:36
 */
public class CatInfoVo implements Serializable {
    private int catId;
    private String catName;
    private boolean isActive;

    public CatInfoVo() {
    }

    public CatInfoVo(int catId, String catName, boolean isActive) {
        this.catId = catId;
        this.catName = catName;
        this.isActive = isActive;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
