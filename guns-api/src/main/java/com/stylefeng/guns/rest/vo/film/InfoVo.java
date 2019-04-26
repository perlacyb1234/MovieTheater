package com.stylefeng.guns.rest.vo.film;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Cyb
 * Date 2019/4/23/023  Time 18:48
 */
public class InfoVo implements Serializable {
    private String biography;
    private Actors actors;
    private Imgs imgVO;//film_info_t
    private int filmId;

    public InfoVo() {
    }

    public InfoVo(String biography, Actors actors, Imgs imgVO, int filmId) {
        this.biography = biography;
        this.actors = actors;
        this.imgVO = imgVO;
        this.filmId = filmId;
    }

    public Imgs getImgVO() {
        return imgVO;
    }

    public void setImgVO(Imgs imgVO) {
        this.imgVO = imgVO;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Actors getActors() {
        return actors;
    }

    public void setActors(Actors actors) {
        this.actors = actors;
    }
}
