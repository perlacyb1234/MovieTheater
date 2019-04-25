package com.stylefeng.guns.rest.api.impl;

import com.alibaba.fastjson.JSON;
import com.stylefeng.guns.rest.vo.HallSeats;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Cyb
 * Date 2019/4/25/025  Time 0:12
 */
public class JsonTest {
    public static void main(String[] args) {
      /*  System.out.println("start");
        String s = "{  \"limit\": 5,  \"ids\":\"1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24\",  \"single\": [    [      {        \"seatId\": 1,        \"row\"   : 1,        \"column\": 1      },      {        \"seatId\": 2,        \"row\"   : 1,        \"column\": 2      },      {        \"seatId\": 3,        \"row\"   : 1,        \"column\": 3      },      {        \"seatId\": 4,        \"row\"   : 1,        \"column\": 4      },      {        \"seatId\": 5,        \"row\"   : 1,        \"column\": 5      },      {        \"seatId\": 6,        \"row\"   : 1,        \"column\": 6      }    ],    [      {        \"seatId\": 7,        \"row\"   : 2,        \"column\": 1      },      {        \"seatId\": 8,        \"row\"   : 2,        \"column\": 2      },      {        \"seatId\": 9,        \"row\"   : 2,        \"column\": 3      },      {        \"seatId\": 10,        \"row\"   : 2,        \"column\": 4      },      {        \"seatId\": 11,        \"row\"   : 2,        \"column\": 5      },      {        \"seatId\": 12,        \"row\"   : 2,        \"column\": 6      }    ]  ],  \"couple\": [    [      {        \"seatId\": 13,        \"row\"   : 3,        \"column\": 1      },      {        \"seatId\": 14,        \"row\"   : 3,        \"column\": 2      },      {        \"seatId\": 15,        \"row\"   : 3,        \"column\": 3      },      {        \"seatId\": 16,        \"row\"   : 3,        \"column\": 4      },      {        \"seatId\": 17,        \"row\"   : 3,        \"column\": 5      },      {        \"seatId\": 18,        \"row\"   : 3,        \"column\": 6      }    ],    [      {        \"seatId\": 19,        \"row\"   : 4,        \"column\": 1      },      {        \"seatId\": 20,        \"row\"   : 4,        \"column\": 2      },      {        \"seatId\": 21,        \"row\"   : 4,        \"column\": 3      },      {        \"seatId\": 22,        \"row\"   : 4,        \"column\": 4      },      {        \"seatId\": 23,        \"row\"   : 4,        \"column\": 5      },      {        \"seatId\": 24,        \"row\"   : 4,        \"column\": 6      }    ]  ]}";
        Object parse = JSON.parse(s);
        HallSeats hallSeats = JSON.parseObject(s, HallSeats.class);
        HallSeats hallSeat = (HallSeats) JSON.parse(s);
        System.out.println("end");
*/      String seatsName = "第一排1座,第一排2座,第一排3座,第二排1座";
        char[] seatsNameCharArray = seatsName.toCharArray();
        ///[\x{4e00}-\x{4e5d}]/u 一到九汉字的正则
        //\u4e00-\u9fa5
        //String rowRegex = "\\u4e00-\\u9fa5";
        //String rowRegex = "[\\u4e00-\\u9fa5]";
        String rowRegex = "[零一二三四五六七八九十]";
        //String rowRegex = "/[\\x{4e00}-\\x{4e5d}]/u";
        List<String> rows = new ArrayList<>();
        //数字的正则
        String columnRegex = "\\d";
        List<String> columns = new ArrayList<>();
        for (char c : seatsNameCharArray) {
            if (Pattern.matches(rowRegex, String.valueOf(c))){
                rows.add(String.valueOf(c));
            }
            if (Pattern.matches(columnRegex, String.valueOf(c))){
                columns.add(String.valueOf(c));
            }
        }
        System.out.println(rows);
        System.out.println(columns);
    }
}
