package com.stylefeng.guns.rest.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.api.IMtimeBrandDictTService;
import com.stylefeng.guns.rest.api.IMtimeCinemaTService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "getCinemas",method = RequestMethod.GET)
    @ResponseBody
    public Map getCinemas(@RequestParam(name = "brandId",defaultValue = "99") String brandId,
                          @RequestParam(name = "districtId",defaultValue = "99") String districtId,
                          @RequestParam(name = "hallType",defaultValue = "99") String hallType,
                          @RequestParam(name = "nowPage",defaultValue = "1") String nowPage,
                          @RequestParam(name = "pageSize",defaultValue = "12") String pageSize){

        Map<String, Object> map = new HashMap<>();

        int now_page = Integer.parseInt(nowPage);
        int page_size = Integer.parseInt(pageSize);

        map = mtimeCinemaTService.selectCinemaByBrandIdDistrictIdHallType(brandId,districtId,hallType,now_page,page_size);

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
        HashMap<String, Object> map = new HashMap<>();

        if(cinemaId == null || cinemaId == ""){
            map.put("status",999);
            map.put("msg","系统出现异常，请联系管理员");
            return map;
        }
        Map data = mtimeCinemaTService.selectFieldsById(cinemaId);

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


    @RequestMapping("getFieldInfo")
    @ResponseBody
    public Map getFieldInfo(String cinemaId,String fieldId){

        HashMap<String, Object> map = new HashMap<>();

        if(cinemaId == null || cinemaId == "" || fieldId == null || fieldId == ""){
            map.put("status",999);
            map.put("msg","系统出现异常，请联系管理员");
            return map;
        }

        Map data = mtimeCinemaTService.selectFieldByCinemaIdAndFieldId(cinemaId,fieldId);

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

