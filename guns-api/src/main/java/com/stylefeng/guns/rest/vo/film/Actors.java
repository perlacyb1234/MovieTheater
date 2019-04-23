package com.stylefeng.guns.rest.vo.film;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Cyb
 * Date 2019/4/23/023  Time 18:50
 */
public class Actors implements Serializable {
    private Actor director;
    private List<Actor> actors;

    public Actors() {
    }

    public Actors(Actor director, List<Actor> actors) {
        this.director = director;
        this.actors = actors;
    }

    public Actor getDirector() {
        return director;
    }

    public void setDirector(Actor director) {
        this.director = director;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}
