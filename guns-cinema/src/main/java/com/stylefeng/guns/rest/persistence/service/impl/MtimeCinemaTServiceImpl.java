package com.stylefeng.guns.rest.persistence.service.impl;

import com.stylefeng.guns.rest.persistence.Vo.CinemaVo;
import com.stylefeng.guns.rest.persistence.Vo.MyPageHelper;
import com.stylefeng.guns.rest.persistence.model.MtimeCinemaT;
import com.stylefeng.guns.rest.persistence.dao.MtimeCinemaTMapper;
import com.stylefeng.guns.rest.persistence.service.IMtimeCinemaTService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class MtimeCinemaTServiceImpl extends ServiceImpl<MtimeCinemaTMapper, MtimeCinemaT> implements IMtimeCinemaTService {

    @Autowired
    MtimeCinemaTMapper mtimeCinemaTMapper;

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
}
