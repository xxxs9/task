package com.gameloft9.demo.webmagic.pipeline;

import com.gameloft9.demo.dataaccess.dao.user.ReptileUserRecentMatchMapper;
import com.gameloft9.demo.dataaccess.model.user.ReptileUserRecentMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

@Component
public class RecentMatchPipeline implements Pipeline {

    @Autowired
    private ReptileUserRecentMatchMapper reptileUserRecentMatchMapper;


    @Override
    public void process(ResultItems resultItems, Task task) {
        List<ReptileUserRecentMatch> rmList = resultItems.get("rm");
        //会自动入库 - 如果是重复数据 会抛出sql异常
        if (rmList != null && rmList.size() > 0){
            reptileUserRecentMatchMapper.insertList(rmList);
        }

    }
}
