package com.example.yygh.hosp.controller.api;

import com.example.yygh.exception.YyghException;
import com.example.yygh.helper.HttpRequestHelper;
import com.example.yygh.hosp.service.DepartmentService;
import com.example.yygh.hosp.service.HospitalService;
import com.example.yygh.hosp.service.HospitalSetService;
import com.example.yygh.hosp.service.ScheduleService;
import com.example.yygh.result.Result;
import com.example.yygh.result.ResultCodeEnum;
import com.example.yygh.utils.MD5;
import com.exmaple.yygh.model.hosp.Department;
import com.exmaple.yygh.model.hosp.Hospital;
import com.exmaple.yygh.model.hosp.Schedule;
import com.exmaple.yygh.vo.hosp.DepartmentQueryVo;
import com.exmaple.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/hosp")
public class ApiController {

    @Resource
    private HospitalService hospitalService;
    @Resource
    private HospitalSetService hospitalSetService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private ScheduleService scheduleService;


    //删除科室接口
    @PostMapping("schedule/remove")
    public Result removeSchedule(HttpServletRequest request) {
        //获取传递过来的科室消息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //医院编号 和 科室编号
        String hoscode = (String)paramMap.get("hoscode");
        String hosScheduleId = (String)paramMap.get("hosScheduleId");

        scheduleService.remove(hoscode, hosScheduleId);
        return Result.ok();
    }

    //查询排班
    @PostMapping("schedule/list")
    public Result schedule(HttpServletRequest request) {
        //获取传递过来的科室消息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //医院编号
        String hoscode = (String)paramMap.get("hoscode");
        //科室编号
        String depcode = (String)paramMap.get("depcode");

        //当前页和每页条数
        int page = StringUtils.isEmpty(paramMap.get("page")) ? 1 : Integer.parseInt((String)paramMap.get("page"));
        int limit = StringUtils.isEmpty(paramMap.get("limit")) ? 10 : Integer.parseInt((String)paramMap.get("limit"));

        ScheduleQueryVo scheduleQueryVo = new ScheduleQueryVo();
        scheduleQueryVo.setHoscode(hoscode);
        scheduleQueryVo.setDepcode(depcode);
        Page<Schedule> pageModel = scheduleService.findPageSchedule(page, limit, scheduleQueryVo);
        return Result.ok(pageModel);
    }

    //上传排班接口
    @PostMapping("saveSchedule")
    public Result saveSchedule(HttpServletRequest request){
        //获取传递过来的科室消息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //1 参数校验 获取医院系统传递过来的签名，进行MD5加密
        String hosSign = (String)paramMap.get("sign");
        //2 根据传递过来的医院编码，查询数据库，查询签名
        String hoscode = (String)paramMap.get("hoscode");
        String signKey = hospitalSetService.getSignKey(hoscode);
        //3 把数据库查询签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(signKey);
        //4 判断签名是否一致
        if(! hosSign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }

        //调用service
        scheduleService.save(paramMap);
        return Result.ok();
    }

    //删除科室接口
    @PostMapping("department/remove")
    public Result removeDepartment(HttpServletRequest request) {
        //获取传递过来的科室消息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //医院编号 和 科室编号
        String hoscode = (String)paramMap.get("hoscode");
        String depcode = (String)paramMap.get("depcode");

        departmentService.remove(hoscode, depcode);
        return Result.ok();
    }


    //查询科室接口
    @PostMapping("department/list")
    public Result fingDepartment(HttpServletRequest request){
        //获取传递过来的科室消息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //医院编号
        String hoscode = (String)paramMap.get("hoscode");

        //当前页和每页条数
        int page = StringUtils.isEmpty(paramMap.get("page")) ? 1 : Integer.parseInt((String)paramMap.get("page"));
        int limit = StringUtils.isEmpty(paramMap.get("limit")) ? 10 : Integer.parseInt((String)paramMap.get("limit"));

        DepartmentQueryVo departmentQueryVo = new DepartmentQueryVo();
        departmentQueryVo.setHoscode(hoscode);
        Page<Department> pageModel = departmentService.findPageDepartment(page, limit, departmentQueryVo);
        return Result.ok(pageModel);
    }


    //上传科室接口
    @PostMapping("saveDepartment")
    public Result saveDepartment(HttpServletRequest request){
        //获取传递过来的科室消息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //1 参数校验 获取医院系统传递过来的签名，进行MD5加密
        String hosSign = (String)paramMap.get("sign");
        //2 根据传递过来的医院编码，查询数据库，查询签名
        String hoscode = (String)paramMap.get("hoscode");
        String signKey = hospitalSetService.getSignKey(hoscode);
        //3 把数据库查询签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(signKey);
        //4 判断签名是否一致
        if(! hosSign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }

        //调用service
        departmentService.save(paramMap);
        return Result.ok();
    }


    //查询医院接口
    @PostMapping("hospital/show")
    public Result getHospital(HttpServletRequest request){
        //获取传递过来的院消息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //1 参数校验 获取医院系统传递过来的签名，进行MD5加密
        String hosSign = (String)paramMap.get("sign");
        //2 根据传递过来的医院编码，查询数据库，查询签名
        String hoscode = (String)paramMap.get("hoscode");
        String signKey = hospitalSetService.getSignKey(hoscode);
        //3 把数据库查询签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(signKey);
        //4 判断签名是否一致
        if(! hosSign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }

        //调用service方法实现根据医院标号查询
        Hospital hospital = hospitalService.getByHoscode(hoscode);
        return Result.ok(hospital);
    }


    //上传医院接口
    @PostMapping("saveHospital")
    public Result saveHospital(HttpServletRequest request) {
        //获取传递过来的院消息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //1 参数校验 获取医院系统传递过来的签名，进行MD5加密
        String hosSign = (String)paramMap.get("sign");
        //2 根据传递过来的医院编码，查询数据库，查询签名
        String hoscode = (String)paramMap.get("hoscode");
        String signKey = hospitalSetService.getSignKey(hoscode);
        //3 把数据库查询签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(signKey);
        //4 判断签名是否一致
        if(! hosSign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }

        //传输过程中“+” 转换成了“ ”，因此我们要转化回来
        String logoData = (String) paramMap.get("logoData");
        logoData = logoData.replaceAll(" ", "+");
        paramMap.put("logoData",logoData);

        //调用service方法
        hospitalService.save(paramMap);
        return Result.ok();
    }
}
