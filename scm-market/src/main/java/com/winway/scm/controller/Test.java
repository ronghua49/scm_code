package com.winway.scm.controller;

import com.hotent.base.controller.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test extends BaseController {
    @Value("${scan.url}")
    private String url;

    @GetMapping("/test")
    @ApiOperation(value="集中发货商品表数据列表", httpMethod = "GET", notes = "获取集中发货商品表列表")
    public String  test(){
       return  url;
    }



}
