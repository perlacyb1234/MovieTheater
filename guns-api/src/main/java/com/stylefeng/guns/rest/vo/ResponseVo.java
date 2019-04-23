package com.stylefeng.guns.rest.vo;

import com.stylefeng.guns.rest.vo.film.ConditionInfoVo;

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
    private int nowPage;
    private int totalPage;
    public ResponseVo() {
    }

    public ResponseVo(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResponseVo(int status, String imgPre, Object data, int nowPage, int totalPage) {
        this.status = status;
        this.imgPre = imgPre;
        this.data = data;
        this.nowPage = nowPage;
        this.totalPage = totalPage;
    }

    public ResponseVo(int status, String msg, String imgPre, Object data) {
        this.status = status;
        this.msg = msg;
        this.imgPre = imgPre;
        this.data = data;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public ResponseVo(int status, Object data) {
        this.status = status;
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
