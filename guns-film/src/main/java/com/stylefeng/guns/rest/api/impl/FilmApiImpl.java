package com.stylefeng.guns.rest.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.rest.api.FilmApi;
import com.stylefeng.guns.rest.persistence.dao.MtimeBannerTMapper;
import com.stylefeng.guns.rest.persistence.dao.MtimeFilmTMapper;
import com.stylefeng.guns.rest.persistence.model.film.MtimeBannerT;
import com.stylefeng.guns.rest.persistence.model.film.MtimeFilmT;
import com.stylefeng.guns.rest.util.convert.DateConvert;
import com.stylefeng.guns.rest.vo.film.BannerVo;
import com.stylefeng.guns.rest.vo.film.FilmInfoVo;
import com.stylefeng.guns.rest.vo.film.FilmsVo;
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
