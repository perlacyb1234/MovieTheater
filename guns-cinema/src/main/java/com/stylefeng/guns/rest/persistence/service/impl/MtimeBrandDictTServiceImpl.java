package com.stylefeng.guns.rest.persistence.service.impl;

import com.stylefeng.guns.rest.persistence.Vo.AreaVo;
import com.stylefeng.guns.rest.persistence.Vo.BrandVo;
import com.stylefeng.guns.rest.persistence.Vo.HalltypeVo;
import com.stylefeng.guns.rest.persistence.dao.MtimeAreaDictTMapper;
import com.stylefeng.guns.rest.persistence.dao.MtimeHallDictTMapper;
import com.stylefeng.guns.rest.persistence.model.MtimeAreaDictT;
import com.stylefeng.guns.rest.persistence.model.MtimeBrandDictT;
import com.stylefeng.guns.rest.persistence.dao.MtimeBrandDictTMapper;
import com.stylefeng.guns.rest.persistence.model.MtimeHallDictT;
import com.stylefeng.guns.rest.persistence.service.IMtimeBrandDictTService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 品牌信息表 服务实现类
 * </p>
 *
 * @author liangzhen
 * @since 2019-04-21
 */
@Service
public class MtimeBrandDictTServiceImpl extends ServiceImpl<MtimeBrandDictTMapper, MtimeBrandDictT> implements IMtimeBrandDictTService {

    @Autowired
    MtimeBrandDictTMapper mtimeBrandDictTMapper;
    @Autowired
    MtimeAreaDictTMapper mtimeAreaDictTMapper;
    @Autowired
    MtimeHallDictTMapper mtimeHallDictTMapper;

    @Override
    public Map selectBrandsByBrandIdHallTypeAreaId(String brandId, String hallType, String areaId) {

        ArrayList<MtimeBrandDictT> brands = mtimeBrandDictTMapper.selectBrandsByBrandId(brandId);

        ArrayList<BrandVo> brandList = new ArrayList<>();
        for (MtimeBrandDictT brand : brands) {
            BrandVo brandVo = new BrandVo();
            brandVo.setBrandId(brand.getUuid());
            brandVo.setBrandName(brand.getShowName());
            brandVo.setActive(true);
            brandList.add(brandVo);
        }

        ArrayList<MtimeAreaDictT> areas  = mtimeAreaDictTMapper.selectAreasByAreaId(areaId);

        ArrayList<AreaVo> areaList = new ArrayList<>();
        for (MtimeAreaDictT area : areas) {
            AreaVo areaVo = new AreaVo();
            areaVo.setAreaId(area.getUuid());
            areaVo.setAreaName(area.getShowName());
            areaVo.setActive(true);
            areaList.add(areaVo);
        }

        ArrayList<MtimeHallDictT> halltypes = mtimeHallDictTMapper.selecthallsByhallType(hallType);

        ArrayList<HalltypeVo> halltypeList = new ArrayList<>();
        for (MtimeHallDictT halltype : halltypes) {
            HalltypeVo halltypeVo = new HalltypeVo();
            halltypeVo.setHalltypeId(halltype.getUuid());
            halltypeVo.setHalltypeName(halltype.getShowName());
            halltypeVo.setActive(true);
            halltypeList.add(halltypeVo);
        }

        HashMap<String, Object> hashMap = new HashMap<>();
        if(!brandList.isEmpty()){
            hashMap.put("brandList",brandList);
        }
        if(!areaList.isEmpty()){
            hashMap.put("areaList",areaList);
        }
        if(!halltypeList.isEmpty()){
            hashMap.put("halltypeList",halltypeList);
        }

        return hashMap;

    }
}
