package com.stylefeng.guns.rest.vo.order;

import java.io.Serializable;

/**
 * Created by Cyb
 * Date 2019/4/25/025  Time 11:37
 */
//如果要在server和client中传递，则要实现序列化，但是为什么？？
public class OrderVo implements Serializable {
    private String orderId;
    private String filmName;
    private String fieldTime;
    private String cinemaName;
    private String seatsName;
    private String orderPrice;
    private Long orderTimestamp;       //订单时间？
    private String orderStatus;

    public OrderVo() {
    }

    public OrderVo(String orderId, String filmName, String fieldTime, String cinemaName, String seatsName, String orderPrice, Long orderTimestamp, String orderStatus) {
        this.orderId = orderId;
        this.filmName = filmName;
        this.fieldTime = fieldTime;
        this.cinemaName = cinemaName;
        this.seatsName = seatsName;
        this.orderPrice = orderPrice;
        this.orderTimestamp = orderTimestamp;
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getFieldTime() {
        return fieldTime;
    }

    public void setFieldTime(String fieldTime) {
        this.fieldTime = fieldTime;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getSeatsName() {
        return seatsName;
    }

    public void setSeatsName(String seatsName) {
        this.seatsName = seatsName;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Long getOrderTimestamp() {
        return orderTimestamp;
    }

    public void setOrderTimestamp(Long orderTimestamp) {
        this.orderTimestamp = orderTimestamp;
    }
}
