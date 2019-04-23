package com.stylefeng.guns.rest.api;

import com.stylefeng.guns.rest.persistence.model.film.MtimeFilmT;
import com.stylefeng.guns.rest.vo.film.BannerVo;
import com.stylefeng.guns.rest.vo.film.FilmInfoVo;
import com.stylefeng.guns.rest.vo.film.FilmsVo;

import java.util.List;

/**
 * Created by Cyb
 * Date 2019/4/22/022  Time 18:08
 */
public interface FilmApi {
    List<BannerVo> selectAllBanner();
    FilmsVo selectHotFilms();
    FilmsVo selectSoonFilms();
    List<FilmInfoVo> selectBoxRanking();
    List<FilmInfoVo> selectExpectRanking();
    List<FilmInfoVo> selectTop100();
}
