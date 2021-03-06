package com.stylefeng.guns.rest.persistence.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.persistence.model.MtimeHallFilmInfoT;

/**
 * <p>
 * 影厅电影信息表 Mapper 接口
 * </p>
 *
 * @author liangzhen
 * @since 2019-04-21
 */
public interface MtimeHallFilmInfoTMapper extends BaseMapper<MtimeHallFilmInfoT> {

    MtimeHallFilmInfoT selectFilmInfosByFilmId(Integer filmId);

}
