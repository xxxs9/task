package com.gameloft9.demo.webmagic.pipeline;

import com.gameloft9.demo.dataaccess.dao.user.HeroWeekFreeMapper;
import com.gameloft9.demo.dataaccess.model.user.HeroWeekFree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * 处理周免数据
 */
@Component
public class HeroWeeKFreePipeline implements Pipeline {

    @Autowired
    private HeroWeekFreeMapper heroWeekFreeMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {

        List<HeroWeekFree> list = resultItems.get("wf");
        if (list != null && list.size() > 0){
            heroWeekFreeMapper.truncateTable();
            heroWeekFreeMapper.insertList(list);
        }
    }
}
