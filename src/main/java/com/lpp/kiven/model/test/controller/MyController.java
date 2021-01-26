package com.lpp.kiven.model.test.controller;

import com.lpp.kiven.model.test.service.IMyservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：zhangliang
 * @date ：Created in 2021/1/12 15:28
 * @description：
 * @modified By：
 * @version: $
 */
@Controller
@RequestMapping("/my")
public class MyController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IMyservice myService;
    @ResponseBody
    @RequestMapping("/index")
    public String index(){
        logger.info("kiven 开始");
        logger.error("哈哈哈");
        logger.info("kiven 结束");
        return myService.test();

    }
}
