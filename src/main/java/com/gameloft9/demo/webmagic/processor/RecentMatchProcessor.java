package com.gameloft9.demo.webmagic.processor;

import com.gameloft9.demo.dataaccess.model.user.ReptileUserRecentMatch;
import com.gameloft9.demo.webmagic.template.ExtractTemplate;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

public class RecentMatchProcessor implements PageProcessor {

    /**
     * 抓取网站的相关配置，可以包括编码、抓取间隔1s、重试次数等
     */
    private Site site = Site.me().setCharset("utf8").setRetryTimes(1000).setSleepTime(1000);


    @Override
    public void process(Page page) {
        /**
         * 提取最近比赛数据模板
         */
        Html html = page.getHtml();
        Selectable tag = html.xpath("//ol[@class=\"breadcrumb\"]/li").nodes().get(1);
        String[] s = tag.xpath("//a/@href").get().split("/");
        Selectable st =  html.xpath("//table[@class=\"table table-hover recent-game-list\"]");
        List<ReptileUserRecentMatch> rmList = ExtractTemplate.extractRecentMatchTemplat(s[2].trim(),s[3].trim(),st);
        page.putField("rm",rmList);
    }

    @Override
    public Site getSite() {
        return site;
    }
}
