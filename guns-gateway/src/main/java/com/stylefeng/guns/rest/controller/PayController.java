package com.stylefeng.guns.rest.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.api.OrderApi;
import com.stylefeng.guns.rest.api.PayApi;
import com.stylefeng.guns.rest.vo.ResponseVo;
import com.stylefeng.guns.rest.vo.order.OrderVo;
import com.stylefeng.guns.rest.vo.pay.PayInfoVo;
import com.stylefeng.guns.rest.vo.pay.PayResultVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Cyb
 * Date 2019/4/26/026  Time 21:32
 */
@RestController
public class PayController {

    @Reference
    OrderApi orderApi;

    @Reference
    PayApi payApi;

    @RequestMapping(value = "order/getPayInfo",method = RequestMethod.POST)
    public ResponseVo getPayInfo(@RequestParam String orderId){
        ResponseVo responseVo = new ResponseVo();
        responseVo.setStatus(999);
        responseVo.setMsg("系统出现异常，请联系管理员");
        PayInfoVo payInfo = null;
            OrderVo orderVo = orderApi.getOrderById(orderId);
            payInfo = payApi.getPayInfo(orderVo);
        if (payInfo == null){
            responseVo.setStatus(1);
            responseVo.setMsg("订单支付失败，请稍后重试");
            return responseVo;
        }
        responseVo.setStatus(0);
        responseVo.setData(payInfo);
        responseVo.setImgPre("https://wangdao-movietheater-project.oss-cn-beijing.aliyuncs.com/");
        responseVo.setMsg("");
        return responseVo;

    }
    @RequestMapping(value = "order/getPayResult",method = RequestMethod.POST)
    public ResponseVo getPayResult(@RequestParam String orderId,@RequestParam(defaultValue = "1")int tryNums){
        ResponseVo responseVo = new ResponseVo();
        responseVo.setStatus(1);
        responseVo.setMsg("订单支付失败，请稍后重试");
        int payResult = payApi.getPayResult(orderId, tryNums);
        if (payResult == 1) {
            PayResultVo payResultVo = new PayResultVo();
            payResultVo.setOrderId(orderId);
            payResultVo.setOrderStatus(1);
            payResultVo.setOrderMsg("支付成功");
            responseVo.setData(payResultVo);
            responseVo.setStatus(0);
            boolean ret = orderApi.setOrderStatusOK(orderId);
            return responseVo;
        }
        return responseVo;
    }
}

