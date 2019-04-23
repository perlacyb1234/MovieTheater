package com.stylefeng.guns.rest.persistence.dao;

import com.stylefeng.guns.rest.persistence.model.film.MtimeActorT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 演员表 Mapper 接口
 * </p>
 *
 * @author Cyb
 * @since 2019-04-22
 */
public interface MtimeActorTMapper extends BaseMapper<MtimeActorT> {
    List<Integer> selectActorIdsByFilmId(int filmId);

    String getRoleNameByFilmIdAndActorId(@Param("filmId") String filmId,@Param("actorId") Integer actorId);
}
