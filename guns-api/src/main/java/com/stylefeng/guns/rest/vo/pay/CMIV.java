package com.stylefeng.guns.rest.vo.pay;

import java.io.Serializable;

/**
 * Created by Cyb
 * Date 2019/4/27/027  Time 12:33
 */
public class CMIV implements Serializable {
    private String orderPrice;

    public CMIV() {
    }

    public CMIV(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }
}
