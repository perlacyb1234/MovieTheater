package com.stylefeng.guns.rest.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.rest.api.OrderApi;
import com.stylefeng.guns.rest.persistence.dao.*;
import com.stylefeng.guns.rest.persistence.model.*;
import com.stylefeng.guns.rest.vo.HallSeats;
import com.stylefeng.guns.rest.vo.Seat;
import com.stylefeng.guns.rest.vo.order.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by Cyb
 * Date 2019/4/24/024  Time 21:06
 */
@Service(interfaceClass = OrderApi.class)
@Component
public class OrderApiImpl implements OrderApi {
    @Autowired
    MtimeOrderTMapper orderMapper;

    @Autowired
    MtimeFieldTMapper fieldMapper;

    @Autowired
    MtimeHallDictTMapper hallDictMapper;

    @Autowired
    MtimeUserTMapper userMapper;

    @Autowired
    MtimeFilmTMapper filmMapper;

    @Autowired
    MtimeCinemaTMapper cinemaMapper;
    @Override
    public OrderVo placeOrder(int fieldId, int[] soldSeats, String seatsName, String username) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        MtimeFieldT fieldT = fieldMapper.selectById(fieldId);
        Integer cinemaId = fieldT.getCinemaId();
        Integer filmId = fieldT.getFilmId();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <soldSeats.length ; i++) {
            if (i != soldSeats.length - 1){
                sb.append(soldSeats[i]).append(",");
            }
            else {
                sb.append(soldSeats[i]);
            }
        }
        Integer filmPrice = fieldT.getPrice();
        int orderPrice = filmPrice * soldSeats.length;
        Date orderTime = new Date(System.currentTimeMillis());
        MtimeUserT userT = new MtimeUserT();
        userT.setUserName(username);
        MtimeUserT userT1 = userMapper.selectOne(userT);
        //封装Po
        MtimeOrderT orderT = new MtimeOrderT();
        orderT.setUuid(uuid);
        orderT.setCinemaId(cinemaId);
        orderT.setFieldId(fieldId);
        orderT.setFilmId(filmId);
        orderT.setSeatsIds(sb.toString());
        orderT.setSeatsName(seatsName);
        orderT.setFilmPrice(Double.valueOf(filmPrice));
        orderT.setOrderPrice(Double.valueOf(orderPrice));
        orderT.setOrderTime(orderTime);
        orderT.setOrderUser(userT1.getUuid());
        orderT.setOrderStatus(0);
        int insert = orderMapper.insert(orderT);
        if (insert == 1) {
            //封装Vo
            MtimeFilmT filmT = filmMapper.selectById(filmId);
            //电影场次似乎暂时没有日期date
            String beginTime = fieldT.getBeginTime();
            StringBuffer fieldTime = new StringBuffer();
            fieldTime.append(beginTime);
            MtimeCinemaT cinemaT = cinemaMapper.selectById(fieldT.getCinemaId());
            long orderTimestamp = orderTime.getTime();
            OrderVo orderVo = new OrderVo(uuid,filmT.getFilmName(),fieldTime.toString(),
                    cinemaT.getCinemaName(),seatsName,String.valueOf(orderPrice),orderTimestamp);
            return orderVo;
        }
        return null;
    }

    @Override
    public boolean isTrueSeats(int fieldId, int[] soldSeats, String seatsName) throws IOException {
        MtimeFieldT fieldT = fieldMapper.selectById(fieldId);
        MtimeHallDictT hallDictT = new MtimeHallDictT();
        hallDictT.setShowName(fieldT.getHallName());
        MtimeHallDictT hallDictT1 = hallDictMapper.selectOne(hallDictT);
        String seatAddress = hallDictT1.getSeatAddress();
        //读取座位信息json，然后判断座位是否在影厅中，暂时使用本地磁盘的文件读取座位信息
        FileReader fileReader = new FileReader("D:\\座位信息json\\" + seatAddress);
        BufferedReader reader = new BufferedReader(fileReader);
        String s = null;
        StringBuilder sb = new StringBuilder();
        while ((s = reader.readLine()) != null) {
            sb = sb.append(s);
        }
        System.out.println(sb);
        //不能用强制类型转换，应使用
        //HallSeats hallSeats = (HallSeats) JSON.parse(sb.toString());
        HallSeats hallSeats = JSON.parseObject(sb.toString(), HallSeats.class);
        //获得ids的int数组
        String hallSeatIds = hallSeats.getIds();
        String[] split = hallSeatIds.split(",");
        List<String> ids = Arrays.asList(split);
        //判断soldSeat中的每个座位，是否对应
        for (int soldSeat : soldSeats) {
            if (!ids.contains(String.valueOf(soldSeat))) {
                //不在ids中
                return false;
            }
        }
        char[] seatsNameCharArray = seatsName.toCharArray();
        ///[\x{4e00}-\x{4e5d}]/u 一到九汉字的正则
        //String rowRegex = "[\\u4e00-\\u4e5d]";
        String rowRegex = "[零一二三四五六七八九十]";
        List<String> rows = new ArrayList<>();
        //数字的正则，暂时不能解决两位以上的数字
        String columnRegex = "\\d";
        List<String> columns = new ArrayList<>();
        for (char c : seatsNameCharArray) {
            if (Pattern.matches(rowRegex, String.valueOf(c))) {
                rows.add(String.valueOf(c));
            }
            if (Pattern.matches(columnRegex, String.valueOf(c))) {
                columns.add(String.valueOf(c));
            }
        }
        //中文数字一到九转化阿拉伯数字1-9
        List<Integer> rowNums = new ArrayList<>();
        for (String row : rows) {
            switch (row) {
                case "一":
                    rowNums.add(1);
                    break;
                case "二":
                    rowNums.add(2);
                    break;
                case "三":
                    rowNums.add(3);
                    break;
                case "四":
                    rowNums.add(4);
                    break;
                case "五":
                    rowNums.add(5);
                    break;
                case "六":
                    rowNums.add(6);
                    break;
                case "七":
                    rowNums.add(7);
                    break;
                case "八":
                    rowNums.add(8);
                    break;
                case "九":
                    rowNums.add(9);
                    break;
            }
        }
        //通过id和座位表获得座位信息
        List<List<Seat>> single = hallSeats.getSingle();
        List<Seat> seatList = new ArrayList<>();
        //单人座
        for (int soldSeat : soldSeats) {
            for (List<Seat> seats : single) {
                for (Seat seat : seats) {
                    //匹配id，获得行列
                    if (seat.getSeatId() == soldSeat) {
                        seatList.add(seat);
                    }
                }
            }
        }
        List<List<Seat>> couple = hallSeats.getCouple();
        for (int soldSeat : soldSeats) {
            for (List<Seat> seats : couple) {
                for (Seat seat : seats) {
                    //匹配id，获得行列
                    if (seat.getSeatId() == soldSeat) {
                        seatList.add(seat);
                    }
                }
            }
        }
        int i = 0;
        for (Seat seat : seatList) {
            int column = seat.getColumn();
            int row = seat.getRow();
            if (row != rowNums.get(i) || column != Integer.parseInt(columns.get(i))) {
                return false;
            }
            i++;
        }
        return true;
    }

    @Override
    public boolean isSeatsOnSaling(int[] soldSeats, int fieldId) {
        EntityWrapper<MtimeOrderT> entityWrapper = new EntityWrapper<>();
        entityWrapper.where("field_id = {0}", fieldId);
        List<MtimeOrderT> ordersByFieldId = orderMapper.selectList(entityWrapper);
        for (int soldSeat : soldSeats) {
            for (MtimeOrderT orderT : ordersByFieldId) {
                String seatsIds = orderT.getSeatsIds();
                String[] split = seatsIds.split(",");
                for (String seatId : split) {
                    if (seatId.equals(String.valueOf(soldSeat))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
