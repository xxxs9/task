<%@ page language="java" contentType="text/html; charset=UTF-8"
         import="com.baidu.ueditor.ActionEnter"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%

    request.setCharacterEncoding("utf-8");
    response.setHeader("Content-Type", "text/html");

    String rootPath = application.getRealPath("/");
    String saveRootPath = rootPath;
    saveRootPath = "D:/image/";//配置本地文件保存路径前缀 修改config.json的imageUrlPrefix字段为http://img.tiyuanku.cn/
    out.write(new ActionEnter(request, saveRootPath, rootPath+"ueditor\\jsp\\config.json").exec());

%>