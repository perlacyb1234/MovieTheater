package com.stylefeng.guns.rest.api;

import com.stylefeng.guns.rest.vo.user.UserVo;

/**
 * Created by Cyb
 * Date 2019/4/24/024  Time 9:57
 */
public interface UserApi {
    boolean isUsernameExist(String username);

    boolean addUser(String username, String password, String email, String phone, String address);

    String login(String username, String password);

    UserVo getUserByUsername(String username);

    UserVo updateUserById(int uuid, String nickname, String email, String phone, String sex, String birthday, String lifeState, String biography, String address);
}
