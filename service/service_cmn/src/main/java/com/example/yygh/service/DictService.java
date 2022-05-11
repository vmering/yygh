package com.example.yygh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exmaple.yygh.model.cmn.Dict;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DictService extends IService<Dict> {
    List<Dict> findChildData(Long id);

    void exportDictData(HttpServletResponse response);

    void importData(MultipartFile file);

    String getDictName(String DictCode, String value);

    List<Dict> findByDictCode(String dictCode);
}
