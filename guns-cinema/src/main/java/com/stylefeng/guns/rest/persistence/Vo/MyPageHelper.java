package com.stylefeng.guns.rest.persistence.Vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by John(LiangZhen)
 * Date 2019/4/21 Time 20:02
 */
public class MyPageHelper {

    public static<T> List<T> subList(int nowPage, int PageSize, ArrayList<T> allList){
        /*Integer pageNum = Integer.valueOf(nowPage);
        Integer rowsNum = Integer.valueOf(PageSize);*/
        //start=(page-1)rows,end=page*rows-1
        int start = (nowPage - 1) * PageSize;
        int end = nowPage * PageSize;
        List<T> list1;
        //如果list长度不够则截取到size即可
        if (allList.size()>=end){
            list1 = allList.subList(start,end);
        }else {
            list1 = allList.subList(start,allList.size());
        }

        return list1;
    }

}
