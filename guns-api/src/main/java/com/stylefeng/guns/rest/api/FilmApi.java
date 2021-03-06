package com.stylefeng.guns.rest.api;

import com.stylefeng.guns.rest.vo.film.*;

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
    List<CatInfoVo> selectAllCatInfo();
    List<SourceInfoVo> selectAllSourceInfo();
    List<YearInfoVo> selectAllYearInfo();
    List<FilmInfoVo> searchOnCondition(int showType,int sortId,int catId,int sourceId,
                                       int yearId,int nowPage,int pageSize);
    FilmDetailVo getFilmDetail(String searchValue,String searchType);
}
