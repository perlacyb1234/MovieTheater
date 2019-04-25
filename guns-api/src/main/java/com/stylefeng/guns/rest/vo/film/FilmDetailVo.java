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
    private String info01;//film_cats查询，拼接字符串
    private String info02;//film_area,film_source/film_length(film_info_t)拼接字符串
    private String info03;//film_time film_area上映，拼接字符串
    private InfoVo info04;//film_info_t 包含剧情简介,导演，演员
    private Imgs imgVO;//film_info_t
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

    public String getInfo01() {
        return info01;
    }

    public void setInfo01(String info01) {
        this.info01 = info01;
    }

    public String getInfo02() {
        return info02;
    }

    public void setInfo02(String info02) {
        this.info02 = info02;
    }

    public String getInfo03() {
        return info03;
    }

    public void setInfo03(String info03) {
        this.info03 = info03;
    }

    public InfoVo getInfo04() {
        return info04;
    }

    public void setInfo04(InfoVo info04) {
        this.info04 = info04;
    }

    public Imgs getImgVO() {
        return imgVO;
    }

    public void setImgVO(Imgs imgVO) {
        this.imgVO = imgVO;
    }
}
