package com.stylefeng.guns.rest.vo.film;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Cyb
 * Date 2019/4/23/023  Time 12:04
 */
public class IndexVo implements Serializable {
    private List<BannerVo> banners;
    private FilmsVo hotFilms;
    private FilmsVo soonFilms;
    private List<FilmInfoVo> boxRanking;
    private List<FilmInfoVo> expectRanking;
    private List<FilmInfoVo> top100;

    public IndexVo() {
    }

    public IndexVo(List<BannerVo> banners, FilmsVo hotFilms, FilmsVo soonFilms, List<FilmInfoVo> boxRanking, List<FilmInfoVo> expectRanking, List<FilmInfoVo> top100) {
        this.banners = banners;
        this.hotFilms = hotFilms;
        this.soonFilms = soonFilms;
        this.boxRanking = boxRanking;
        this.expectRanking = expectRanking;
        this.top100 = top100;
    }

    public List<BannerVo> getBanners() {
        return banners;
    }

    public void setBanners(List<BannerVo> banners) {
        this.banners = banners;
    }

    public FilmsVo getHotFilms() {
        return hotFilms;
    }

    public void setHotFilms(FilmsVo hotFilms) {
        this.hotFilms = hotFilms;
    }

    public FilmsVo getSoonFilms() {
        return soonFilms;
    }

    public void setSoonFilms(FilmsVo soonFilms) {
        this.soonFilms = soonFilms;
    }

    public List<FilmInfoVo> getBoxRanking() {
        return boxRanking;
    }

    public void setBoxRanking(List<FilmInfoVo> boxRanking) {
        this.boxRanking = boxRanking;
    }

    public List<FilmInfoVo> getExpectRanking() {
        return expectRanking;
    }

    public void setExpectRanking(List<FilmInfoVo> expectRanking) {
        this.expectRanking = expectRanking;
    }

    public List<FilmInfoVo> getTop100() {
        return top100;
    }

    public void setTop100(List<FilmInfoVo> top100) {
        this.top100 = top100;
    }
}
