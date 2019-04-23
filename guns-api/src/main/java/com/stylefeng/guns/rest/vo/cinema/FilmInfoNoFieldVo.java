package com.stylefeng.guns.rest.vo.cinema;

import java.io.Serializable;

/**
 * Create by John(LiangZhen)
 * Date 2019/4/24 Time 0:13
 */
public class FilmInfoNoFieldVo implements Serializable {

    int filmId;
    String filmName;
    String filmLength;
    String filmType;
    String filmCats;
    String imgAddress;

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

    public String getFilmLength() {
        return filmLength;
    }

    public void setFilmLength(String filmLength) {
        this.filmLength = filmLength;
    }

    public String getFilmType() {
        return filmType;
    }

    public void setFilmType(String filmType) {
        this.filmType = filmType;
    }

    public String getFilmCats() {
        return filmCats;
    }

    public void setFilmCats(String filmCats) {
        this.filmCats = filmCats;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    @Override
    public String toString() {
        return "FilmInfoNoFieldVo{" +
                "filmId=" + filmId +
                ", filmName='" + filmName + '\'' +
                ", filmLength='" + filmLength + '\'' +
                ", filmType='" + filmType + '\'' +
                ", filmCats='" + filmCats + '\'' +
                ", imgAddress='" + imgAddress + '\'' +
                '}';
    }
}
