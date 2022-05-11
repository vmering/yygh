package com.example.yygh.hosp.controller.api;

import com.example.yygh.hosp.service.DepartmentService;
import com.example.yygh.hosp.service.HospitalService;
import com.example.yygh.result.Result;
import com.exmaple.yygh.model.hosp.Hospital;
import com.exmaple.yygh.model.hosp.Schedule;
import com.exmaple.yygh.vo.hosp.DepartmentVo;
import com.exmaple.yygh.vo.hosp.HospitalQueryVo;
import com.exmaple.yygh.vo.hosp.ScheduleOrderVo;
import com.exmaple.yygh.vo.order.SignInfoVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hosp/hospital")
public class HospitalApiController {

    @Resource
    private DepartmentService departmentService;
    @Resource
    private HospitalService hospitalService;

    //获取分页列表
    @GetMapping("{page}/{limit}")
    public Result findHospList(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit,
                        HospitalQueryVo hospitalQueryVo) {
        //显示上线的医院
        //hospitalQueryVo.setStatus(1);
        Page<Hospital> pageModel = hospitalService.selectPage(page, limit, hospitalQueryVo);
        return Result.ok(pageModel);
    }

    //根据医院名称获取医院列表
    @GetMapping("findByHosname/{hosname}")
    public Result findByHosname(@PathVariable("hosname") String hosname) {
        List<Hospital> list = hospitalService.findByHosname(hosname);
        return Result.ok(list);
    }

    //根据医院编号获取科室
    @GetMapping("department/{hoscode}")
    public Result index(@PathVariable("hoscode") String hoscode) {
        List<DepartmentVo> list = departmentService.findDeptTree(hoscode);
        return Result.ok(list);
    }

    //根据医院编号获取医院预约挂号详情
    @GetMapping("findHospDetail/{hoscode}")
    public Result item(@PathVariable String hoscode) {
        Map<String, Object> map = hospitalService.item(hoscode);
        return Result.ok(map);
    }

}
