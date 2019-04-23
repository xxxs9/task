package com.gameloft9.demo.webmagic.processor;

import com.gameloft9.demo.dataaccess.model.user.HeroDetail;
import com.gameloft9.demo.dataaccess.model.user.HeroSkill;
import com.gameloft9.demo.webmagic.HeroReptile;
import com.gameloft9.demo.webmagic.template.ExtractTemplate;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * 抓取英雄明细的爬虫
 */
public class LolHeroDetailProcessor implements PageProcessor {

    /**
     * 抓取网站的相关配置，可以包括编码、抓取间隔1s、重试次数等
     */
    private Site site = Site.me().setCharset("utf8").setRetryTimes(1000).setSleepTime(1000);

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        //英雄详情实体
        HeroDetail hd = ExtractTemplate.extractHeroDetailTemplat(html);
        //技能详情实体
        List<HeroSkill> hsList = ExtractTemplate.extractHeroSkillTemplat(html);


//        ExtractTemplate.extractRuneTemplat(html);
//        ExtractTemplate.extractEquipTemplat(html);
        HeroReptile hr = new HeroReptile();
        hr.setHeroDetail(hd);
        hr.setHsList(hsList);
        page.putField("detail",hr);

    }

    @Override
    public Site getSite() {
        return site;
    }
}
