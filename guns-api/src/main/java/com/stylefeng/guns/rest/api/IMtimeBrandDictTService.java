package com.stylefeng.guns.rest.api;


import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.rest.persistence.model.cinema.MtimeBrandDictT;

import java.util.Map;

/**
 * <p>
 * 品牌信息表 服务类
 * </p>
 *
 * @author liangzhen
 * @since 2019-04-21
 */
public interface IMtimeBrandDictTService extends IService<MtimeBrandDictT> {

    Map selectBrandsByBrandIdHallTypeAreaId(String brandId, String hallType, String areaId);
}
