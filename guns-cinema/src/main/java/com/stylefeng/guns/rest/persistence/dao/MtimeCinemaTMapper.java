package com.stylefeng.guns.rest.persistence.dao;

import com.stylefeng.guns.rest.persistence.model.MtimeCinemaT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * <p>
 * 影院信息表 Mapper 接口
 * </p>
 *
 * @author liangzhen
 * @since 2019-04-21
 */
public interface MtimeCinemaTMapper extends BaseMapper<MtimeCinemaT> {

    ArrayList<MtimeCinemaT> selectCinemaByBrandId(@Param("brandId") String brandId, @Param("districtId") String districtId);
}