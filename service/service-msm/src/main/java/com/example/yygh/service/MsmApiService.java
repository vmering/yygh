package com.example.yygh.service;

public interface MsmApiService {
    //发送手机验证码
    boolean send(String phone, String code);
}
