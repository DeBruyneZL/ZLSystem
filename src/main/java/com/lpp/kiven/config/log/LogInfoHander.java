package com.lpp.kiven.config.log;

import com.lpp.kiven.config.log.model.CasJSON;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Random;


/**
 * <p>Title: 保留当前请求会话		</p>
 * <p>Description: 	</p>
 * <p>Copyright: 	Copyright (c) 2017</p>
 * <p>Company: 		SI-TECH </p>
 * @date 2017-11-7 下午5:28:59 
 * @author 		gt
 * @version 		1.0
 */
public class LogInfoHander {
	/**
	 * 记录当前请求线程的日志信息
	 */
	private final static ThreadLocal<ReportLogInfo> loginfo = new ThreadLocal<ReportLogInfo>();
	
	/**
	 * 记录当前请求线程的流水号
	 */
	private static final ThreadLocal<String> requestSeq = new ThreadLocal<String>();
	
	/**
	 * 当前线程中保存用户的cas信息
	 */
	private static final ThreadLocal<CasJSON> casInfo=new ThreadLocal<CasJSON>();

	//并不是每一个请求，web容器都会使用新的线程来处理,有可能是复用没被回收的老线程(比如tomcat默认使用线程池，线程池中的线程是可以被复用的）所以保证每次都用新的值覆盖线程变量；
	public static void initLogInfo(HttpServletRequest request){
		loginfo.set(new ReportLogInfo(getRequestSeq()));
		requestSeq.set(getLogid());//线程池里面的一个线程可能被不同请求重复使用，导致id是一样的，所以初始化要重新赋值
		casInfo.set((CasJSON)request.getSession().getAttribute("CAS"));
	}
	
	public static ReportLogInfo getLogInfo(){
		if( null == loginfo.get() ){
			loginfo.set(new ReportLogInfo(getRequestSeq()));
		}
		return loginfo.get();
	}
	
	public static CasJSON getCAS(){
	    return casInfo.get();
	}

	/**
	 * 获取当前线程日志ID，19位Long值。
	 * 如果createAble == true 或者 线程变量没有记录logID，那么系统会重新生成一个logID，并且保存到
	 * 线程变量中。否则系统会沿用当前线程ID。仅仅在程序入口，例如http请求入口filter中、定时任务启动
	 * 的时候重新设置logID，其他时候，都应该沿用既有的logid。
	 */
	public static String getRequestSeq(){
		if( null == requestSeq.get() ){
			requestSeq.set(getLogid());
		}
		return requestSeq.get();
	}
	
	/**
	 * 生成ID，时间yyyyMMddHHmmssSSS加上3位随机数
	 * 
	 * @return
	 */
	private static String getLogid()
	{
		Random r = new Random();
		String rs = StringUtils.leftPad(r.nextInt(999) + "", 3, "0");
		return getCurDateStr("") + rs;
	}

	
	/**
	 * 获取指定格式的当前时间字符串
	 * @param pattern
	 * @return
	 */
	private static String getCurDateStr(String pattern)
	{
		String dateStr = null;
		try
		{
			if(StringUtils.isNotBlank(pattern))
			{
				dateStr = DateFormatUtils.format(new Date(), pattern);
			}
			else
			{
				dateStr = DateFormatUtils.format(new Date(),"yyyyMMddHHmmssSSS");
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return dateStr;
	}
}
