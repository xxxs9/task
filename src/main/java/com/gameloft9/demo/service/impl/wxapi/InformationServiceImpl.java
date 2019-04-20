package com.gameloft9.demo.service.impl.wxapi;

import com.gameloft9.demo.dataaccess.dao.user.UserInformationMapper;
import com.gameloft9.demo.dataaccess.model.user.UserInformation;
import com.gameloft9.demo.dto.index.BannerDto;
import com.gameloft9.demo.dto.index.NewsDto;
import com.gameloft9.demo.service.api.wxapi.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class InformationServiceImpl implements InformationService {

    @Autowired
    private UserInformationMapper userInformationMapper;


    /**
     * 获取滚动栏
     * @return
     */
    @Override
    public List<BannerDto> getBannerList() {
        List<BannerDto> dtoList = new ArrayList<>();
        List<UserInformation> iList = userInformationMapper.getBannerList();
        if (iList != null && iList.size() > 0){
            for (int i = 0 , num = iList.size() ; i < num ; i++) {
                BannerDto dto = new BannerDto();
                dto.setId(iList.get(i).getId());
                dto.setUrl(iList.get(i).getInformationImg());
                //TODO 等待填充外部跳转地址
                dto.setLink("外部跳转地址等待填充");
                dtoList.add(dto);
            }
        }
        return dtoList;
    }

    /**
     * 获取新闻列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<NewsDto> getNewsList(Integer pageNum, Integer pageSize) {

        List<NewsDto> dtoList = new ArrayList<>();
        List<UserInformation> iList = userInformationMapper.getNewsList(pageNum,pageSize);
        if (iList != null && iList.size() > 0){
            for (int i = 0 , num = iList.size() ; i < num ; i++) {
                NewsDto dto = new NewsDto();
                //TODO 等待填充外部跳转地址
                dto.setId(iList.get(i).getId());
                dto.setAddress("文章Url跳转地址");
                dto.setArticle(iList.get(i).getInformationContent());
                dto.setName(iList.get(i).getInformationTitle());
                dto.setImages(iList.get(i).getInformationImg());
                dtoList.add(dto);
            }
        }
        return dtoList;
    }
}
