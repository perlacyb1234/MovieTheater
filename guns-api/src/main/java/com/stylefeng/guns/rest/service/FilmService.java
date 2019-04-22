package com.stylefeng.guns.rest.service;

import com.stylefeng.guns.rest.persistence.model.MtimeFilmT;

/**
 * Created by Cyb
 * Date 2019/4/22/022  Time 18:08
 */
public interface FilmService {
    MtimeFilmT selectById(int id);
}
