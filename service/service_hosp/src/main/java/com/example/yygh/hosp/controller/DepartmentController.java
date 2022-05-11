package com.example.yygh.hosp.controller;

import com.example.yygh.hosp.service.DepartmentService;
import com.example.yygh.result.Result;
import com.exmaple.yygh.vo.hosp.DepartmentVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/hosp/department")
//@CrossOrigin
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    //根据医院编号，查询医院所有科室列表
    @GetMapping("/getDeptList/{hoscode}")
    public Result getDeptList(@PathVariable("hoscode") String hoscode) {
        List<DepartmentVo> list = departmentService.findDeptTree(hoscode);
        return Result.ok(list);
    }
}