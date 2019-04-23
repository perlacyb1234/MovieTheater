package com.stylefeng.guns.rest.vo.cinema;

import java.io.Serializable;

/**
 * Create by John(LiangZhen)
 * Date 2019/4/23 Time 16:02
 */
public class CinemaInfoVo implements Serializable {

    int cinemaId;
    String imgUrl;
    String cinemaName;
    String cinemaAdress;
    String cinemaPhone;

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getCinemaAdress() {
        return cinemaAdress;
    }

    public void setCinemaAdress(String cinemaAdress) {
        this.cinemaAdress = cinemaAdress;
    }

    public String getCinemaPhone() {
        return cinemaPhone;
    }

    public void setCinemaPhone(String cinemaPhone) {
        this.cinemaPhone = cinemaPhone;
    }

    @Override
    public String toString() {
        return "CinemaInfoVo{" +
                "cinemaId=" + cinemaId +
                ", imgUrl='" + imgUrl + '\'' +
                ", cinemaName='" + cinemaName + '\'' +
                ", cinemaAdress='" + cinemaAdress + '\'' +
                ", cinemaPhone='" + cinemaPhone + '\'' +
                '}';
    }
}
