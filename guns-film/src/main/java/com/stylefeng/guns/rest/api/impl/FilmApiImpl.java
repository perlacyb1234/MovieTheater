package com.stylefeng.guns.rest.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.api.FilmApi;
import com.stylefeng.guns.rest.persistence.dao.*;
import com.stylefeng.guns.rest.persistence.model.film.*;
import com.stylefeng.guns.rest.util.convert.DateConvert;
import com.stylefeng.guns.rest.vo.film.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Cyb
 * Date 2019/4/22/022  Time 18:09
 */
@Service(interfaceClass = FilmApi.class)
@Component(value = "filmService")
public class FilmApiImpl implements FilmApi {

    @Autowired
    MtimeFilmTMapper filmMapper;

    @Autowired
    MtimeBannerTMapper bannerMapper;

    @Autowired
    MtimeCatDictTMapper catMapper;

    @Autowired
    MtimeSourceDictTMapper sourceMapper;

    @Autowired
    MtimeYearDictTMapper yearMapper;

    @Override
    public List<CatInfoVo> selectAllCatInfo() {
        List<MtimeCatDictT> catDictTList = catMapper.selectList(new EntityWrapper<>());
        List<CatInfoVo> catInfoVos = new ArrayList<>();
        for (MtimeCatDictT catDictT : catDictTList) {
            if (catDictT.getShowName().equals("爱情")) {
                catInfoVos.add(new CatInfoVo(catDictT.getUuid(), catDictT.getShowName(), true));
            } else {
                catInfoVos.add(new CatInfoVo(catDictT.getUuid(), catDictT.getShowName(), false));
            }
        }
        return catInfoVos;
    }

    @Override
    public List<SourceInfoVo> selectAllSourceInfo() {
        List<MtimeSourceDictT> sourceList = sourceMapper.selectList(new EntityWrapper<>());
        List<SourceInfoVo> sourceInfoVos = new ArrayList<>();
        for (MtimeSourceDictT source : sourceList) {
            if (source.getShowName().equals("美国")) {
                sourceInfoVos.add(new SourceInfoVo(source.getUuid(), source.getShowName(), true));
            } else {
                sourceInfoVos.add(new SourceInfoVo(source.getUuid(), source.getShowName(), false));
            }
        }
        return sourceInfoVos;
    }

    @Override
    public List<YearInfoVo> selectAllYearInfo() {
        List<MtimeYearDictT> yearList = yearMapper.selectList(new EntityWrapper<>());
        List<YearInfoVo> yearInfoVos = new ArrayList<>();
        for (MtimeYearDictT year : yearList) {
            if (year.getShowName().equals("2018")) {
                yearInfoVos.add(new YearInfoVo(year.getUuid(), year.getShowName(), true));
            } else {
                yearInfoVos.add(new YearInfoVo(year.getUuid(), year.getShowName(), false));
            }
        }
        return yearInfoVos;
    }

    @Override
    public List<BannerVo> selectAllBanner() {
        List<MtimeBannerT> banners = bannerMapper.selectList(new EntityWrapper<>());
        List<BannerVo> bannerVos = new ArrayList<>();
        //使用Vo进行封装
        for (MtimeBannerT banner : banners) {
            bannerVos.add(new BannerVo(banner.getUuid(), banner.getBannerAddress(), banner.getBannerUrl()));
        }
        return bannerVos;
    }

    @Override
    public FilmsVo selectHotFilms() {
        EntityWrapper<MtimeFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(new MtimeFilmT());
        //     * 影片状态,1-正在热映，2-即将上映，3-经典影片
        entityWrapper.where("film_status=1");
        List<MtimeFilmT> hotFilms = filmMapper.selectList(entityWrapper);
        //使用Vo进行封装
        FilmsVo filmsVo = getFilmsVo(hotFilms);
        return filmsVo;
    }


    @Override
    public FilmsVo selectSoonFilms() {
        EntityWrapper<MtimeFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(new MtimeFilmT());
        //     * 影片状态,1-正在热映，2-即将上映，3-经典影片
        entityWrapper.where("film_status=2");
        List<MtimeFilmT> soonFilms = filmMapper.selectList(entityWrapper);
        FilmsVo filmsVo = getFilmsVo(soonFilms);
        return filmsVo;
    }

    @Override
    public List<FilmInfoVo> selectBoxRanking() {
        EntityWrapper<MtimeFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(new MtimeFilmT());
        // false根据票房降序
        entityWrapper.orderBy("film_box_office", false);
        List<MtimeFilmT> boxRanking = filmMapper.selectList(entityWrapper);
        List<FilmInfoVo> filmInfoVos = getFilmInfoVo(boxRanking);
        return filmInfoVos;
    }


    @Override
    public List<FilmInfoVo> selectExpectRanking() {
        EntityWrapper<MtimeFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(new MtimeFilmT());
        // false降序
        entityWrapper.orderBy("film_preSaleNum", false);
        List<MtimeFilmT> expectRanking = filmMapper.selectList(entityWrapper);
        List<FilmInfoVo> filmInfoVo = getFilmInfoVo(expectRanking);
        return filmInfoVo;
    }

