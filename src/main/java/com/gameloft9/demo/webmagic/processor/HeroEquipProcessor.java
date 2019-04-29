package com.gameloft9.demo.webmagic.processor;

import com.gameloft9.demo.dataaccess.model.user.HeroEquip;
import com.gameloft9.demo.webmagic.template.ReptileDataUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 抓取英雄出装 - 爬虫
 */
public class HeroEquipProcessor implements PageProcessor {

    /**
     * 抓取网站的相关配置，可以包括编码、抓取间隔1s、重试次数等
     */
    private Site site = Site.me().setCharset("utf8").setRetryTimes(1000).setSleepTime(1000);


    @Override
    public void process(Page page) {
        //返回的json数据
        String jsonStr = page.getJson().toString();
        if (jsonStr.indexOf("info") == -1) {
//            System.out.println("record_id:" + regexString(jsonStr, "record_id"));
//            System.out.println("pre_cz:" + regexString(jsonStr, "pre_cz"));
//            System.out.println("pre_explain:" + regexString(jsonStr, "pre_explain"));
//
//            System.out.println("mid_cz:" + regexString(jsonStr, "mid_cz"));
//            System.out.println("mid_explain:" + regexString(jsonStr, "mid_explain"));
//
//            System.out.println("end_cz:" + regexString(jsonStr, "end_cz"));
//            System.out.println("end_explain:" + regexString(jsonStr, "end_explain"));
//
//            System.out.println("nf_cz:" + regexString(jsonStr, "nf_cz"));
//            System.out.println("nf_explain:" + regexString(jsonStr, "nf_explain"));

            HeroEquip heroEquip = new HeroEquip();
            //主键
            heroEquip.setRecordId(regexString(jsonStr, "record_id"));
            //出门装
            heroEquip.setPreCz(regexString(jsonStr, "pre_cz"));
            //出装思路
            heroEquip.setPreExplain(ReptileDataUtil.unicodeToString(regexString(jsonStr, "pre_explain")));
            //中期核心装
            heroEquip.setMidCz(regexString(jsonStr, "mid_cz"));
            //中期核心装思路
            heroEquip.setPreExplain(ReptileDataUtil.unicodeToString(regexString(jsonStr, "mid_explain")));
            //顺风装
            heroEquip.setEndCz(regexString(jsonStr, "end_cz"));
            //顺风装思路
            heroEquip.setEndExplain(ReptileDataUtil.unicodeToString(regexString(jsonStr, "end_explain")));
            //逆风装
            heroEquip.setNfCz(regexString(jsonStr, "nf_cz"));
            //逆风装思路
            heroEquip.setNfExplain(ReptileDataUtil.unicodeToString(regexString(jsonStr, "nf_explain")));
            page.putField("equip", heroEquip);
        }
    }

    /**
     * 正则表达式取数据
     */
    private String regexString(String jsonStr,String tag){
        String regex = "(" +tag + "\":\".*?\",+" + ")";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(jsonStr);
        String str = null;
        while (matcher.find()) {
            str = matcher.group(1);
            str = str.substring(str.indexOf("\":\"") + 3, str.lastIndexOf("\","));
            break;
        }
        return str;
    }

    @Override
    public Site getSite() {
        return site;
    }
}
