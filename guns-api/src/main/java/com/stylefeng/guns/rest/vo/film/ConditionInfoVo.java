package com.stylefeng.guns.rest.vo.film;

import java.io.Serializable;
import java.time.Year;
import java.util.List;

/**
 * Created by Cyb
 * Date 2019/4/23/023  Time 14:42
 */
public class ConditionInfoVo implements Serializable {
    private List<CatInfoVo> catInfo;
    private List<SourceInfoVo> sourceInfo;
    private List<YearInfoVo> yearInfo;

    public ConditionInfoVo() {
    }

    public ConditionInfoVo(List<CatInfoVo> catInfo, List<SourceInfoVo> sourceInfo, List<YearInfoVo> yearInfo) {
        this.catInfo = catInfo;
        this.sourceInfo = sourceInfo;
        this.yearInfo = yearInfo;
    }

    public List<CatInfoVo> getCatInfo() {
        return catInfo;
    }

    public void setCatInfo(List<CatInfoVo> catInfo) {
        this.catInfo = catInfo;
    }

    public List<SourceInfoVo> getSourceInfo() {
        return sourceInfo;
    }

    public void setSourceInfo(List<SourceInfoVo> sourceInfo) {
        this.sourceInfo = sourceInfo;
    }

    public List<YearInfoVo> getYearInfo() {
        return yearInfo;
    }

    public void setYearInfo(List<YearInfoVo> yearInfo) {
        this.yearInfo = yearInfo;
    }
}
