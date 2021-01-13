package com.lpp.kiven.util;

public class ResultMsg {
	
	public final static String SUCCESS="1";//成功枚举
	public final static String FAIL="0";//失败枚举
	
	private String msg;//返回信息
	private String url;//返回点击确认后跳转地址
	private String status;//返回错误码
	private Object data;//返回对象

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}


}
