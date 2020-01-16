package com.incarcloud.methane.monitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    /**
     * 静态页面处理程序
     * 默认显示index页面
     * @return 返回HTML静态页面
     */
    @RequestMapping("/")
    public String forward() {
        return "forward:/index.html";
    }
}
