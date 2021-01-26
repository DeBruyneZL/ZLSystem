package com.lpp.kiven.model.test.service.impl;

import com.lpp.kiven.model.test.dao.MyDao;
import com.lpp.kiven.model.test.service.IMyservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：zhangliang
 * @date ：Created in 2021/1/14 15:39
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class MyserviceImpl implements IMyservice {
    @Autowired
    private MyDao myDao;
    @Override
    public String test() {
        return myDao.getName();
    }
}
