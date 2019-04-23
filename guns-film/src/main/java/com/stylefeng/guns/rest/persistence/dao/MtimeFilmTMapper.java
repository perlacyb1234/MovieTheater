package com.stylefeng.guns.rest.persistence.dao;

import com.stylefeng.guns.rest.persistence.model.film.MtimeFilmT;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 影片主表 Mapper 接口
 * </p>
 *
 * @author Cyb
 * @since 2019-04-22
 */
public interface MtimeFilmTMapper extends BaseMapper<MtimeFilmT> {

    MtimeFilmT selectByName(String filmName);
}
