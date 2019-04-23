package com.stylefeng.guns.rest.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.api.IMtimeBrandDictTService;
import com.stylefeng.guns.rest.api.IMtimeCinemaTService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 影院信息表 前端控制器
 * </p>
 *
 * @author liangzhen
 * @since 2019-04-21
 */
@Controller
@RequestMapping("/cinema")
public class MtimeCinemaTController {

    @Reference
    IMtimeCinemaTService mtimeCinemaTService;
    @Reference
    IMtimeBrandDictTService mtimeBrandDictTService;

    @RequestMapping("getCinemas")
    @ResponseBody
    public Map getCinemas(String brandId, String districtId, String hallType, String nowPage, String pageSize){

        Map<String, Object> map = new HashMap<>();

        if(brandId == null || brandId == ""){
            brandId = "99";
        }
        if(districtId == null || districtId == ""){
            districtId = "99";
        }
        if(hallType == null || hallType == ""){
            hallType = "99";
        }

        int now_page = 0;
        int page_size = 0;
        if(nowPage == null || nowPage == ""){
            now_page = 1;
        }else{
            now_page = Integer.parseInt(nowPage);
        }
        if(pageSize == null || pageSize == ""){
            page_size = 12;
        }else {
            page_size = Integer.parseInt(pageSize);
        }

        map = mtimeCinemaTService.selectCinemaByBrandIdDistrictIdHallType(brandId,districtId,hallType,now_page,page_size);

       // ArrayList cinemaList = (ArrayList) cinemas.get("cinemas");

        if(map == null || map.isEmpty()){

            map.put("status",1);
            map.put("msg","影院信息查询失败");

        }else{

            map.put("status",0);

        }

        return map;

    }

    @RequestMapping("getCondition")
    @ResponseBody
    public Map getCondition(String brandId, String hallType, String areaId){

        if(brandId == null || brandId == ""){
            brandId = "99";
        }
        if(hallType == null || hallType == ""){
            hallType = "99";
        }
        if(areaId == null || areaId == ""){
            areaId = "99";
        }

        HashMap<String, Object> map = new HashMap<>();

        Map data = mtimeBrandDictTService.selectBrandsByBrandIdHallTypeAreaId(brandId,hallType,areaId);

        if(data.size() == 0){
            map.put("status",1);
            map.put("msg","影院信息查询失败");
        }else{
            map.put("status",0);
            map.put("data",data);
        }

        return map;

    }


    @RequestMapping("getFields")
    @ResponseBody
    public Map getFields(String cinemaId){

        Map data = mtimeCinemaTService.selectFieldsById(cinemaId);

        HashMap<String, Object> map = new HashMap<>();

        if(!data.isEmpty()){
            map.put("status",0);
            map.put("imgPre","http://img.meetingshop.cn/");
            map.put("data",data);
        }else{
            map.put("status",1);
            map.put("msg","影院信息查询失败");
        }

        return map;

    }

}

