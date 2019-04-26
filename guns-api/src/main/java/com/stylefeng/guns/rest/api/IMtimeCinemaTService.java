package com.stylefeng.guns.rest.api;

import java.util.Map;

/**
 * <p>
 * 影院信息表 服务类
 * </p>
 *
 * @author liangzhen
 * @since 2019-04-21
 */
public interface IMtimeCinemaTService{

    Map selectCinemaByBrandIdAreaIdHallType(String brandId, String areaId, String hallType, int nowPage, int pageSize);

    Map selectFieldsById(String cinemaId);

    Map selectFieldByCinemaIdAndFieldId(String cinemaId, String fieldId);
}
