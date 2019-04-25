package com.stylefeng.guns.rest.api;

import com.stylefeng.guns.rest.vo.order.OrderVo;

import java.io.IOException;
import java.util.List;

/**
 * Created by Cyb
 * Date 2019/4/24/024  Time 21:05
 */
public interface OrderApi {

    boolean isTrueSeats(int fieldId, int[] soldSeats, String seatsName) throws IOException;
    boolean isSeatsOnSaling(int[] soldSeats, int fieldId);

    OrderVo placeOrder(int fieldId, int[] soldSeats, String seatsName, String username);

    List<OrderVo> getOrderInfoByUsername(String username, int nowPage, int pageSize);
}
