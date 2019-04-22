package com.stylefeng.guns.rest.service;

import com.stylefeng.guns.rest.persistence.dao.MtimeFilmTMapper;
import com.stylefeng.guns.rest.persistence.model.MtimeFilmT;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Cyb
 * Date 2019/4/22/022  Time 18:08
 */
public interface FilmService {
    MtimeFilmT selectById(int id);
}
