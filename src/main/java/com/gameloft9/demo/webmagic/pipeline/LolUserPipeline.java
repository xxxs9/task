package com.gameloft9.demo.webmagic.pipeline;

import com.gameloft9.demo.dataaccess.dao.user.GetReptileIdMapper;
import com.gameloft9.demo.dataaccess.model.user.GetReptileId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component("lolUserPipeline")
public class LolUserPipeline implements Pipeline {


    @Autowired
    private GetReptileIdMapper getReptileIdMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {

        GetReptileId gri = resultItems.get("gri");
        getReptileIdMapper.updateByPrimaryKey(gri);
    }
}
