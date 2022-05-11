package com.example.yygh.hosp.controller;


import com.example.yygh.hosp.service.HospitalService;
import com.example.yygh.result.Result;
import com.exmaple.yygh.model.hosp.Hospital;
import com.exmaple.yygh.vo.hosp.HospitalQueryVo;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/admin/hosp/hospital")
//@CrossOrigin
public class HospitalController {

    @Resource
    private HospitalService hospitalService;

    //获取分页列表
    @GetMapping("/list/{page}/{limit}")
    public Result index(@PathVariable Integer page, @PathVariable Integer limit
            , HospitalQueryVo hospitalQueryVo) {
        Page<Hospital> pageModel = hospitalService.selectPage(page, limit, hospitalQueryVo);
        return Result.ok(pageModel);
    }

    //更新医院上线状态
    @GetMapping("updateHospStatus/{id}/{status}")
    public Result updateHospStatus(@PathVariable String id, @PathVariable Integer status) {
        hospitalService.updateStatus(id, status);
        return Result.ok();
    }

    //医院详情信息
    @GetMapping("showHospDetail/{id}")
    public Result showHospDetail(@PathVariable String id) {
        Map<String, Object> map = hospitalService.getHospById(id);
        return Result.ok(map);
    }
}