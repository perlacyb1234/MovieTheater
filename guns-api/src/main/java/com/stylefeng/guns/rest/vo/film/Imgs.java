package com.stylefeng.guns.rest.vo.film;

import java.io.Serializable;

/**
 * Created by Cyb
 * Date 2019/4/23/023  Time 19:13
 */
public class Imgs implements Serializable {
    private String mainImg; //影片详情下面的图集
    private String img01;
    private String img02;
    private String img03;
    private String img04;

    public Imgs() {
    }

    public Imgs(String mainImg, String img01, String img02, String img03, String img04) {
        this.mainImg = mainImg;
        this.img01 = img01;
        this.img02 = img02;
        this.img03 = img03;
        this.img04 = img04;
    }

    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

    public String getImg01() {
        return img01;
    }

    public void setImg01(String img01) {
        this.img01 = img01;
    }

    public String getImg02() {
        return img02;
    }

    public void setImg02(String img02) {
        this.img02 = img02;
    }

    public String getImg03() {
        return img03;
    }

    public void setImg03(String img03) {
        this.img03 = img03;
    }

    public String getImg04() {
        return img04;
    }

    public void setImg04(String img04) {
        this.img04 = img04;
    }
}
