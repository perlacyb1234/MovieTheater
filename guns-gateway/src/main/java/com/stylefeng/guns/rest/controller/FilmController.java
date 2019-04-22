package com.stylefeng.guns.rest.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.persistence.model.MtimeFilmT;
import com.stylefeng.guns.rest.service.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Cyb
 * Date 2019/4/22/022  Time 18:25
 */
@Controller
@RequestMapping("/film")
public class FilmController {
    @Reference(interfaceClass = FilmService.class)
    FilmService filmService;
    @RequestMapping
    @ResponseBody
    public MtimeFilmT getFilmById(int id){
        MtimeFilmT mtimeFilmT = filmService.selectById(id);
        return mtimeFilmT;
    }
}
