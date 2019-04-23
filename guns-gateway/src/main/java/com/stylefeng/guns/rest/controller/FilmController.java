package com.stylefeng.guns.rest.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.api.FilmApi;
import com.stylefeng.guns.rest.persistence.model.film.MtimeBannerT;
import com.stylefeng.guns.rest.persistence.model.film.MtimeFilmT;
import com.stylefeng.guns.rest.vo.ResponseVo;
import com.stylefeng.guns.rest.vo.film.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Cyb
 * Date 2019/4/22/022  Time 18:25
 */
@RestController
@RequestMapping("/film")
public class FilmController {
    @Reference(interfaceClass = FilmApi.class)
    FilmApi filmApi;

    @RequestMapping(value = "getIndex",method = RequestMethod.GET)
    public ResponseVo getIndex(){
        List<BannerVo> bannerVos = filmApi.selectAllBanner();
        FilmsVo hotFilms = filmApi.selectHotFilms();
        FilmsVo soonFilms = filmApi.selectSoonFilms();
        List<FilmInfoVo> boxRanking = filmApi.selectBoxRanking();
        List<FilmInfoVo> expectRanking = filmApi.selectExpectRanking();
        List<FilmInfoVo> top100 = filmApi.selectTop100();
        IndexVo indexVo = new IndexVo(bannerVos,hotFilms,soonFilms,boxRanking,expectRanking,top100);
        ResponseVo responseVo = new ResponseVo();
        responseVo.setStatus(0);
        responseVo.setImgPre("http://img.meetingshop.cn/");
        responseVo.setData(indexVo);
        return responseVo;
    }


    @RequestMapping(value = "getConditionList",method = RequestMethod.GET)
    public ResponseVo getCondition(){
        List<CatInfoVo> catInfoVos = filmApi.selectAllCatInfo();
        List<SourceInfoVo> sourceInfoVos = filmApi.selectAllSourceInfo();
        List<YearInfoVo> yearInfoVos = filmApi.selectAllYearInfo();
        ConditionInfoVo conditionInfoVo = new ConditionInfoVo(catInfoVos, sourceInfoVos, yearInfoVos);
        ResponseVo responseVo = new ResponseVo(0,conditionInfoVo);
        return responseVo;
    }


}
