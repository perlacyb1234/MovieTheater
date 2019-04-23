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

    public InfoVo() {
    }

    public InfoVo(String biography, Actors actors) {
        this.biography = biography;
        this.actors = actors;
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
