package com.gameloft9.demo.webmagic;

/**
 * 爬取数据处理方法
 */
public class ReptileDataUtil {

    /**
     * serverName 转换成 serverId
     * @param serverName
     * @return
     */
    public static String toServerId(String serverName){

        StringBuffer str = new StringBuffer();
        String ser = serverName.substring(0,2);
        String id = serverName.substring(2,serverName.length());
        switch (ser){
            case "电信":str.append("dx");break;
            case "网通":str.append("wt");break;
            case "教育":str.append("jy");break;
            case "全网":str.append("qw");break;
            default: break;
        }
        if (id.length() == 2){
            str.append("1");
            id = id.substring(1,2);
        }
        if (id.length() == 1){
            switch (id){
                case "一": str.append("1");break;
                case "二": str.append("2");break;
                case "三": str.append("3");break;
                case "四": str.append("4");break;
                case "五": str.append("5");break;
                case "六": str.append("6");break;
                case "七": str.append("7");break;
                case "八": str.append("8");break;
                case "九": str.append("9");break;
                default:break;
            }
        }
        return str.toString();
    }
}
