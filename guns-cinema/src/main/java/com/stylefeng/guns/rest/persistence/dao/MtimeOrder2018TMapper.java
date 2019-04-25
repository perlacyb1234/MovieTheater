package com.stylefeng.guns.rest.persistence.dao;

import com.stylefeng.guns.rest.persistence.model.MtimeOrder2018T;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 订单信息表 Mapper 接口
 * </p>
 *
 * @author liangzhen
 * @since 2019-04-25
 */
public interface MtimeOrder2018TMapper extends BaseMapper<MtimeOrder2018T> {

    MtimeOrder2018T selectMtimeOrder2018TByFieldId(String fieldId);
}
