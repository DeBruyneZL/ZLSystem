package com.lpp.kiven.config.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

/**
 * <p>Title: 统一日志输出类	</p>
 * <p>Description: 	</p>
 * <p>Copyright: 	Copyright (c) 2017</p>
 * <p>Company: 		SI-TECH </p>
 * @date 2017-7-21 下午3:32:25 
 * @author 		gt
 * @version 		1.0
 */
public class Log4j2Logger {
	
	private static Logger info;
	private static Logger error;
	private static Logger debug;
	private static Logger dao_info;

	
	static{
		debug = LoggerFactory.getLogger("debug");
		info = LoggerFactory.getLogger("info");
		error = LoggerFactory.getLogger("error");
		//打印db信息
		dao_info = LoggerFactory.getLogger("dao_info");

	}
	//普通
	public static void debug(String _info){
		debug.debug(getCasInfo()+"|"+LogInfoHander.getRequestSeq()+"|"+_info);
	}
	public static void info(String _info){
		info.info(getCasInfo()+"|"+LogInfoHander.getRequestSeq()+"|"+_info);
	}
	public static void error(Exception e){
		error("",e);
	}
	public static void error(String info){
		error(getCasInfo()+"|"+LogInfoHander.getRequestSeq()+"|"+info,null);
	}
	public static void error(String info,Exception e){
		error.error(getCasInfo()+"|"+LogInfoHander.getRequestSeq()+"|"+info + (e==null?"": "|" + getExceptionTrace(e)));
	}
	//chg分发
	public static void daoinfo(String _info){
		dao_info.info(getCasInfo()+"|"+LogInfoHander.getRequestSeq()+"|"+_info);
	}
	
	private static String getCasInfo(){
		String casInfo="";
		if (null!=LogInfoHander.getCAS()) 
		{
			casInfo="操作者ID:"+LogInfoHander.getCAS().getMsg().getLoginNo()+",姓名:"+LogInfoHander.getCAS().getMsg().getLoginName();
		}
		return casInfo;
	}
    /**
     * @param e: debug级别的异常信息
	 */
    public static String getExceptionTrace(Exception e) {
        String print = null;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        PrintWriter wrt = new PrintWriter(bout);
        e.printStackTrace(wrt);
        wrt.close();
        print = bout.toString();
        return print;
    }
}
