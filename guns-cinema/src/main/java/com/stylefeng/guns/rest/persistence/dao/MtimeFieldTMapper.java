package com.stylefeng.guns.rest.persistence.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.persistence.model.MtimeFieldT;

import java.util.ArrayList;

/**
 * <p>
 * 放映场次表 Mapper 接口
 * </p>
 *
 * @author liangzhen
 * @since 2019-04-21
 */
public interface MtimeFieldTMapper extends BaseMapper<MtimeFieldT> {

    ArrayList<MtimeFieldT> selectFilmFieldsByCinemaId(String cinemaId);

    MtimeFieldT selectFilmFieldsByFieldId(String fieldId);
}
