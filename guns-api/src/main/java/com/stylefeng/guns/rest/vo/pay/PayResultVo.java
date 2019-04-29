package com.stylefeng.guns.rest.vo.pay;

import java.io.Serializable;

/**
 * Created by Cyb
 * Date 2019/4/26/026  Time 23:34
 */
public class PayResultVo implements Serializable {
    private String orderId;
    private int orderStatus;
    private String orderMsg;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderMsg() {
        return orderMsg;
    }

    public void setOrderMsg(String orderMsg) {
        this.orderMsg = orderMsg;
    }
}
