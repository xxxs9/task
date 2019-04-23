package com.gameloft9.demo.webmagic.pipeline;

import com.gameloft9.demo.dataaccess.dao.user.HeroEquipMapper;
import com.gameloft9.demo.dataaccess.model.user.HeroEquip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 处理抓取的出装数据工具
 */
@Component
public class HeroEquipPipeline implements Pipeline {

    @Autowired
    private HeroEquipMapper heroEquipMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        HeroEquip heroEquip = resultItems.get("equip");
        if (heroEquip != null){
            heroEquipMapper.insert(heroEquip);
        }
    }
}
