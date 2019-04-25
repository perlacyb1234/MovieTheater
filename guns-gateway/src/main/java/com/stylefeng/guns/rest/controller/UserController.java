package com.stylefeng.guns.rest.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.api.UserApi;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.vo.ResponseVo;
import com.stylefeng.guns.rest.vo.user.AuthVo;
import com.stylefeng.guns.rest.vo.user.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Cyb
 * Date 2019/4/24/024  Time 10:07
 */
@RestController
public class UserController {

    @Reference(interfaceClass = UserApi.class)
    UserApi userApi;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "user/register", method = RequestMethod.POST)
    public ResponseVo register(@RequestParam String username, @RequestParam String password,
                               String email, String phone, String address) {
        ResponseVo responseVo = new ResponseVo();
        boolean ret = false;
        try {
            ret = userApi.isUsernameExist(username);
        } catch (Exception e) {
            responseVo.setStatus(999);
            responseVo.setMsg("系统出现异常，请联系管理员");
            return responseVo;
        }
        if (ret) {
            responseVo.setStatus(1);
            responseVo.setMsg("用户已存在");
            return responseVo;
        }
        boolean ret2 = userApi.addUser(username, password, email, phone, address);
        if (ret2) {
            responseVo.setStatus(0);
            responseVo.setMsg("注册成功");
            return responseVo;
        }
        responseVo.setStatus(999);
        responseVo.setMsg("系统出现异常，请联系管理员");
        return responseVo;
    }

    @RequestMapping(value = "user/check", method = RequestMethod.POST)
    public ResponseVo checkUsername(@RequestParam String username) {
        boolean ret = false;
        ResponseVo responseVo = new ResponseVo();
        try {
            ret = userApi.isUsernameExist(username);
        } catch (Exception e) {
            responseVo.setStatus(999);
            responseVo.setMsg("系统出现异常，请联系管理员");
            return responseVo;
        }
        if (ret) {
            responseVo.setStatus(1);
            responseVo.setMsg("用户名已存在");
            return responseVo;
        } else {
            responseVo.setStatus(0);
            responseVo.setMsg("验证成功");
            return responseVo;
        }
    }

    @RequestMapping(value = "auth", method = RequestMethod.POST)
    public ResponseVo login(@RequestParam String username, @RequestParam String password) {
        String username1 = null;
        ResponseVo responseVo = new ResponseVo();
        try {
            username1 = userApi.login(username, password);
        } catch (Exception e) {
            responseVo.setStatus(999);
            responseVo.setMsg("系统出现异常，请联系管理员");
            return responseVo;
        }
        if (username1 == null) {
            responseVo.setStatus(1);
            responseVo.setMsg("用户名或密码错误");
            return responseVo;
        }
        //生成token
        String randomKey = jwtTokenUtil.getRandomKey();
        String token = jwtTokenUtil.generateToken(username, randomKey);
        AuthVo authVo = new AuthVo(randomKey, token);
        responseVo.setStatus(0);
        responseVo.setData(authVo);
        return responseVo;
    }

    @RequestMapping(value = "user/logout", method = RequestMethod.GET)
    //authToken在请求header中，怎么获取
    public ResponseVo logout(HttpServletRequest request) {
        ResponseVo responseVo = new ResponseVo();
        boolean exist = false;
        try {
            String header = request.getHeader("Authorization");
            String authToken = header.substring(7);
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            //目前只验证usernameFromToken是否存在数据库
            exist = userApi.isUsernameExist(username);
        } catch (Exception e) {
            responseVo.setStatus(999);
            responseVo.setMsg("系统出现异常，请联系管理员");
            return responseVo;
        }

        if (exist) {
            responseVo.setStatus(0);
            responseVo.setData(new AuthVo());
            responseVo.setMsg("成功退出");
            return responseVo;
        }
        responseVo.setStatus(1);
        responseVo.setMsg("退出失败，用户尚未登录");
        return responseVo;
    }
    @RequestMapping(value = "user/getUserInfo",method = RequestMethod.GET)
    public ResponseVo getUserInfo(HttpServletRequest request){
        ResponseVo responseVo = new ResponseVo();
        String username = null;
        boolean exist = false;
        try {
            String header = request.getHeader("Authorization");
            String authToken = header.substring(7);
            username = jwtTokenUtil.getUsernameFromToken(authToken);
            exist = userApi.isUsernameExist(username);
        } catch (Exception e) {
            responseVo.setStatus(999);
            responseVo.setMsg("系统出现异常，请联系管理员");
            return responseVo;
        }
        if (username == null || !exist){
            responseVo.setStatus(1);
            responseVo.setMsg("查询失败，用户尚未登录");
            return responseVo;
        }
        UserVo userVo = userApi.getUserByUsername(username);
        responseVo.setStatus(0);
        responseVo.setData(userVo);
        return responseVo;
    }

    @RequestMapping(value = "user/updateUserInfo",method = RequestMethod.POST)
    public ResponseVo updateUserInfo(HttpServletRequest request,@RequestParam int uuid,String nickname,
                                     String email,String phone,String sex, String birthday,String lifeState,
                                     String biography,String address){
        ResponseVo responseVo = new ResponseVo();
        UserVo userVo = null;
        String username = null;
        boolean exist = false;
        try {
            String header = request.getHeader("Authorization");
            String authToken = header.substring(7);
            username = jwtTokenUtil.getUsernameFromToken(authToken);
            exist = userApi.isUsernameExist(username);
            userVo = userApi.updateUserById(uuid,nickname,email,phone,sex,birthday,lifeState,biography,address);
        } catch (Exception e) {
            responseVo.setStatus(999);
            responseVo.setMsg("系统出现异常，请联系管理员");
            return responseVo;
        }
        if (userVo == null){
            responseVo.setStatus(1);
            responseVo.setMsg("用户信息修改失败");
            return responseVo;
        }
        responseVo.setStatus(0);
        responseVo.setData(userVo);
        return responseVo;
    }
}
