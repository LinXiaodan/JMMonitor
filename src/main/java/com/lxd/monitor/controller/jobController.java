package com.lxd.monitor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class jobController {
    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }
}
