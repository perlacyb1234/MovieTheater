package com.stylefeng.guns.rest.vo;

/**
 * Created by Cyb
 * Date 2019/4/24/024  Time 21:52
 */
//模拟影厅座位模型
public class Seat {
    private int seatId;
    private int row;
    private int column;

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatId=" + seatId +
                ", row=" + row +
                ", column=" + column +
                '}';
    }
}
