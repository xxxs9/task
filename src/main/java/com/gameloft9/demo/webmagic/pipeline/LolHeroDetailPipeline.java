package com.gameloft9.demo.webmagic.pipeline;

import com.gameloft9.demo.dataaccess.dao.user.HeroDetailMapper;
import com.gameloft9.demo.dataaccess.dao.user.HeroSkillMapper;
import com.gameloft9.demo.webmagic.HeroReptile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 英雄明细持久化处理
 */
@Component
//@Transactional(rollbackFor = Exception.class)
public class LolHeroDetailPipeline implements Pipeline {

    @Autowired
    private HeroDetailMapper heroDetailMapper;
    @Autowired
    private HeroSkillMapper heroSkillMapper;


    @Override
    public void process(ResultItems resultItems, Task task) {
        HeroReptile heroReptile = resultItems.get("detail");
        if (heroReptile != null){
            heroDetailMapper.insert(heroReptile.getHeroDetail());
            for (int i = 0 , num = heroReptile.getHsList().size(); i < num ; i ++ ){
                heroSkillMapper.insert(heroReptile.getHsList().get(i));
            }
        }
    }
}
