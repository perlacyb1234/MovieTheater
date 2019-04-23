package com.stylefeng.guns.rest.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.api.FilmApi;
import com.stylefeng.guns.rest.persistence.model.film.MtimeBannerT;
import com.stylefeng.guns.rest.persistence.model.film.MtimeFilmT;
import com.stylefeng.guns.rest.vo.ResponseVo;
import com.stylefeng.guns.rest.vo.film.*;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "getFilms",method = RequestMethod.GET)
    public ResponseVo getFilms(@RequestParam(value = "showType",defaultValue = "1") int showType,
                               @RequestParam(value = "sortId",defaultValue = "1")int sortId,
                               @RequestParam(value = "catId",defaultValue = "99")int catId,
                               @RequestParam(value = "sourceId",defaultValue = "99")int sourceId,
                               @RequestParam(value = "yearId",defaultValue = "99")int yearId,
                               @RequestParam(value = "nowPage",defaultValue = "1")int nowPage,
                               @RequestParam(value = "pageSize",defaultValue = "18")int pageSize){
        List<FilmInfoVo> filmInfoVos =
                filmApi.searchOnCondition(showType, sortId, catId, sourceId, yearId, nowPage, pageSize);
        int totalPage = filmInfoVos.size()/nowPage;
        if (filmInfoVos.size() == 0){
            nowPage = 1;
            totalPage = 1;
        }
        ResponseVo responseVo = new ResponseVo(0,"http://img.meetingshop.cn/",filmInfoVos,
                nowPage,totalPage);
        return responseVo;
    }

    @RequestMapping("films/{searchValue}")
    public ResponseVo getFilmDetail(@PathVariable("searchValue")String searchValue,String searchType){
        FilmDetailVo filmDetail = filmApi.getFilmDetail(searchValue, searchType);
        ResponseVo responseVo = new ResponseVo(0, "http://img.meetingshop.cn/", filmDetail);
        return responseVo;
    }
}
