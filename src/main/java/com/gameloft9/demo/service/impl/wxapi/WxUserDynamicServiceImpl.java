package com.gameloft9.demo.service.impl.wxapi;

import com.gameloft9.demo.dataaccess.dao.user.UserCommentMapper;
import com.gameloft9.demo.dataaccess.dao.user.UserDynamicMapper;
import com.gameloft9.demo.dataaccess.dao.user.UserFriendsMapper;
import com.gameloft9.demo.dataaccess.dao.user.WxUserMapper;
import com.gameloft9.demo.dataaccess.model.user.UserComment;
import com.gameloft9.demo.dataaccess.model.user.UserDynamic;
import com.gameloft9.demo.dataaccess.model.user.UserFriends;
import com.gameloft9.demo.dto.dynamic.DynamicDto;
import com.gameloft9.demo.dto.dynamic.UserCommentDto;
import com.gameloft9.demo.dto.dynamic.UserDynamicDto;
import com.gameloft9.demo.service.api.wxapi.WxUserDynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 好友动态全逻辑层
 */
@Service
public class WxUserDynamicServiceImpl implements WxUserDynamicService {

    @Autowired
    private UserDynamicMapper userDynamicMapper;
    @Autowired
    private UserCommentMapper userCommentMapper;
    @Autowired
    private WxUserMapper wxUserMapper;
    @Autowired
    private UserFriendsMapper userFriendsMapper;


    /**
     * 获取好友动态
     * @param uuid
     * @return
     */
    @Override
    public List<DynamicDto> findFriendDynamic(String uuid) {
        List<DynamicDto> dtoList = new ArrayList<>();
        List<UserDynamicDto> fudList = userDynamicMapper.getFriendDynamic(uuid);
        //查看是否有朋友圈动态
        if (fudList != null && fudList.size() > 0){
            for (int i = 0, num = fudList.size(); i < num ; i ++ ){
                DynamicDto dto = new DynamicDto();
                dto.setUserDynamic(fudList.get(i));
                dto.setUcList(userCommentMapper.queryListByDynamicId(fudList.get(i).getId()));
                dtoList.add(dto);
            }
        }
        return dtoList;
    }

    /**
     * 发送动态
     * @param uuid
     * @param content
     * @return
     */
    @Override
    public boolean pushDynamic(String uuid, String content) {
        UserDynamic ud = new UserDynamic();
        ud.setCreateTime(new Date());
        ud.setDynamicContent(content);
        ud.setUuid(uuid);
        ud.setIsDel(0);
        ud.setLoginName(wxUserMapper.queryWxUserByUuid(uuid).getNickname());
        userDynamicMapper.insert(ud);
        return true;
    }

    /**
     * 提交评论
     * @param dynamicId
     * @param uuid
     * @param content
     * @return
     */
    @Override
    public List<UserCommentDto> pushComment(Integer dynamicId, String uuid, String content) {
        //先校验动态是否存在
        UserDynamic ud = new UserDynamic();
        ud.setId(dynamicId);
        ud = userDynamicMapper.selectByPrimaryKey(ud);
        //判断动态是否存在，并且是否是正常状态
        if (ud != null && ud.getIsDel() == 0){
            UserFriends uf = userFriendsMapper.queryFriendByFirstSecondId(uuid,ud.getUuid());
            //判断是不是自己或者好友评论
            if ((uf != null && uf.getFriendStatus() == 1)
                    || uuid.equals(ud.getUuid())){
                UserComment uc = new UserComment();
                uc.setCommentDetails(content);
                uc.setCommentName(wxUserMapper.queryWxUserByUuid(uuid).getNickname());
                uc.setCreateTime(new Date());
                uc.setDynamicId(dynamicId);
                uc.setUuid(uuid);
                uc.setIsDel(0);
                userCommentMapper.insert(uc);
                return userCommentMapper.queryListByDynamicId(dynamicId);
            }
        }
        return new ArrayList<UserCommentDto>();
    }

    /**
     * 删除动态
     * @param uuid
     * @param dynamicId
     * @return
     */
    @Override
    public boolean delDynamic(String uuid, Integer dynamicId) {
        UserDynamic ud = new UserDynamic();
        ud.setId(dynamicId);
        ud = userDynamicMapper.selectByPrimaryKey(ud);
        //校验是否有该动态
        if (ud != null){
            //只删除动态 - 动态下的评论不会删除，便于恢复删除的动态
            ud.setIsDel(1);
            userDynamicMapper.updateByPrimaryKey(ud);
            return true;
        }
        System.out.println("删除动态异常");
        return false;
    }

    /**
     * 删除评论
     * @param uuid
     * @param commentId
     * @return
     */
    @Override
    public boolean delComment(String uuid, Integer commentId) {
        //先获取评论条目
        UserComment uc = new UserComment();
        uc.setId(commentId);
        uc = userCommentMapper.selectByPrimaryKey(uc);
        if (uc != null){
            uc.setIsDel(1);
            userCommentMapper.updateByPrimaryKey(uc);
            return true;
        }
        System.out.println("删除评论异常");
        return false;
    }
}
