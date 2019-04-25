package com.stylefeng.guns.rest.vo.cinema;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Create by John(LiangZhen)
 * Date 2019/4/23 Time 16:42
 */
public class FilmInfoVo implements Serializable {

    int filmId;
    String filmName;
    String filmLength;
    String filmType;
    String filmCats;
    String actors;
    String imgAddress;

    ArrayList<FilmFieldVo> filmFields;

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

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    public ArrayList<FilmFieldVo> getFilmFields() {
        return filmFields;
    }

    public void setFilmFields(ArrayList<FilmFieldVo> filmFields) {
        this.filmFields = filmFields;
    }

}
