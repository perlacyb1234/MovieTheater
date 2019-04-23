package com.stylefeng.guns.rest.vo.cinema;


import java.io.Serializable;

/**
 * Create by John(LiangZhen)
 * Date 2019/4/21 Time 12:15
 */
public class CinemaVo implements Serializable {

   // private static final long serialVersionUID = 1L;

    int uuid;
    String cinemaName;
    String address;
    int minimumPrice;

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(int minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    @Override
    public String toString() {
        return "CinemaVo{" +
                "uuid=" + uuid +
                ", cinemaName='" + cinemaName + '\'' +
                ", address='" + address + '\'' +
                ", minimumPrice=" + minimumPrice +
                '}';
    }

}
