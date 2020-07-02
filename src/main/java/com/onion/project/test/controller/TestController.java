package com.onion.project.test.controller;


import cn.hutool.core.util.ReUtil;
import com.onion.common.tool.Res;
import com.onion.project.test.entity.Test;
import com.onion.project.test.service.ITestService;
import com.onion.project.test.service.impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yu
 * @since 2020-07-02
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ITestService testService;

    @GetMapping("getTestList")
    public Res<Test> getTestList() {
        List<Test> list = testService.list();
        return Res.ok.data(list);
    }

}
