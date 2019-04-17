package com.gameloft9.demo.webmagic.processor;

import com.gameloft9.demo.webmagic.template.ExtractTemplate;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * 抓取用户英雄联盟统计数据
 */
public class GameDateProcessor implements PageProcessor {

    // 抓取网站的相关配置，可以包括编码、抓取间隔1s、重试次数等
    private Site site = Site.me().setCharset("utf8").setRetryTimes(1000).setSleepTime(1000);

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        Selectable st =  html.xpath("//table[@class=\"table table-hover recent-game-list\"]");
        List<Selectable> stList = st.nodes();
        //基本统计数据
        String content = html.xpath("//table[@class=\"table\"]/tbody/tr/td/allText()").get();
        //各个赛季统计数据
        ExtractTemplate.extractContentStatisticsTemplat(stList.get(0));
        if (stList.size() > 2){
            //英雄使用情况
            ExtractTemplate.extractHeroDetailTemplat(stList.get(1));
        }
        System.out.println("综合统计: "+ content);
    }

    @Override
    public Site getSite() {
        return site;
    }
}
