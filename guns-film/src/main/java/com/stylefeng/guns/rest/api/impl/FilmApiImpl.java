package com.stylefeng.guns.rest.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
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
            if (catDictT.getShowName().equals("爱情")){
                catInfoVos.add(new CatInfoVo(catDictT.getUuid(),catDictT.getShowName(),true));
            }
            else {
                catInfoVos.add(new CatInfoVo(catDictT.getUuid(),catDictT.getShowName(),false));
            }
        }
        return catInfoVos;
    }

    @Override
    public List<SourceInfoVo> selectAllSourceInfo() {
        List<MtimeSourceDictT> sourceList = sourceMapper.selectList(new EntityWrapper<>());
        List<SourceInfoVo> sourceInfoVos = new ArrayList<>();
        for (MtimeSourceDictT source : sourceList) {
            if (source.getShowName().equals("美国")){
                sourceInfoVos.add(new SourceInfoVo(source.getUuid(),source.getShowName(),true));
            }
            else {
                sourceInfoVos.add(new SourceInfoVo(source.getUuid(),source.getShowName(),false));
            }
        }
        return sourceInfoVos;
    }

    @Override
    public List<YearInfoVo> selectAllYearInfo() {
        List<MtimeYearDictT> yearList = yearMapper.selectList(new EntityWrapper<>());
        List<YearInfoVo> yearInfoVos = new ArrayList<>();
        for (MtimeYearDictT year : yearList) {
            if (year.getShowName().equals("2018")){
                yearInfoVos.add(new YearInfoVo(year.getUuid(),year.getShowName(),true));
            }
            else{
                yearInfoVos.add(new YearInfoVo(year.getUuid(),year.getShowName(),false));
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
            bannerVos.add(new BannerVo(banner.getUuid(),banner.getBannerAddress(),banner.getBannerUrl()));
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
        entityWrapper.orderBy("film_box_office",false);
        List<MtimeFilmT> boxRanking = filmMapper.selectList(entityWrapper);
        List<FilmInfoVo> filmInfoVos = getFilmInfoVo(boxRanking);
        return filmInfoVos;
    }


    @Override
    public List<FilmInfoVo> selectExpectRanking() {
        EntityWrapper<MtimeFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(new MtimeFilmT());
        // false降序
        entityWrapper.orderBy("film_preSaleNum",false);
        List<MtimeFilmT> expectRanking = filmMapper.selectList(entityWrapper);
        List<FilmInfoVo> filmInfoVo = getFilmInfoVo(expectRanking);
        return filmInfoVo;
    }

    @Override
    public List<FilmInfoVo> selectTop100() {
        EntityWrapper<MtimeFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(new MtimeFilmT());
        // false降序
        entityWrapper.orderBy("film_score",false);
        List<MtimeFilmT> top100 = filmMapper.selectList(entityWrapper);
        List<FilmInfoVo> filmInfoVo = getFilmInfoVo(top100);
        return filmInfoVo;
    }

    private List<FilmInfoVo> getFilmInfoVo(List<MtimeFilmT> films) {
        List<FilmInfoVo> filmInfoVos = new ArrayList<>();
        //日期格式转换
        DateConvert dateConvert = new DateConvert();
        for (MtimeFilmT film : films) {
            Date filmTime = film.getFilmTime();
            String showTime = dateConvert.convert(filmTime);
            filmInfoVos.add(new FilmInfoVo(film.getUuid(),film.getFilmType(),film.getImgAddress(),
                    film.getFilmName(),film.getFilmScore(),film.getFilmPresalenum(),
                    showTime,film.getFilmBoxOffice(),film.getFilmScore()));
        }
        return filmInfoVos;
    }
    //封装方法
    private FilmsVo getFilmsVo(List<MtimeFilmT> films) {
        List<FilmInfoVo> filmInfoVos = getFilmInfoVo(films);
        //暂不考虑page参数
        FilmsVo filmsVo = new FilmsVo(filmInfoVos.size(),filmInfoVos,1,1);
        return filmsVo;
    }
}
