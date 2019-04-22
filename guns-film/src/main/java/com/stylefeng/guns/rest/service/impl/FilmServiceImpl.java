package com.stylefeng.guns.rest.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.stylefeng.guns.rest.persistence.dao.MtimeFilmTMapper;
import com.stylefeng.guns.rest.persistence.model.MtimeFilmT;
import com.stylefeng.guns.rest.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Cyb
 * Date 2019/4/22/022  Time 18:09
 */
@Service(interfaceClass = FilmService.class)
@Component(value = "filmService")
public class FilmServiceImpl implements FilmService{

    @Autowired
    MtimeFilmTMapper mapper;

    @Override
    public MtimeFilmT selectById(int id) {
        MtimeFilmT mtimeFilmT = mapper.selectById(id);
        return mtimeFilmT;
    }
}
