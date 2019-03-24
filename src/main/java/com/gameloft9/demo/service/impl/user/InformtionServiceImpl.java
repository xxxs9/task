package com.gameloft9.demo.service.impl.user;

import com.gameloft9.demo.dataaccess.dao.user.UserInformationMapper;

import com.gameloft9.demo.dataaccess.model.user.UserInformation;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.user.InformtionService;
import com.gameloft9.demo.service.beans.system.InformationAddRequest;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
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
        dao.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    @Transactional(rollbackForClassName = "Exception")
    public String addInformation(InformationAddRequest information) {
        CheckUtil.notNull(information, "添加信息为空");
        CheckUtil.notBlank(information.getLoginName(), "发布者名为空");
        CheckUtil.notBlank(information.getInformationTitle(), "资讯标题为空");
        //信息存储
        UserInformation in = new UserInformation();
        in.setId(UUIDUtil.getUUID());
        in.setLoginName(information.getLoginName());
        in.setInformationContent(information.getInformationContent());
        in.setInformationImg(information.getInformationImg());
        in.setInformationTitle(information.getInformationTitle());
        in.setInformationType(information.getInformationType());
        in.setIsTop(information.getIsTop());
        in.setCreateTime(new Date());
        //添加信息
        dao.insert(in);
        return in.getId();
    }

    @Override
    public UserInformation getById(String id) {
        CheckUtil.notBlank(id,"用户id为空");
        //根据主键查询
        UserInformation userInformation = dao.selectByPrimaryKey(id);
        return userInformation;
    }

    @Override
    public Boolean updateInformation(UserInformation request) {
        //获取创建时间不变
        UserInformation selectCreateTimeExp = dao.selectByPrimaryKey(request.getId());
        //设置传入属性
        UserInformation userInformation = new UserInformation();
        userInformation.setId(request.getId());
        userInformation.setCreateTime(selectCreateTimeExp.getCreateTime());
        userInformation.setIsTop(request.getIsTop());
        userInformation.setInformationType(request.getInformationType());
        userInformation.setInformationTitle(request.getInformationTitle());
        userInformation.setInformationImg(request.getInformationImg());
        userInformation.setInformationContent(request.getInformationContent());
        userInformation.setLoginName(request.getLoginName());
        userInformation.setUpdateTime(new Date());
        dao.updateByPrimaryKey(userInformation);
        return true;
    }
}
