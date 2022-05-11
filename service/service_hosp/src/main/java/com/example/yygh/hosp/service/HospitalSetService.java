package com.example.yygh.hosp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exmaple.yygh.model.hosp.HospitalSet;

public interface HospitalSetService extends IService<HospitalSet> {
    String getSignKey(String hoscode);
}
