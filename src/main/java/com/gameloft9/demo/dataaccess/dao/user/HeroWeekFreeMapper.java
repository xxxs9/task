package com.gameloft9.demo.dataaccess.dao.user;

import com.gameloft9.demo.dataaccess.model.user.HeroWeekFree;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface HeroWeekFreeMapper extends Mapper<HeroWeekFree> {

    void truncateTable();

    void insertList(List<HeroWeekFree> list);
}