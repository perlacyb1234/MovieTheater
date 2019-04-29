package com.stylefeng.guns.rest.vo.pay;

import java.io.Serializable;

/**
 * Created by Cyb
 * Date 2019/4/26/026  Time 20:45
 */
public class PayInfoVo implements Serializable {
    private String orderId;
    private String QRCodeAddress;
    private CMIV cinemaInfo;
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getQRCodeAddress() {
        return QRCodeAddress;
    }

    public CMIV getCinemaInfo() {
        return cinemaInfo;
    }

    public void setCinemaInfo(CMIV cinemaInfo) {
        this.cinemaInfo = cinemaInfo;
    }

    public void setQRCodeAddress(String QRCodeAddress) {
        this.QRCodeAddress = QRCodeAddress;
    }
}
