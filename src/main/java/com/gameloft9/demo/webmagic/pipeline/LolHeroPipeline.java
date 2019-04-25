package com.gameloft9.demo.webmagic.pipeline;

import com.gameloft9.demo.dataaccess.dao.user.HeroBaseMapper;
import com.gameloft9.demo.dataaccess.model.user.HeroBase;
import com.gameloft9.demo.webmagic.beans.Heros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

@Component
public class LolHeroPipeline implements Pipeline {

    @Autowired
    private HeroBaseMapper heroBaseMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {

        Heros hs = resultItems.get("hero");
        if (hs != null){
            List<HeroBase> heroList = hs.getHeroList();
            if (heroList != null && heroList.size() > 0){
                for (int i = 0 , num = heroList.size(); i < num ; i ++ ){
                    heroBaseMapper.insertSelective(heroList.get(i));
                }
            }


        }
    }
}
