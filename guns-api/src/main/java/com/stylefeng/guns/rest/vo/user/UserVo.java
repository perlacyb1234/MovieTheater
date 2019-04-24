package com.stylefeng.guns.rest.vo.user;

import java.io.Serializable;

/**
 * Created by Cyb
 * Date 2019/4/24/024  Time 13:17
 */
public class UserVo implements Serializable {
    private int uuid;
    private String username;
    private String nickname;
    private String email;
    private String phone;
    private String sex;                //0-男，1-女
    private String birthday;
    private String lifeState;          //0-单身，1-热恋中，2-已婚，3-为人父母
    private String biography;
    private String address;
    private String headAddress;
    private long createTime;
    private long updateTime;

    public UserVo() {
    }

    public UserVo(int uuid, String username, String nickname, String email, String phone, String sex, String birthday, String lifeState, String biography, String address, String headAddress, long createTime, long updateTime) {
        this.uuid = uuid;
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
        this.birthday = birthday;
        this.lifeState = lifeState;
        this.biography = biography;
        this.address = address;
        this.headAddress = headAddress;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLifeState() {
        return lifeState;
    }

    public void setLifeState(String lifeState) {
        this.lifeState = lifeState;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }



    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeadAddress() {
        return headAddress;
    }

    public void setHeadAddress(String headAddress) {
        this.headAddress = headAddress;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
