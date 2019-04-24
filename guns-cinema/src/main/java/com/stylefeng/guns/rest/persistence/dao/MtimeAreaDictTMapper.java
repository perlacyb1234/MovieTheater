package com.stylefeng.guns.rest.persistence.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.persistence.model.MtimeAreaDictT;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * <p>
 * 地域信息表 Mapper 接口
 * </p>
 *
 * @author liangzhen
 * @since 2019-04-21
 */
public interface MtimeAreaDictTMapper extends BaseMapper<MtimeAreaDictT> {

    ArrayList<MtimeAreaDictT> selectAreasByAreaId(@Param("areaId") String areaId);
}
