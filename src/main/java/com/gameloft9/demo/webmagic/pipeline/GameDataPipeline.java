package com.gameloft9.demo.webmagic.pipeline;

import com.gameloft9.demo.dataaccess.dao.user.ReptileUserBaseMapper;
import com.gameloft9.demo.dataaccess.dao.user.ReptileUserContentStatisticsMapper;
import com.gameloft9.demo.webmagic.UserGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
public class GameDataPipeline implements Pipeline {


    @Autowired
    private ReptileUserBaseMapper reptileUserBaseMapper;
    @Autowired
    private ReptileUserContentStatisticsMapper reptileUserContentStatisticsMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        UserGame ug = resultItems.get("ug");
        if (ug != null && ug.getRub() != null && ug.getRList().size() > 0 ){
            if (reptileUserBaseMapper.queryByReptileServerId(
                    ug.getRub().getServerId(),ug.getRub().getReptileId()) == null){
                reptileUserBaseMapper.insert(ug.getRub());
                reptileUserContentStatisticsMapper.insertList(ug.getRList());
            }
        }
    }
}
