package com.code.line.domain.controller;

import com.code.line.system.entity.SysConfig;
import com.code.line.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: syl
 * @Date: 2022/6/21 23:38
 * @Description:
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ISysConfigService configService;

    @RequestMapping("/getByid/{id}")
    public SysConfig getByid(@PathVariable("id") Long id){
        return configService.getById(id);
    }


}
