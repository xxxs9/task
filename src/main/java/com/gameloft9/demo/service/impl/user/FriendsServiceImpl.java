package com.gameloft9.demo.service.impl.user;

import com.gameloft9.demo.dataaccess.dao.user.UserFriendsMapper;
import com.gameloft9.demo.dataaccess.model.user.UserFriends;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.user.FriendsService;
import com.gameloft9.demo.service.beans.system.PageRange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class FriendsServiceImpl implements FriendsService {

    @Autowired(required = false)
    UserFriendsMapper dao;

    @Override
    public List<UserFriends> getAll(String page, String limit, String loginName) {
        PageRange pageRange = new PageRange(page,limit);
        return dao.getAll(pageRange.getStart(), pageRange.getEnd(), loginName);
    }

    @Override
    public Integer countGetAll(String loginName) {
        return dao.countGetAll(loginName);
    }

    @Override
    public Boolean deleteById(String id) {
        CheckUtil.notBlank(id, "id不能为空");
        dao.deleteByPrimaryKey(id);
        return true;
    }
}
