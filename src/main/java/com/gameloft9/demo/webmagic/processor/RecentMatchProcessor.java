package com.gameloft9.demo.webmagic.processor;

import com.gameloft9.demo.webmagic.template.ExtractTemplate;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

public class RecentMatchProcessor implements PageProcessor {

    // 抓取网站的相关配置，可以包括编码、抓取间隔1s、重试次数等
    private Site site = Site.me().setCharset("utf8").setRetryTimes(1000).setSleepTime(1000);


    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        Selectable st =  html.xpath("//table[@class=\"table table-hover recent-game-list\"]");
        ExtractTemplate.extractRecentMatchTemplat(st);
    }

    @Override
    public Site getSite() {
        return site;
    }
}
