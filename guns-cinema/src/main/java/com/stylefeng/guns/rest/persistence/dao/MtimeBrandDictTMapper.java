package com.stylefeng.guns.rest.persistence.dao;

import com.stylefeng.guns.rest.persistence.model.MtimeBrandDictT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * <p>
 * 品牌信息表 Mapper 接口
 * </p>
 *
 * @author liangzhen
 * @since 2019-04-21
 */
public interface MtimeBrandDictTMapper extends BaseMapper<MtimeBrandDictT> {

    ArrayList<MtimeBrandDictT> selectBrandsByBrandId(@Param("brandId") String brandId);
}
