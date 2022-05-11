package com.example.yygh.controller;


import com.example.yygh.result.Result;
import com.example.yygh.service.DictService;
import com.exmaple.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api("数据字典接口")
@RestController
@RequestMapping("/admin/cmn/dict")
//@CrossOrigin
public class DictController {

    @Resource
    private DictService dictService;

    //导入数据接口
    @PostMapping("importData")
    public Result importData(MultipartFile file) {
        dictService.importData(file);
        return Result.ok();
    }

    //导出数据接口
    @GetMapping("exportData")
    public void exportData(HttpServletResponse response){
        dictService.exportDictData(response);
    }

    //根据数据id查询子数据id
    @GetMapping("findChildData/{id}")
    public Result findChildData(@PathVariable Long id){
        List<Dict> list = dictService.findChildData(id);
        return Result.ok(list);
    }

    //根据dictcode获取下级节点
    @GetMapping(value = "/findByDictCode/{dictCode}")
    public Result<List<Dict>> findByDictCode(@PathVariable("dictCode") String dictCode) {
        List<Dict> list = dictService.findByDictCode(dictCode);
        return Result.ok(list);
    }

    //根据dictcode和value查询
    @GetMapping(value = "/getName/{DictCode}/{value}")
    public String getName(@PathVariable("DictCode") String DictCode,
            @PathVariable("value") String value) {
        return dictService.getDictName(DictCode, value);
    }

    //根据value查询
    @GetMapping(value = "/getName/{value}")
    public String getName(@PathVariable("value") String value) {
        return dictService.getDictName("", value);
    }

}
