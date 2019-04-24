package com.stylefeng.guns.rest.vo.user;

/**
 * Created by Cyb
 * Date 2019/4/24/024  Time 11:47
 */
public class AuthVo {
    private String randomKey;
    private String token;

    public AuthVo() {
    }

    public AuthVo(String randomKey, String token) {
        this.randomKey = randomKey;
        this.token = token;
    }

    public String getRandomKey() {
        return randomKey;
    }

    public void setRandomKey(String randomKey) {
        this.randomKey = randomKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