    @Override
    public List<FilmInfoVo> selectTop100() {
        EntityWrapper<MtimeFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(new MtimeFilmT());
        // false降序
        entityWrapper.orderBy("film_score", false);
        List<MtimeFilmT> top100 = filmMapper.selectList(entityWrapper);
        List<FilmInfoVo> filmInfoVo = getFilmInfoVo(top100);
        return filmInfoVo;
    }

    @Override
    public List<FilmInfoVo> searchOnCondition(int showType, int sortId, int catId, int sourceId,
                                              int yearId, int nowPage, int pageSize) {
        EntityWrapper<MtimeFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(new MtimeFilmT());
        switch (sortId) {
            case 1:
                entityWrapper.orderBy("film_preSaleNum", false);
                break;
            case 2:
                entityWrapper.orderBy("film_time", false);
                break;
            case 3:
                entityWrapper.orderBy("film_score", false);
                break;
        }
        switch (yearId) {
            //全部
            case 99:
                break;
            //更早
            case 1:
                entityWrapper.where("film_time < {0}", "1970-1-1");
                break;
            //"70年代"
            case 2:
                entityWrapper.where("film_time > ={0}", "1970-1-1").andNew("film_time < {0}", "1980-1-1");
                break;
            //80年代
            case 3:
                entityWrapper.where("film_time >= {0}", "1980-1-1").andNew("film_time < {0}", "1990-1-1");
                break;
            //90年代
            case 4:
                entityWrapper.where("film_time >= {0}", "1990-1-1").andNew("film_time < {0}", "2000-1-1");
                break;
            //2000-2010
            case 5:
                entityWrapper.where("film_time >= {0}", "2000-1-1").andNew("film_time < {0}", "2010-1-1");
                break;
            //2011
            case 6:
                entityWrapper.where("film_time >= {0}", "2011-1-1").andNew("film_time < {0}", "2012-1-1");
                break;
            //2012
            case 7:
                entityWrapper.where("film_time >= {0}", "2012-1-1").andNew("film_time < {0}", "2013-1-1");
                break;
            //2013
            case 8:
                entityWrapper.where("film_time >= {0}", "2013-1-1").andNew("film_time < {0}", "2014-1-1");
                break;
            //2014
            case 9:
                entityWrapper.where("film_time >= {0}", "2014-1-1").andNew("film_time < {0}", "2015-1-1");
                break;
            //2015
            case 10:
                entityWrapper.where("film_time >= {0}", "2015-1-1").andNew("film_time < {0}", "2016-1-1");
                break;
            //2016
            case 11:
                entityWrapper.where("film_time >= {0}", "2016-1-1").andNew("film_time < {0}", "2017-1-1");
                break;
            //2017
            case 12:
                entityWrapper.where("film_time >= {0}", "2017-1-1").andNew("film_time < {0}", "2018-1-1");
                break;
            //2018
            case 13:
                entityWrapper.where("film_time >= {0}", "2018-1-1").andNew("film_time < {0}", "2019-1-1");
                break;
            //2018以后
            case 14:
                entityWrapper.where("film_time >= {0}", "2019-1-1");
                break;
        }
        //缺少年份查询
        entityWrapper.where("film_status = {0}", showType);
        //模糊查询，但如23不可以查询到2或者3
        if (catId != 99) {
            entityWrapper.like("film_cats", "#" + catId + "#", SqlLike.DEFAULT);
        }
        if (sourceId != 99) {
            entityWrapper.where("film_area = {0}", sourceId);
        }
        Page<FilmInfoVo> page = new Page<>(nowPage, pageSize);
        List<MtimeFilmT> films = filmMapper.selectPage(page, entityWrapper);
        List<FilmInfoVo> filmInfoVo = getFilmInfoVo(films);
        return filmInfoVo;
    }

    //使用Vo封装Po
    private List<FilmInfoVo> getFilmInfoVo(List<MtimeFilmT> films) {
        List<FilmInfoVo> filmInfoVos = new ArrayList<>();
        //日期格式转换
        DateConvert dateConvert = new DateConvert();
        for (MtimeFilmT film : films) {
            Date filmTime = film.getFilmTime();
            String showTime = dateConvert.convert(filmTime);
            filmInfoVos.add(new FilmInfoVo(film.getUuid(), film.getFilmType(), film.getImgAddress(),
                    film.getFilmName(), film.getFilmScore(), film.getFilmPresalenum(),
                    showTime, film.getFilmBoxOffice(), film.getFilmScore()));
        }
        return filmInfoVos;
    }

    //封装方法
    private FilmsVo getFilmsVo(List<MtimeFilmT> films) {
        List<FilmInfoVo> filmInfoVos = getFilmInfoVo(films);
        //暂不考虑page参数
        FilmsVo filmsVo = new FilmsVo(filmInfoVos.size(), filmInfoVos, 1, 1);
        return filmsVo;
    }
}
