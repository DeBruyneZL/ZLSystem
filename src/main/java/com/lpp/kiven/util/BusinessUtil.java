package com.lpp.kiven.util;


import com.lpp.kiven.config.log.Log4j2Logger;
import com.lpp.kiven.config.log.model.CasJSON;
import com.lpp.kiven.config.log.model.LoginMsgJSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BusinessUtil {
	//从session中获取登录用户。
	public static String getLoginUserName(HttpServletRequest request ) {
		 HttpSession session=request.getSession();
	        CasJSON cj = new CasJSON();
	        cj = (CasJSON) session.getAttribute("CAS");
	        if (cj == null) {
	            Log4j2Logger.error("用户未登录！");
	            return "root";
	        }
	        LoginMsgJSON msg = cj.getMsg();
	        String createNo = msg.getCreateNo();
	        if (StringUtil.isNullOrEmpty(createNo)) {
	            Log4j2Logger.error("用户未登录！");
	            return "root";
	        }
	        return createNo;
	}
}
