package com.stylefeng.guns.rest.api.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;

import java.io.File;

/**
 * Created by Cyb
 * Date 2019/4/26/026  Time 22:14
 */
public class Test {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Administrator\\Desktop\\新建文件夹\\qr-tradeprecreate4ab99c86fdea4056bc1a386e88c58f81.png");
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建
        String accessKeyId = "LTAIEmPzBcfbwWbO";
        String accessKeySecret = "PmUyY8HLQSy7Adrj8MQc3B94s3MGYd";
        String buketname=    "wangdao-movietheater-project";
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        PutObjectResult ltaiEmPzBcfbwWbO = client.putObject(buketname, "LTAIEmPzBcfbwWbO", file);

    }
}
