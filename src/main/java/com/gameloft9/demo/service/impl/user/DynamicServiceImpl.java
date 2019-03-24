package com.gameloft9.demo.service.impl.user;

import com.gameloft9.demo.dataaccess.dao.user.UserDynamicMapper;
import com.gameloft9.demo.dataaccess.model.user.UserDynamic;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.user.DynamicService;
import com.gameloft9.demo.service.beans.system.PageRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DynamicServiceImpl implements DynamicService {
    @Autowired(required = false)
    UserDynamicMapper dao;

    @Override
    public List<UserDynamic> getAll(String page, String limit, String loginName) {
        PageRange pageRange = new PageRange(page,limit);
        return dao.getAll(pageRange.getStart(), pageRange.getEnd(), loginName);
    }

    @Override
    public Integer countGetAll(String loginName) {
        return dao.countGetAll(loginName);
    }

    @Override
    public Boolean deleteById(String id) {
        CheckUtil.notBlank(id,"id为空");
        //删除
        dao.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public UserDynamic getById(String id) {
        CheckUtil.notBlank(id, "id为空");
        //根据主键获取信息
        UserDynamic dynamic = dao.selectByPrimaryKey(id);
        return dynamic;
    }

    @Override
    public Boolean updateDynamic(UserDynamic dynamic) {
        CheckUtil.notNull(dynamic.getId(), "id不能为空");
        //获取旧的信息
        UserDynamic oldDynamic = dao.selectByPrimaryKey(dynamic.getId());
        //设置创建时间
        dynamic.setCreateTime(oldDynamic.getCreateTime());
        //设置更新时间
        dynamic.setUpdateTime(new Date());
        dao.updateByPrimaryKey(dynamic);
        return true;
    }
}
