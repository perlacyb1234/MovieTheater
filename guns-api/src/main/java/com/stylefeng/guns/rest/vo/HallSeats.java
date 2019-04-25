package com.stylefeng.guns.rest.vo;

import java.util.List;

/**
 * Created by Cyb
 * Date 2019/4/24/024  Time 21:50
 */
//模拟影厅座位
public class HallSeats {
    private int limit;
    private String ids;
    private List<List<Seat>> single;
    private List<List<Seat>> couple;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }


    public List<List<Seat>> getSingle() {
        return single;
    }

    public void setSingle(List<List<Seat>> single) {
        this.single = single;
    }

    public List<List<Seat>> getCouple() {
        return couple;
    }

    public void setCouple(List<List<Seat>> couple) {
        this.couple = couple;
    }

}
