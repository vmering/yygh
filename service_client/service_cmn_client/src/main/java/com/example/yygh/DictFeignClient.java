package com.example.yygh;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-cmn")
public interface DictFeignClient {

    //根据dictcode和value查询
    @GetMapping(value = "/admin/cmn/dict/getName/{DictCode}/{value}")
    public String getName(@PathVariable("DictCode") String DictCode,
                          @PathVariable("value") String value);

    //根据value查询
    @GetMapping(value = "/admin/cmn/dict/getName/{value}")
    public String getName(@PathVariable("value") String value);
}
