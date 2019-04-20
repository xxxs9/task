package com.gameloft9.demo.webmagic.processor;


import com.gameloft9.demo.dataaccess.model.user.GetReptileId;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 获取数据爬去的用户爬取id
 */
public class LolUserIdProcessor implements PageProcessor {

    /**
     * 抓取网站的相关配置，可以包括编码、抓取间隔1s、重试次数等
     */
    private Site site = Site.me().setCharset("utf8").setRetryTimes(1000).setSleepTime(1000);

    @Override
    public void process(Page page) {
        String jsonStr = page.getJson().toString();
        String regex = "(user_id\":.*?,+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(jsonStr);
        String reptileId = null;
        while (matcher.find()) {
            String str = matcher.group(1);
            reptileId = str.substring(str.indexOf(":") + 1, str.indexOf(","));
            System.out.println(reptileId);
            break;
        }
        if (reptileId != null){
            GetReptileId gri = new GetReptileId();
            gri.setCode(reptileId);
            gri.setId(1);
            page.putField("gri",gri);
        }

    }

    @Override
    public Site getSite() {
        return site;
    }
}
