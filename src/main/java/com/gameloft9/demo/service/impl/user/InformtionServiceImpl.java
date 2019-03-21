package com.gameloft9.demo.service.impl.user;

import com.gameloft9.demo.dataaccess.dao.user.UserInformationMapper;

import com.gameloft9.demo.dataaccess.model.user.UserInformation;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.user.InformtionService;
import com.gameloft9.demo.service.beans.system.PageRange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class InformtionServiceImpl implements InformtionService {

    @Autowired(required = false)
    UserInformationMapper dao;

    @Override
    public List<UserInformation> getAll(String page, String limit, String loginName, String informationTitle, String isTop) {
        PageRange pageRange = new PageRange(page,limit);
        return dao.getAll(pageRange.getStart(), pageRange.getEnd(), loginName, informationTitle, isTop);
    }

    @Override
    public int countGetAll(String loginName, String informationTitle, String isTop) {
        return dao.countGetAll(loginName, informationTitle, isTop);
    }

    @Override
    public Boolean deleteById(String id) {
        CheckUtil.notBlank(id,"用户id为空");
        //删除
        dao.deleteById(id);
        return true;
    }
}
