package com.stylefeng.guns.rest.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.rest.api.IMtimeBrandDictTService;
import com.stylefeng.guns.rest.persistence.dao.MtimeAreaDictTMapper;
import com.stylefeng.guns.rest.persistence.dao.MtimeHallDictTMapper;
import com.stylefeng.guns.rest.persistence.dao.MtimeBrandDictTMapper;
import com.stylefeng.guns.rest.persistence.model.cinema.MtimeAreaDictT;
import com.stylefeng.guns.rest.persistence.model.cinema.MtimeBrandDictT;
import com.stylefeng.guns.rest.persistence.model.cinema.MtimeHallDictT;
import com.stylefeng.guns.rest.vo.cinema.AreaVo;
import com.stylefeng.guns.rest.vo.cinema.BrandVo;
import com.stylefeng.guns.rest.vo.cinema.HalltypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
@Service(interfaceClass = IMtimeBrandDictTService.class)
@Component
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
