package com.stylefeng.guns.rest.vo;

/**
 * Created by Cyb
 * Date 2019/4/23/023  Time 11:14
 */
//公用响应报文vo
public class ResponseVo {
    private int status;
    private String msg;
    private String imgPre;
    private Object data;
    public ResponseVo() {
    }

    public ResponseVo(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResponseVo(int status, String msg, String imgPre, Object data) {
        this.status = status;
        this.msg = msg;
        this.imgPre = imgPre;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getImgPre() {
        return imgPre;
    }

    public void setImgPre(String imgPre) {
        this.imgPre = imgPre;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
