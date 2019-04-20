package com.gameloft9.demo.webmagic.template;

import com.gameloft9.demo.dataaccess.model.user.ReptileUserContentStatistics;
import com.gameloft9.demo.dataaccess.model.user.ReptileUserRecentMatch;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * 提取模板
 */
public class ExtractTemplate {

    /**
     * 爬取用户基本数据
     */
    public static void extractUserBaseTemplate(Selectable selectable){

    }

    /**
     * 提取各赛季统计数据模板
     */
    public static List<ReptileUserContentStatistics> extractContentStatisticsTemplat(String serverId, String reptileId ,Selectable selectable){

        List<ReptileUserContentStatistics> resultList = new ArrayList<>();
        //获取html标签中的tr集合
        List<Selectable> trList = selectable.xpath("//table[@class=\"table table-hover recent-game-list\"]/tbody/tr").nodes();
        if (trList != null && trList.size() > 0) {
            for (int i = 0, num = trList.size(); i < num; i++) {
                Selectable tr = trList.get(i);
                List<Selectable> tdList = tr.xpath("//tr/td").nodes();
                if (tdList != null && tdList.size() == 6) {
                    ReptileUserContentStatistics result = new ReptileUserContentStatistics();
                    result.setServerId(serverId);
                    result.setReptileId(reptileId);
                    //赛季类型
                    result.setContentType(tdList.get(0).xpath("//td/text()").get());
                    //胜场
                    int a = Integer.valueOf(tdList.get(3).xpath("//td/text()").get().trim());
                    result.setWin(a);
                    //负场
                    a = Integer.valueOf(tdList.get(4).xpath("//td/text()").get().trim());
                    result.setLose(Integer.valueOf(a));
                    resultList.add(result);
                } else {
                    System.out.println("第 " + (i + 1) + " 行数据异常或无效");
                }
            }
        }else{
            System.out.println("赛季统计表，列异常");
        }
        return resultList;
    }

    /**
     * 提取英雄使用明细模板
     */
    public static void extractHeroDetailTemplat(Selectable selectable){
    //.xpath("//table[@class=\"table table-hover recent-game-list\"]/tbody/allText()")
        List<Selectable> trList = selectable.xpath("//table[@class=\"table table-hover recent-game-list\"]/tbody/tr").nodes();
        if (trList != null && trList.size() > 0) {
            for (int i = 0, num = trList.size(); i < num; i++) {
                Selectable tr = trList.get(i);
                List<Selectable> tdList = tr.xpath("//tr/td").nodes();
                if (tdList != null && tdList.size() == 10) {
                    System.out.println("英雄：" + tdList.get(0).xpath("//td/img/@title").get());
                    System.out.print("场次：" + tdList.get(1).xpath("//td/text()").get());
                    System.out.print("胜率：" + tdList.get(2).xpath("//td/text()").get());
                    System.out.print("战损：" + tdList.get(3).xpath("//td/text()").get());
                    System.out.print("杀死助：" + tdList.get(4).xpath("//td/text()").get());
                    System.out.print("对英雄输出：" + tdList.get(5).xpath("//td/text()").get());
                    System.out.print("分钟经济：" + tdList.get(6).xpath("//td/text()").get());
                    System.out.print("10分钟补兵：" + tdList.get(7).xpath("//td/text()").get());
                    System.out.print("MVP&Carry：" + tdList.get(8).xpath("//td/text()").get());
                    System.out.println("上次使用：" + tdList.get(9).xpath("//td/text()").get());
                } else {
                    System.out.println("第 " + (i + 1) + " 行数据异常或无效");
                }
            }
        }else{
            System.out.println("英雄明细表，列异常");
        }
    }

    /**
     * 提取最近比赛数据模板
     */
    public static List<ReptileUserRecentMatch> extractRecentMatchTemplat(String serverId, String reptileId , Selectable selectable){
    //.xpath("//table[@class=\"table table-hover recent-game-list\"]/tbody/allText()")
        List<ReptileUserRecentMatch> rmList = new ArrayList<>();

        List<Selectable> trList = selectable.xpath("//table[@class=\"table table-hover recent-game-list\"]/tbody/tr").nodes();
        if (trList != null && trList.size() > 0) {
            for (int i = 0, num = trList.size(); i < num; i++) {
                Selectable tr = trList.get(i);
                List<Selectable> tdList = tr.xpath("//tr/td").nodes();
                if (tdList != null && tdList.size() == 5) {
                    ReptileUserRecentMatch rm = new ReptileUserRecentMatch();
                    rm.setReptileId(reptileId);
                    rm.setServerId(serverId);
                    rm.setHeroName(tdList.get(0).xpath("//td/a/img/@title").get().trim());
                    rm.setGameUrl(tdList.get(0).xpath("//td/a/@href").get().trim());
                    rm.setHeroImg(tdList.get(0).xpath("//td/a/img/@src").get().trim());
                    rm.setGameType(tdList.get(1).xpath("//td/text()").get().trim());
                    rm.setResult(tdList.get(2).xpath("//td/span/text()").get().trim());
                    rm.setGameTime(tdList.get(3).xpath("//td/text()").get());
                    rmList.add(rm);
                } else {
                    System.out.println("第 " + (i + 1) + " 行数据异常或无效");
                }
            }
        }else{
            System.out.println("最近比赛表表，列异常");
        }
        return rmList;
    }

}
