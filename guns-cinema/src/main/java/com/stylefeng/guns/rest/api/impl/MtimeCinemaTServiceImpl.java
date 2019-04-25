package com.stylefeng.guns.rest.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.rest.api.IMtimeCinemaTService;
import com.stylefeng.guns.rest.persistence.dao.MtimeCinemaTMapper;
import com.stylefeng.guns.rest.persistence.dao.MtimeFieldTMapper;
import com.stylefeng.guns.rest.persistence.dao.MtimeHallFilmInfoTMapper;

import com.stylefeng.guns.rest.persistence.model.MtimeCinemaT;
import com.stylefeng.guns.rest.persistence.model.MtimeFieldT;
import com.stylefeng.guns.rest.persistence.model.MtimeHallFilmInfoT;
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

        ArrayList<MtimeCinemaT> mtimeCinemaTS = mtimeCinemaTMapper.selectCinemaByBrandIdDistrictIdHallType(brandId,districtId,hallType);
        ArrayList<CinemaVo> cinemaList = new ArrayList<>();
        for (MtimeCinemaT mtimeCinemaT : mtimeCinemaTS) {
            // 通过getCinemaVo方法将MtimeCinemaT(mtime_cinema_t)中报文所需的部分内容抽取出来封装到封装类CinemaVo中
            CinemaVo cinemaVo = getCinemaVo(mtimeCinemaT);

            cinemaList.add(cinemaVo);

        }
        HashMap<String, Object> hashMap = new HashMap<>();
        int totalNum = cinemaList.size();
        int totalPage = (int) Math.ceil(1.0 * totalNum / pageSize );
        List subList = MyPageHelper.subList(nowPage, pageSize, cinemaList);
        if(!subList.isEmpty()){
            //hashMap.put("cinemas",subList);
            //map.put("data",hashMap);
            map.put("data",subList);
            map.put("totalPage",totalPage);
            map.put("nowPage",nowPage);
        }

        return map;

    }

    @Override
    public Map selectFieldsById(String cinemaId) {
        HashMap<String, Object> map = new HashMap<>();
        MtimeCinemaT mtimeCinemaT = mtimeCinemaTMapper.selectCinemaByCinemaId(cinemaId);
        // 通过cinemaId查询mtime_cinema_t获得cinema全属性bean mtimeCinemaT,经过getCinemaInfoVo方法抽取报文所需部分信息
        if(mtimeCinemaT != null){

            CinemaInfoVo cinemaInfo = getCinemaInfoVo(mtimeCinemaT);

            map.put("cinemaInfo",cinemaInfo);
        }
        /*通过cinemaId查询mtime_field_t表获取field全属性bean MtimeFieldT的list*/
        ArrayList<MtimeFieldT> mtimeFilmFieldList = mtimeFieldTMapper.selectFilmFieldsByCinemaId(cinemaId);
        ArrayList<MtimeHallFilmInfoT> mtimeHallFilmInfoList = new ArrayList<>();
        ArrayList<Map<String,Object>> filmList = new ArrayList<>();

        for (MtimeFieldT mtimeFieldT : mtimeFilmFieldList) {
            /*通过field list中的每一个field bean film_id字段查询mtime_hall_film_info_t
            * 并通过film_name进行联合查询mtime_film_t 获取的属性值封装进MtimeHallFilmInfoT
            * (对MtimeHallFilmInfoT改了字段，新增片源类型film_type)
            * */
            MtimeHallFilmInfoT mtimeHallFilmInfo = mtimeHallFilmInfoTMapper.selectFilmInfosByFilmId(mtimeFieldT.getFilmId());
            mtimeHallFilmInfoList.add(mtimeHallFilmInfo);

        }

        for (MtimeHallFilmInfoT filmInfo : mtimeHallFilmInfoList) {
            /*将film信息从MtimeHallFilmInfoT中抽取报文需要的部分封装到FilmInFoVo,
                同时根据报文需求嵌入FilmFieldVo包装类的list
            */
            FilmInfoVo filmInfoVo = getFilmInFoVoFromMtimeHallFilmInfoT(filmInfo,mtimeFilmFieldList);


            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("filmInfo",filmInfoVo);
            filmList.add(hashMap);
        }

        if(!filmList.isEmpty()){
            map.put("filmList",filmList);
        }

        return map;
    }

    @Override
    public Map selectFieldByCinemaIdAndFieldId(String cinemaId, String fieldId) {

        HashMap<String, Object> map = new HashMap<>();
        MtimeCinemaT mtimeCinemaT = mtimeCinemaTMapper.selectCinemaByCinemaId(cinemaId);

        if(mtimeCinemaT != null){

            CinemaInfoVo cinemaInfo = getCinemaInfoVo(mtimeCinemaT);

            map.put("cinemaInfo",cinemaInfo);
        }

        MtimeFieldT mtimeFieldT = mtimeFieldTMapper.selectFilmFieldsByFieldId(fieldId);
        MtimeHallFilmInfoT mtimeHallFilmInfo = null;
        if(mtimeFieldT != null){
            mtimeHallFilmInfo = mtimeHallFilmInfoTMapper.selectFilmInfosByFilmId(mtimeFieldT.getFilmId());

            HallInfoVo hallInfoVo = new HallInfoVo();
            hallInfoVo.setHallFieldId(mtimeFieldT.getHallId());
            hallInfoVo.setHallName(mtimeFieldT.getHallName());
            hallInfoVo.setPrice(mtimeFieldT.getPrice());
            hallInfoVo.setSeatFile(mtimeFieldT.getSeatAddress());
            hallInfoVo.setSoldSeats("1,2,3,5,12");
            map.put("hallInfo",hallInfoVo);
        }

        // 封装不带field list的filmInfoVo
        if(mtimeHallFilmInfo != null){

            FilmInfoNoFieldVo filmInfoVo = getFilmInfoNoFieldVoFromMtimeHallFilmInfoT(mtimeHallFilmInfo);

            map.put("filmInfo",filmInfoVo);
        }



        return map;
    }

    private CinemaVo getCinemaVo(MtimeCinemaT mtimeCinemaT) {

        CinemaVo cinemaVo = new CinemaVo();
        cinemaVo.setUuid(mtimeCinemaT.getUuid());
        cinemaVo.setCinemaName(mtimeCinemaT.getCinemaName());
        cinemaVo.setAddress(mtimeCinemaT.getCinemaAddress());
        cinemaVo.setMinimumPrice(mtimeCinemaT.getMinimumPrice());

        return cinemaVo;

    }

    private FilmInfoVo getFilmInFoVoFromMtimeHallFilmInfoT(MtimeHallFilmInfoT filmInfo, ArrayList<MtimeFieldT> mtimeFilmFieldList) {
        // 根据前端报文需要将MtimeHallFilmInfoT中部分属性转入FilmInfoVo
        FilmInfoVo filmInfoVo = new FilmInfoVo();
        filmInfoVo.setFilmId(filmInfo.getFilmId());
        filmInfoVo.setFilmName(filmInfo.getFilmName());
        filmInfoVo.setFilmLength(filmInfo.getFilmLength());
        filmInfoVo.setFilmCats(filmInfo.getFilmCats());
        filmInfoVo.setActor(filmInfo.getActors());
        filmInfoVo.setImgAddress(filmInfo.getImgAddress());

        /* 0-2D,1-3D,2-3DIMAX,4-无*/
        filmInfoVo.setFilmType(filmTypeConvertIntToString(filmInfo.getFilmType()));

        int filmId = filmInfoVo.getFilmId();
        int k = 0;
        ArrayList<FilmFieldVo> filmFieldVos = new ArrayList<>();
        // 根据filmId，从mtimeFilmFieldList中将相同filmId的元素根据报文需求封装成FilmFieldVo然后形成List添加到filmInfoVo
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
        return filmInfoVo;

    }

    private String filmTypeConvertIntToString(Integer filmType) {

        String filmTypeVo = null;
        if(filmType == null){
            filmTypeVo = "4-无";
        }else{
            switch(filmType){
                case 0:
                    filmTypeVo = "0-2D";
                    break;
                case 1:
                    filmTypeVo = "1-3D";
                    break;
                case 2:
                    filmTypeVo = "2-3DIMAX";
                    break;
                default:
                    filmTypeVo = "4-无";
                    break;
            }
        }

        return filmTypeVo;
    }

    private CinemaInfoVo getCinemaInfoVo(MtimeCinemaT mtimeCinemaT) {

        CinemaInfoVo cinemaInfo = new CinemaInfoVo();
        cinemaInfo.setCinemaId(mtimeCinemaT.getUuid());
        cinemaInfo.setImgUrl(mtimeCinemaT.getImgAddress());
        cinemaInfo.setCinemaName(mtimeCinemaT.getCinemaName());
        cinemaInfo.setCinemaPhone(mtimeCinemaT.getCinemaPhone());
        cinemaInfo.setCinemaAdress(mtimeCinemaT.getCinemaAddress());

        return cinemaInfo;

    }

    private FilmInfoNoFieldVo getFilmInfoNoFieldVoFromMtimeHallFilmInfoT(MtimeHallFilmInfoT mtimeHallFilmInfo) {

        FilmInfoNoFieldVo filmInfoVo = new FilmInfoNoFieldVo();
        filmInfoVo.setFilmId(mtimeHallFilmInfo.getFilmId());
        filmInfoVo.setFilmName(mtimeHallFilmInfo.getFilmName());
        filmInfoVo.setFilmLength(mtimeHallFilmInfo.getFilmLength());
        filmInfoVo.setFilmCats(mtimeHallFilmInfo.getFilmCats());
        filmInfoVo.setImgAddress(mtimeHallFilmInfo.getImgAddress());

        /* 0-2D,1-3D,2-3DIMAX,4-无*/
        filmInfoVo.setFilmType(filmTypeConvertIntToString(mtimeHallFilmInfo.getFilmType()));
        return filmInfoVo;
    }


}
