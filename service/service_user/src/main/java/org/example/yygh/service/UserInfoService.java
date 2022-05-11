package org.example.yygh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exmaple.yygh.model.user.UserInfo;
import com.exmaple.yygh.vo.user.LoginVo;
import com.exmaple.yygh.vo.user.UserAuthVo;

import java.util.Map;

public interface UserInfoService extends IService<UserInfo> {
    Map<String, Object> login(LoginVo loginVo);

    UserInfo selectWxInfoOpenId(String openid);

    void userAuth(Long userId, UserAuthVo userAuthVo);
}