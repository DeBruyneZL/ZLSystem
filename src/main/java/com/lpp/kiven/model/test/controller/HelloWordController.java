package com.lpp.kiven.model.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zhangliang
 * @date ：Created in 2021/1/12 15:11
 * @description：
 * @modified By：
 * @version: $
 */
@Controller
public class HelloWordController {
    @ResponseBody
    @RequestMapping("/index")
    public String index() {
        return "helloword!";
    }
}
