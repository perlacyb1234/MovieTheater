package com.stylefeng.guns.rest.vo.film;

import java.io.Serializable;

/**
 * Created by Cyb
 * Date 2019/4/23/023  Time 18:45
 */
public class FilmDetailVo implements Serializable {
    private String filmName;//
    private String filmEnName;
    private String imgAddress;//
    private String score;//
    private String socreNum;
    private String totalBox;//
    private String info1;//film_cats查询，拼接字符串
    private String info2;//film_area,film_source/film_length(film_info_t)拼接字符串
    private String info3;//film_time film_area上映，拼接字符串
    private InfoVo info4;//film_info_t 包含剧情简介,导演，演员
    private Imgs imgs; //film_info_t
    private int filmId;

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getFilmEnName() {
        return filmEnName;
    }

    public void setFilmEnName(String filmEnName) {
        this.filmEnName = filmEnName;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSocreNum() {
        return socreNum;
    }

    public void setSocreNum(String socreNum) {
        this.socreNum = socreNum;
    }

    public String getTotalBox() {
        return totalBox;
    }

    public void setTotalBox(String totalBox) {
        this.totalBox = totalBox;
    }

    public String getInfo1() {
        return info1;
    }

    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    public String getInfo2() {
        return info2;
    }

    public void setInfo2(String info2) {
        this.info2 = info2;
    }

    public String getInfo3() {
        return info3;
    }

    public void setInfo3(String info3) {
        this.info3 = info3;
    }

    public InfoVo getInfo4() {
        return info4;
    }

    public void setInfo4(InfoVo info4) {
        this.info4 = info4;
    }

    public Imgs getImgs() {
        return imgs;
    }

    public void setImgs(Imgs imgs) {
        this.imgs = imgs;
    }
}
