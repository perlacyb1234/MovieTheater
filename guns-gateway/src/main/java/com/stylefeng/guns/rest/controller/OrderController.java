package com.stylefeng.guns.rest.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.api.OrderApi;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.vo.ResponseVo;
import com.stylefeng.guns.rest.vo.order.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Cyb
 * Date 2019/4/24/024  Time 21:14
 */
@RestController
@RequestMapping("order")
public class OrderController {
    @Reference
    OrderApi orderApi;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "buyTickets", method = RequestMethod.POST)
    public ResponseVo buyTickets(@RequestParam int fieldId, @RequestParam int[] soldSeats,
                                 @RequestParam String seatsName, HttpServletRequest request) throws IOException {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setStatus(999);
        responseVo.setMsg("系统出现异常，请联系管理员");
        String username = null;
        boolean ret = false;
        boolean ret2 = false;
        try {
            String header = request.getHeader("Authorization");
            String authToken = header.substring(7);
            username = jwtTokenUtil.getUsernameFromToken(authToken);
            ret = orderApi.isTrueSeats(fieldId, soldSeats, seatsName);
            ret2 = orderApi.isSeatsOnSaling(soldSeats, fieldId);
        } catch (IOException e) {
            return responseVo;
        }
        if (!ret || !ret2) {
            responseVo.setStatus(1);
            responseVo.setMsg("该订单不存在");
            return responseVo;
        }
        OrderVo orderVo = orderApi.placeOrder(fieldId, soldSeats, seatsName, username);
        if (orderVo != null) {
            responseVo.setStatus(0);
            responseVo.setMsg("");
            responseVo.setData(orderVo);
            return responseVo;
        }
        return responseVo;
    }
}
