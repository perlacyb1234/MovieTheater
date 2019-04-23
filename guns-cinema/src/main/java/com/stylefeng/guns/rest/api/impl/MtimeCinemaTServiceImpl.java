package com.stylefeng.guns.rest.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.rest.api.IMtimeCinemaTService;
import com.stylefeng.guns.rest.persistence.dao.MtimeCinemaTMapper;
import com.stylefeng.guns.rest.persistence.dao.MtimeFieldTMapper;
import com.stylefeng.guns.rest.persistence.dao.MtimeHallFilmInfoTMapper;
import com.stylefeng.guns.rest.persistence.model.cinema.MtimeCinemaT;
import com.stylefeng.guns.rest.persistence.model.cinema.MtimeFieldT;
import com.stylefeng.guns.rest.persistence.model.cinema.MtimeHallFilmInfoT;
import com.stylefeng.guns.rest.vo.cinema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 影院信息表 服务实现类
 * </p>
 *
 * @author liangzhen
 * @since 2019-04-21
 */
@Service(interfaceClass = IMtimeCinemaTService.class)
@Component
public class MtimeCinemaTServiceImpl extends ServiceImpl<MtimeCinemaTMapper, MtimeCinemaT> implements IMtimeCinemaTService {

    @Autowired
    MtimeCinemaTMapper mtimeCinemaTMapper;
    @Autowired
    MtimeFieldTMapper mtimeFieldTMapper;
    @Autowired
    MtimeHallFilmInfoTMapper mtimeHallFilmInfoTMapper;

    @Override
    public Map selectCinemaByBrandIdDistrictIdHallType(String brandId,String districtId,String hallType,int nowPage,int pageSize) {

        HashMap<String, Object> map = new HashMap<>();

        ArrayList<MtimeCinemaT> mtimeCinemaTS = mtimeCinemaTMapper.selectCinemaByBrandId(brandId,districtId);
        ArrayList<CinemaVo> cinemaList = new ArrayList<>();
        for (MtimeCinemaT mtimeCinemaT : mtimeCinemaTS) {
            String hallIds = mtimeCinemaT.getHallIds();
            if(hallType != null && hallType != "" && !hallIds.contains(hallType)){
                continue;
            }
            CinemaVo cinemaVo = new CinemaVo();
            cinemaVo.setUuid(mtimeCinemaT.getUuid());
            cinemaVo.setCinemaName(mtimeCinemaT.getCinemaName());
            cinemaVo.setAddress(mtimeCinemaT.getCinemaAddress());
            cinemaVo.setMinimumPrice(mtimeCinemaT.getMinimumPrice());
            cinemaList.add(cinemaVo);

        }
        HashMap<String, Object> hashMap = new HashMap<>();
        int totalNum = cinemaList.size();
        int totalPage = (int) Math.ceil(1.0 * totalNum / pageSize );
        List subList = MyPageHelper.subList(nowPage, pageSize, cinemaList);
        if(!subList.isEmpty()){
            hashMap.put("cinemas",subList);
            map.put("data",hashMap);
            map.put("totalPage",totalPage);
            map.put("nowPage",nowPage);
        }

        return map;

    }

    @Override
    public Map selectFieldsById(String cinemaId) {
        HashMap<String, Object> map = new HashMap<>();
        MtimeCinemaT mtimeCinemaT = mtimeCinemaTMapper.selectCinemaByCinemaId(cinemaId);
        if(mtimeCinemaT != null){
            CinemaInfoVo cinemaInfo = new CinemaInfoVo();
            cinemaInfo.setCinemaId(mtimeCinemaT.getUuid());
            cinemaInfo.setImgUrl(mtimeCinemaT.getImgAddress());
            cinemaInfo.setCinemaName(mtimeCinemaT.getCinemaName());
            cinemaInfo.setCinemaPhone(mtimeCinemaT.getCinemaPhone());
            cinemaInfo.setCinemaAdress(mtimeCinemaT.getCinemaAddress());
            map.put("cinemaInfo",cinemaInfo);
        }

        ArrayList<MtimeFieldT> mtimeFilmFieldList = mtimeFieldTMapper.selectFilmFieldsByCinemaId(cinemaId);
        ArrayList<MtimeHallFilmInfoT> mtimeHallFilmInfoList = new ArrayList<>();
        ArrayList<Map<String,Object>> filmList = new ArrayList<>();

        for (MtimeFieldT mtimeFieldT : mtimeFilmFieldList) {

            MtimeHallFilmInfoT mtimeHallFilmInfo = mtimeHallFilmInfoTMapper.selectFilmInfosByFilmId(mtimeFieldT.getFilmId());
            mtimeHallFilmInfoList.add(mtimeHallFilmInfo);

        }

        for (MtimeHallFilmInfoT filmInfo : mtimeHallFilmInfoList) {
            FilmInfoVo filmInfoVo = new FilmInfoVo();
            filmInfoVo.setFilmId(filmInfo.getFilmId());
            filmInfoVo.setFilmName(filmInfo.getFilmName());
            filmInfoVo.setFilmLength(filmInfo.getFilmLength());
            filmInfoVo.setFilmCats(filmInfo.getFilmCats());
            filmInfoVo.setActor(filmInfo.getActors());
            filmInfoVo.setImgAddress(filmInfo.getImgAddress());

            /* 0-2D,1-3D,2-3DIMAX,4-无*/
            if(filmInfo.getFilmType() == null){
                filmInfoVo.setFilmType("4-无");
            }else{
                switch(filmInfo.getFilmType()){
                    case 0:
                        filmInfoVo.setFilmType("0-2D");
                        break;
                    case 1:
                        filmInfoVo.setFilmType("1-3D");
                        break;
                    case 2:
                        filmInfoVo.setFilmType("2-3DIMAX");
                        break;
                    default:
                        filmInfoVo.setFilmType("4-无");
                        break;
                }
            }

            int filmId = filmInfoVo.getFilmId();
            int k = 0;
            ArrayList<FilmFieldVo> filmFieldVos = new ArrayList<>();
            for (int i = k; i < mtimeFilmFieldList.size(); i++) {
                if(filmId == mtimeFilmFieldList.get(i).getFilmId()){
                    MtimeFieldT remove = mtimeFilmFieldList.remove(i);
                    FilmFieldVo filmFieldVo = new FilmFieldVo();
                    filmFieldVo.setFieldId(remove.getUuid());
                    filmFieldVo.setBeginTime(remove.getBeginTime());
                    filmFieldVo.setEndTime(remove.getEndTime());
                    filmFieldVo.setLanguage(filmInfo.getFilmLanguage());
                    filmFieldVo.setHallName(remove.getHallName());
                    filmFieldVo.setPrice(remove.getPrice());
                    filmFieldVos.add(filmFieldVo);
                }
            }
            filmInfoVo.setFilmFields(filmFieldVos);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("filmInfo",filmInfoVo);
            filmList.add(hashMap);
        }

        if(!filmList.isEmpty()){
            map.put("filmList",filmList);
        }

        return map;
    }


}
