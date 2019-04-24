package com.stylefeng.guns.rest.vo.cinema;

import java.io.Serializable;

/**
 * Create by John(LiangZhen)
 * Date 2019/4/23 Time 23:34
 */
public class HallInfoVo implements Serializable {

    int hallFieldId;
    String hallName;
    int price;
    String seatFile;
    String soldSeats;

    public int getHallFieldId() {
        return hallFieldId;
    }

    public void setHallFieldId(int hallFieldId) {
        this.hallFieldId = hallFieldId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSeatFile() {
        return seatFile;
    }

    public void setSeatFile(String seatFile) {
        this.seatFile = seatFile;
    }

    public String getSoldSeats() {
        return soldSeats;
    }

    public void setSoldSeats(String soldSeats) {
        this.soldSeats = soldSeats;
    }

    @Override
    public String toString() {
        return "HallInfoVo{" +
                "hallFieldId=" + hallFieldId +
                ", hallName='" + hallName + '\'' +
                ", price=" + price +
                ", seatFile='" + seatFile + '\'' +
                ", soldSeats='" + soldSeats + '\'' +
                '}';
    }
}
