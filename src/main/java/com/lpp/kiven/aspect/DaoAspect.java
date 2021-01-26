package com.lpp.kiven.aspect;

import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：zhangliang
 * @date ：Created in 2021/1/13 16:38
 * @description：dao层日志
 * @modified By：
 * @version: $
 */
@Aspect
@Configuration
public class DaoAspect {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    Gson gson=new Gson();

    //定义切入点
    @Pointcut("execution(* com.lpp.kiven.model.*.dao..*(..))")
    public void excudeService(){}

    //使用切入点
    @Around("excudeService()")
    public Object asp(ProceedingJoinPoint point) throws Throwable {
        try {

            long startTime=System.currentTimeMillis();
            Object obj=point.proceed();
            long endTime=System.currentTimeMillis();
            logger.info(point.getSignature().getDeclaringTypeName() + "." +point.getSignature().getName()+":入参:"+(point.getArgs()==null?"":gson.toJson(point.getArgs()))
                            +",返参:"+(obj==null?"":gson.toJson(obj))+",耗时:"+(endTime-startTime));
            return obj;
        } catch (Exception e) {
            throw e;
        }
    }
    //校验默认白名单方法
    public boolean checkWhiteList(String daoName){
        String[] whiteLists=new String[]{"save","update","delete","create","remove","modify","insert"};
        for(String white : whiteLists){
            if(daoName.indexOf(white)>-1) {
                return true;
            }
        }
        return false;
    }
}

