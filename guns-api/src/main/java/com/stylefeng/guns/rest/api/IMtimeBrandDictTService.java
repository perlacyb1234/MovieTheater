package com.stylefeng.guns.rest.api;


import java.util.Map;

/**
 * <p>
 * 品牌信息表 服务类
 * </p>
 *
 * @author liangzhen
 * @since 2019-04-21
 */
public interface IMtimeBrandDictTService{

    Map selectBrandsByBrandIdHallTypeAreaId(String brandId, String hallType, String areaId);
}
