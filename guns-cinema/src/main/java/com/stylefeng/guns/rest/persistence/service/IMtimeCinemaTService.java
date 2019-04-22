package com.stylefeng.guns.rest.persistence.service;

import com.stylefeng.guns.rest.persistence.Vo.CinemaVo;
import com.stylefeng.guns.rest.persistence.model.MtimeCinemaT;
import com.baomidou.mybatisplus.service.IService;

import java.util.ArrayList;
import java.util.Map;

/**
 * <p>
 * 影院信息表 服务类
 * </p>
 *
 * @author liangzhen
 * @since 2019-04-21
 */
public interface IMtimeCinemaTService extends IService<MtimeCinemaT> {

    //ArrayList<CinemaVo> selectCinemaByBrandId(String brandId, String districtId, String hallType);
    Map selectCinemaByBrandIdDistrictIdHallType(String brandId, String districtId, String hallType,int nowPage,int pageSize);
}
