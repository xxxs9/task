package com.gameloft9.demo.webmagic.processor;

import com.gameloft9.demo.dataaccess.model.user.HeroBase;
import com.gameloft9.demo.webmagic.Heros;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * 抓取lol英雄数据
 */
public class LolHeroProcessor implements PageProcessor {

    /**
     * 抓取网站的相关配置，可以包括编码、抓取间隔1s、重试次数等
     */
    private Site site = Site.me().setCharset("utf8").setRetryTimes(1000).setSleepTime(1000);


    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        Selectable st =  html.xpath("//ul[@id=\"champion_list\"]/li");
        Selectable des = html.xpath("//div[@class=\"tooltip champion_tooltip\"]");
        List<Selectable> stList = st.nodes();
        List<Selectable> desList = des.nodes();
        if (stList != null && stList.size() > 0 && stList.size() == desList.size()){
            Heros hs = new Heros();
            List<HeroBase> heroList = new ArrayList<>();
            for (int i = 0, num = stList.size();i < num ; i ++ ){
                HeroBase hb = new HeroBase();
                Selectable selectable = stList.get(i);
                Selectable d = desList.get(i);
                hb.setImgUrl(d.xpath("//img/@src").get());
                hb.setHeroName(d.xpath("//h2/text()").get());
                hb.setHeroTitle(d.xpath("//h3/text()").get());
                hb.setDes(d.xpath("//p/text()").get());
                hb.setDetailUrl(selectable.xpath("//a/@href").get());
                heroList.add(hb);
            }
            hs.setHeroList(heroList);
            page.putField("hero",hs);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
