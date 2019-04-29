package com.stylefeng.guns.rest.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alipay.demo.trade.Main;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.stylefeng.guns.rest.api.PayApi;
import com.stylefeng.guns.rest.persistence.dao.MtimeOrderTMapper;
import com.stylefeng.guns.rest.vo.order.OrderVo;
import com.stylefeng.guns.rest.vo.pay.CMIV;
import com.stylefeng.guns.rest.vo.pay.PayInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by Cyb
 * Date 2019/4/26/026  Time 20:48
 */
@Component
@Service(interfaceClass = PayApi.class)
public class PayApiImpl implements PayApi {
    @Override
    public PayInfoVo getPayInfo(OrderVo orderVo) {

        Main main = new Main();

        String qrCodeAddress = main.test_trade_precreate(orderVo);
   /*     switch (qrCodeAddress){
            case "支付宝预下单失败!!!":
                return null;
            case "系统异常，预下单状态未知!!!":
                return null;
            case "不支持的交易状态，交易返回异常!!!":
                return null;
        }*/
        PayInfoVo payInfoVo = new PayInfoVo();
        payInfoVo.setOrderId(orderVo.getOrderId());
        payInfoVo.setCinemaInfo(new CMIV(orderVo.getOrderPrice()));
        File file = new File(qrCodeAddress);
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建
        String accessKeyId = "LTAIEmPzBcfbwWbO";
        String accessKeySecret = "PmUyY8HLQSy7Adrj8MQc3B94s3MGYd";
        String buketname=    "wangdao-movietheater-project";
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        client.putObject(buketname,file.getName(),file);
        payInfoVo.setQRCodeAddress(file.getName());
        return payInfoVo;
    }

    @Override
    public int getPayResult(String orderId, int tryNums) {

        Main main = new Main();
        int result = main.test_trade_query(orderId);
        return result;
    }

}
