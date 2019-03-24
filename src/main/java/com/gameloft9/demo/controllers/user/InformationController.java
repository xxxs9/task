package com.gameloft9.demo.controllers.user;

import com.gameloft9.demo.dataaccess.model.user.UserInformation;
import com.gameloft9.demo.mgrframework.annotation.BizOperLog;
import com.gameloft9.demo.mgrframework.beans.constant.OperType;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.user.InformtionService;
import com.gameloft9.demo.service.beans.system.InformationAddRequest;
import com.gameloft9.demo.service.beans.system.SysUserResponse;
import com.gameloft9.demo.service.beans.system.UserAddRequest;
import com.gameloft9.demo.service.beans.system.UserUpdateRequest;
import com.gameloft9.demo.utils.Constants;
import com.gameloft9.demo.utils.FileUtil;
import com.gameloft9.demo.utils.PropertyUtil;
import com.gameloft9.demo.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 资讯功能
 */
@Slf4j
@Service
@RequestMapping("/common")
public class InformationController {
    @Autowired
    InformtionService service;
    @Autowired
    HttpServletRequest servlet;

    /**
     * 获取所有用户列表
     * @param page 页序
     * @param limit 分页大小
     * */
    @RequestMapping(value = "/informationList.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getUserList(String page, String limit, String loginName, String informationTitle, String isTop){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new PageResultBean<Collection<UserInformation>>(service.getAll(page,limit,loginName,informationTitle,isTop),service.countGetAll(loginName,informationTitle,isTop));
    }

    /**
     * 删除用户
     * */
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "删除资讯")
    public IResult deleteUser(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(service.deleteById(id));
    }

    /**
     * 资讯
     * */
    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD,memo = "添加资讯")
    public IResult addInformation(@RequestBody InformationAddRequest request){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<String>(service.addInformation(request));
    }


    /**
     * 上传封面
     * @param file 上传文件
     *
     */
    @RequestMapping(value = "/upload.do", method = RequestMethod.POST)
    @ResponseBody
    public Map upload(MultipartFile file) throws Exception {
        //设置上传目录
        String folderPath=servlet.getServletContext().getRealPath("\\informationImg");
        //如果文件夹不存在则新建文件夹
        FileUtil.newFolder(folderPath);
        //加密文件名
        String fName= UUIDUtil.getUUID()+file.getOriginalFilename();
        //创建文件
        File saveFile = new File(folderPath + "\\" + fName);
        file.transferTo(saveFile);
        //校验文件是否存在
        HashMap<String, Object> rs = new HashMap<>();
        if (saveFile.exists()){
            //文件存在
            //返回结果至前台
            rs.put("code", "0000");
            rs.put("msg", "上传成功");
            //只要截取目录下的相对路径即可
            rs.put("data", saveFile.toString().substring(saveFile.toString().indexOf("informationImg")-1));
        }else{
            rs.put("code", "500");
            rs.put("msg", "上传失败");
            rs.put("data", null);
        }
       return rs;

    }

    /**
     * 获取资讯
     * */
    @RequestMapping(value = "/get.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getUser(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<UserInformation>(service.getById(id));
    }
    /**
     * 更新用户
     * */
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "更新资讯")
    public IResult updateUser(@RequestBody @Valid UserInformation informationAddRequest){//传递了数组，前台放在payload里面了，后台通过@RequestBody获取
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(service.updateInformation(informationAddRequest));
    }

}
