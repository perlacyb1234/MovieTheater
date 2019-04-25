package com.stylefeng.guns.rest.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.api.UserApi;
import com.stylefeng.guns.rest.persistence.dao.MtimeUserTMapper;
import com.stylefeng.guns.rest.persistence.model.MtimeUserT;
import com.stylefeng.guns.rest.vo.user.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by Cyb
 * Date 2019/4/24/024  Time 9:58
 */
@Service(interfaceClass = UserApi.class)
@Component
public class UserApiImpl implements UserApi {

    @Autowired
    MtimeUserTMapper userMapper;

    @Override
    public boolean isUsernameExist(String username) {
        EntityWrapper<MtimeUserT> entityWrapper = new EntityWrapper<>();
        entityWrapper.where("user_name = {0}",username);
        List<MtimeUserT> userTList = userMapper.selectList(entityWrapper);
        if (userTList.size() != 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean addUser(String username, String password, String email, String phone, String address) {
        //暂存用MD5加密，缺少加盐算法
        MtimeUserT userT = new MtimeUserT();
        userT.setUserName(username);
        String pwd = MD5Util.encrypt(password);
        userT.setUserPwd(pwd);
        userT.setEmail(email);
        userT.setUserPhone(phone);
        userT.setAddress(address);
        int insert = userMapper.insert(userT);
        return insert == 1;
    }

    @Override
    public String login(String username, String password) {
        MtimeUserT userT = new MtimeUserT();
        userT.setUserName(username);
        String pwd = MD5Util.encrypt(password);
        userT.setUserPwd(pwd);
        MtimeUserT mtimeUserT = userMapper.selectOne(userT);
        if (mtimeUserT != null){
            return username;
        }
        return null;
    }

    @Override
    public UserVo updateUserById(int uuid, String nickname, String email, String phone, String sex,
                                 String birthday, String lifeState, String biography, String address) {
        MtimeUserT userT = new MtimeUserT();
        userT.setUuid(uuid);
        userT.setNickName(nickname);
        userT.setEmail(email);
        userT.setUserPhone(phone);
        if (sex != null){
            userT.setUserSex(Integer.valueOf(sex));
        }
        userT.setBirthday(birthday);
        if (lifeState != null){
            userT.setLifeState(Integer.valueOf(lifeState));
        }
        userT.setBiography(biography);
        userT.setAddress(address);
        int update = userMapper.updateById(userT);
        if (update==1) {
            MtimeUserT userT1 = userMapper.selectOne(userT);
            UserVo userVo = getUserVoFromUserT(userT1);
            return userVo;
        }
        return null;
    }

    private UserVo getUserVoFromUserT(MtimeUserT userT) {
        Date beginTime = userT.getBeginTime();
        long createTime = 0;
        //判空防止nullpoint
        if (beginTime != null){
            createTime = beginTime.getTime();
        }
        Date userTUpdateTime = userT.getUpdateTime();
        long updateTime = 0;
        if (userTUpdateTime != null){
            updateTime = userTUpdateTime.getTime();
        }
        UserVo userVo = new UserVo(userT.getUuid(), userT.getUserName(), userT.getNickName(), userT.getEmail(),
                userT.getUserPhone(), String.valueOf(userT.getUserSex()), userT.getBirthday(), String.valueOf(userT.getLifeState()),
                userT.getBiography(), userT.getAddress(), userT.getHeadUrl(), createTime, updateTime);
        return userVo;
    }

    @Override
    public UserVo getUserByUsername(String username) {
        MtimeUserT mtimeUserT = new MtimeUserT();
        mtimeUserT.setUserName(username);
        MtimeUserT userT = userMapper.selectOne(mtimeUserT);
        /*Date beginTime = userT.getBeginTime();
        long createTime = 0;
        //判空防止nullpoint
        if (beginTime != null){
            createTime = beginTime.getTime();
        }
        Date userTUpdateTime = userT.getUpdateTime();
        long updateTime = 0;
        if (userTUpdateTime != null){
            updateTime = userTUpdateTime.getTime();
        }
        //封装到UserVo
        UserVo userVo = new UserVo(userT.getUuid(), userT.getUserName(), userT.getNickName(), userT.getEmail(),
                userT.getUserPhone(), userT.getUserSex(), userT.getBirthday(), userT.getLifeState(),
                userT.getBiography(), userT.getAddress(), userT.getHeadUrl(), createTime, updateTime);
       */
        UserVo userVo = getUserVoFromUserT(userT);
        return userVo;
    }
}
