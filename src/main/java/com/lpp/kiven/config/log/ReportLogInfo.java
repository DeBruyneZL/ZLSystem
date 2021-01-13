package com.lpp.kiven.config.log;

public class ReportLogInfo {


	/** 局数据上报需要扩充字段 add */
	private String requestUrl; // 请求报文
	private String responseUrl; // 返回报文
	private String template; // 模板类型
	private String optType; // 操作类型
	private String questionStr; //问题定位
	/** 局数据上报需要扩充字段 end */
	
	
	public ReportLogInfo(String _id) {

	}
	
	/**
	 * <p>Description: 设置错误日志，带抛出异常 	</p>
	 * @param info
	 * @param e
	 */
	public void setErrorLog(String info,Exception e){

		Log4j2Logger.error(info, e);
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getResponseUrl() {
		return responseUrl;
	}

	public void setResponseUrl(String responseUrl) {
		this.responseUrl = responseUrl;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getOptType() {
		return optType;
	}

	public void setOptType(String optType) {
		this.optType = optType;
	}
	
	public String getQuestionStr() {
		return questionStr;
	}

	public void setQuestionStr(String questionStr) {
		this.questionStr = questionStr;
	}
}