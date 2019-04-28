package com.gameloft9.demo.service.api.wxapi;

import com.gameloft9.demo.dataaccess.model.user.UserInformation;
import com.gameloft9.demo.dto.index.BannerDto;
import com.gameloft9.demo.dto.index.NewsDto;

import java.util.List;

public interface InformationService {

    /**
     * 获取滚动栏
     * @return
     */
    List<BannerDto> getBannerList();

    /**
     * 获取新闻列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<NewsDto> getNewsList(Integer pageNum, Integer pageSize);

    /**
     * 获取文章详情
     * @param informationId
     * @return
     */
    UserInformation getDetail(Integer informationId);
}

