package com.stylefeng.guns.rest.vo.film;

import java.io.Serializable;

/**
 * Created by Cyb
 * Date 2019/4/23/023  Time 18:49
 */
public class Actor implements Serializable {
    private String imgAddress;
    private String directorName;
    private String roleName;

    public Actor() {
    }

    public Actor(String imgAddress, String directorName, String roleName) {
        this.imgAddress = imgAddress;
        this.directorName = directorName;
        this.roleName = roleName;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
