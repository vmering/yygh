package com.example.yygh.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.yygh.mapper.DictMapper;
import com.example.yygh.service.DictService;
import com.exmaple.yygh.model.cmn.Dict;
import com.exmaple.yygh.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;

public class DictListener extends AnalysisEventListener<DictEeVo> {

    @Resource
    private DictMapper dictMapper;
    public DictListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    //一行一行读取
    @Override
    public void invoke(DictEeVo dictEeVo, AnalysisContext analysisContext) {
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictEeVo,dict);
        dictMapper.insert(dict);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
