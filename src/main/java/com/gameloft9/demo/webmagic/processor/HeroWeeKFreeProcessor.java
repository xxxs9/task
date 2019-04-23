package com.gameloft9.demo.webmagic.processor;

import com.gameloft9.demo.dataaccess.model.user.HeroWeekFree;
import org.apache.ibatis.annotations.Select;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * 爬取周免英雄
 */
public class HeroWeeKFreeProcessor implements PageProcessor {

    /**
     * 抓取网站的相关配置，可以包括编码、抓取间隔1s、重试次数等
     */
    private Site site = Site.me().setCharset("utf8").setRetryTimes(1000).setSleepTime(1000);

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        List<Selectable> selectableList = html.xpath("//div[@class=\"row\"]/div[@class=\"col-md-3\"]").nodes();
        if (selectableList.size() > 1){
            List<HeroWeekFree> pipeList = new ArrayList<>();
            for (int i = 0 ,num = selectableList.size(); i < num ; i++){
                Selectable s = selectableList.get(i);
                HeroWeekFree hwf = new HeroWeekFree();
                //英雄头像
                hwf.setImg(s.xpath("//div[@class=\"col-md-3\"]/a/img/@src").get());
                hwf.setDes(s.xpath("//div[@class=\"col-md-3\"]/p/text()").get());
                String title = s.xpath("//div[@class=\"col-md-3\"]/a/img/@title").get();
                String[] array = title.split(" ");
                if (array.length > 0){
                    hwf.setHeroTitle(array[0]);
                    hwf.setHeroName(array[1]);
                }
                pipeList.add(hwf);
            }
            page.putField("wf",pipeList);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
