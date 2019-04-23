package com.stylefeng.guns.rest.vo.film;

import java.io.Serializable;

/**
 * Created by Cyb
 * Date 2019/4/23/023  Time 10:57
 */
public class BannerVo implements Serializable {
    private int bannerId;
    private String bannerAddress;
    private String bannerUrl;

    public BannerVo() {
    }

    public BannerVo(int bannerId, String bannerAddress, String bannerUrl) {
        this.bannerId = bannerId;
        this.bannerAddress = bannerAddress;
        this.bannerUrl = bannerUrl;
    }

    public int getBannerId() {
        return bannerId;
    }

    public void setBannerId(int bannerId) {
        this.bannerId = bannerId;
    }

    public String getBannerAddress() {
        return bannerAddress;
    }

    public void setBannerAddress(String bannerAddress) {
        this.bannerAddress = bannerAddress;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }
}
