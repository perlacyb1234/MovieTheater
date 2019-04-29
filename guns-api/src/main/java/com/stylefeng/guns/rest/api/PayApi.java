package com.stylefeng.guns.rest.api;

import com.stylefeng.guns.rest.vo.order.OrderVo;
import com.stylefeng.guns.rest.vo.pay.PayInfoVo;

/**
 * Created by Cyb
 * Date 2019/4/26/026  Time 20:42
 */
public interface PayApi {
    PayInfoVo getPayInfo(OrderVo orderVo);
    int getPayResult(String orderId,int tryNums); //orderStatus状态为1为支付成功，其余均为支付失败
}
