package com.stylefeng.guns.rest.api;


import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.rest.persistence.model.cinema.MtimeCinemaT;

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

    Map selectCinemaByBrandIdDistrictIdHallType(String brandId, String districtId, String hallType, int nowPage, int pageSize);

}
