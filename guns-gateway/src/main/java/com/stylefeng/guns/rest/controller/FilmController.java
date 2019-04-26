package com.stylefeng.guns.rest.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.api.FilmApi;
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

    @RequestMapping(value = "getIndex", method = RequestMethod.GET)
    public ResponseVo getIndex() {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setStatus(999);
        responseVo.setMsg("系统出现异常，请联系管理员");
        List<BannerVo> bannerVos = null;
        FilmsVo hotFilms = null;
        FilmsVo soonFilms = null;
        List<FilmInfoVo> boxRanking = null;
        List<FilmInfoVo> expectRanking = null;
        List<FilmInfoVo> top100 = null;
        try {
            bannerVos = filmApi.selectAllBanner();
            hotFilms = filmApi.selectHotFilms();
            soonFilms = filmApi.selectSoonFilms();
            boxRanking = filmApi.selectBoxRanking();
            expectRanking = filmApi.selectExpectRanking();
            top100 = filmApi.selectTop100();
        } catch (Exception e) {
            return responseVo;
        }
        if (bannerVos == null || bannerVos.size() == 0) {
            responseVo.setStatus(1);
            responseVo.setMsg("查询失败，无banner可加载");
            return responseVo;
        }
        /*if (hotFilms == null || hotFilms.getFilmNum() == 0) {
            responseVo.setStatus(1);
            responseVo.setMsg("查询失败，无hotFilms可加载");
            return responseVo;
        }
        if (soonFilms == null || soonFilms.getFilmNum() == 0) {
            responseVo.setStatus(1);
            responseVo.setMsg("查询失败，无soonFilms可加载");
            return responseVo;
        }
        if (boxRanking == null || boxRanking.size() == 0) {
            responseVo.setStatus(1);
            responseVo.setMsg("查询失败，无boxRanking可加载");
            return responseVo;
        }
        if (expectRanking == null || expectRanking.size() == 0) {
            responseVo.setStatus(1);
            responseVo.setMsg("查询失败，无expectRanking可加载");
            return responseVo;
        }
        if (top100 == null || top100.size() == 0) {
            responseVo.setStatus(1);
            responseVo.setMsg("查询失败，无top100可加载");
            return responseVo;
        }*/
        IndexVo indexVo = new IndexVo(bannerVos, hotFilms, soonFilms, boxRanking, expectRanking, top100);
        responseVo.setStatus(0);
        responseVo.setImgPre("https://wangdao-movietheater-project.oss-cn-beijing.aliyuncs.com/");
        responseVo.setData(indexVo);
        responseVo.setMsg("OK");
        return responseVo;
    }


    @RequestMapping(value = "getConditionList", method = RequestMethod.GET)
    public ResponseVo getCondition() {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setStatus(999);
        responseVo.setMsg("系统出现异常，请联系管理员");
        List<CatInfoVo> catInfoVos = null;
        List<SourceInfoVo> sourceInfoVos = null;
        List<YearInfoVo> yearInfoVos = null;
        try {
            catInfoVos = filmApi.selectAllCatInfo();
            sourceInfoVos = filmApi.selectAllSourceInfo();
            yearInfoVos = filmApi.selectAllYearInfo();
        } catch (Exception e) {
            return responseVo;
        }
        if (catInfoVos.size() == 0 ||sourceInfoVos.size() == 0 || yearInfoVos.size() == 0){
            responseVo.setStatus(1);
            responseVo.setMsg("查询失败，无条件可加载");
            return responseVo;
        }
        ConditionInfoVo conditionInfoVo = new ConditionInfoVo(catInfoVos, sourceInfoVos, yearInfoVos);
        responseVo.setStatus(0);
        responseVo.setData(conditionInfoVo);
        responseVo.setMsg("OK");
        return responseVo;
    }

    @RequestMapping(value = "getFilms", method = RequestMethod.GET)
    public ResponseVo getFilms(@RequestParam(value = "showType", defaultValue = "1") int showType,
                               @RequestParam(value = "sortId", defaultValue = "1") int sortId,
                               @RequestParam(value = "catId", defaultValue = "99") int catId,
                               @RequestParam(value = "sourceId", defaultValue = "99") int sourceId,
                               @RequestParam(value = "yearId", defaultValue = "99") int yearId,
                               @RequestParam(value = "nowPage", defaultValue = "1") int nowPage,
                               @RequestParam(value = "pageSize", defaultValue = "18") int pageSize) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setStatus(999);
        responseVo.setMsg("系统出现异常，请联系管理员");
        List<FilmInfoVo> filmInfoVos =
                null;
        int totalPage = 0;
        try {
            filmInfoVos = filmApi.searchOnCondition(showType, sortId, catId, sourceId, yearId, nowPage, pageSize);
            totalPage = filmInfoVos.size() / nowPage;
            if (filmInfoVos.size() == 0) {
                nowPage = 1;
                totalPage = 1;
            }
        } catch (Exception e) {
            return responseVo;
        }
        responseVo = new ResponseVo(0, "https://wangdao-movietheater-project.oss-cn-beijing.aliyuncs.com/", filmInfoVos,
                nowPage, totalPage);
        responseVo.setMsg("OK");
        return responseVo;
    }

    @RequestMapping("films/{searchValue}")
    public ResponseVo getFilmDetail(@PathVariable("searchValue") String searchValue, String searchType) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setStatus(999);
        responseVo.setMsg("系统出现异常，请联系管理员");
        FilmDetailVo filmDetail = null;
        try {
            filmDetail = filmApi.getFilmDetail(searchValue, searchType);
        } catch (Exception e) {
            return responseVo;
        }
        if (filmDetail == null){
            responseVo.setStatus(1);
            responseVo.setMsg("查询失败，无影片可加载");
            return responseVo;
        }
        responseVo.setStatus(0);
        //图片头
        responseVo.setImgPre("https://wangdao-movietheater-project.oss-cn-beijing.aliyuncs.com/");
        responseVo.setData(filmDetail);
        responseVo.setMsg("OK");
        return responseVo;
    }
}
