package com.stylefeng.guns.rest.vo.film;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Cyb
 * Date 2019/4/23/023  Time 11:18
 */
public class FilmsVo implements Serializable{
    private int filmNum;
    private List<FilmInfoVo> filmInfo;
    private int nowPage;
    private int totalPage;
    public FilmsVo() {
    }

    public FilmsVo(int filmNum, List<FilmInfoVo> filmInfo, int nowPage, int totalPage) {
        this.filmNum = filmNum;
        this.filmInfo = filmInfo;
        this.nowPage = nowPage;
        this.totalPage = totalPage;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getFilmNum() {
        return filmNum;
    }

    public void setFilmNum(int filmNum) {
        this.filmNum = filmNum;
    }

    public List<FilmInfoVo> getFilmInfo() {
        return filmInfo;
    }

    public void setFilmInfo(List<FilmInfoVo> filmInfo) {
        this.filmInfo = filmInfo;
    }
}

