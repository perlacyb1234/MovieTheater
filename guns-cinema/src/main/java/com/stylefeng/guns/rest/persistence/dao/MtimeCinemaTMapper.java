package com.stylefeng.guns.rest.persistence.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.persistence.model.MtimeCinemaT;
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

    ArrayList<MtimeCinemaT> selectCinemaByBrandIdAreaIdHallType(@Param("brandId") String brandId, @Param("areaId") String areaId,@Param("hallType") String hallType);

    MtimeCinemaT selectCinemaByCinemaId(String cinemaId);
}
