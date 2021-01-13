package com.lpp.kiven.config.log.model;

import net.sf.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FuncJSON  implements Serializable{

	/** 功能编码 */
	private Integer functionCode;
	/** 功能名称 */
	private String functionName;
	/** 链接地址 */
	private String funcAction;
	/** 功能描述 */
	private String descInfo;
	/** 图片描述 */
	private String icoDesc;
	/** 左编号 */
	private Integer leftId;
	/** 右编号 */
	private Integer rightId;
	/** 功能类型 */
	private Integer funcType;
	/** 所属系统URL */
	private String sysUrl;
	/** 父ID */
	private Integer parentCode;

	public Integer getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(Integer functionCode) {
		this.functionCode = functionCode;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFuncAction() {
		return funcAction;
	}

	public void setFuncAction(String funcAction) {
		this.funcAction = funcAction;
	}

	public String getDescInfo() {
		return descInfo;
	}

	public void setDescInfo(String descInfo) {
		this.descInfo = descInfo;
	}

	public String getIcoDesc() {
		return icoDesc;
	}

	public void setIcoDesc(String icoDesc) {
		this.icoDesc = icoDesc;
	}

	public Integer getLeftId() {
		return leftId;
	}

	public void setLeftId(Integer leftId) {
		this.leftId = leftId;
	}

	public Integer getRightId() {
		return rightId;
	}

	public void setRightId(Integer rightId) {
		this.rightId = rightId;
	}

	public Integer getFuncType() {
		return funcType;
	}

	public void setFuncType(Integer funcType) {
		this.funcType = funcType;
	}

	public Integer getParentCode() {
		return parentCode;
	}

	public void setParentCode(Integer parentCode) {
		this.parentCode = parentCode;
	}

	public String getSysUrl() {
		return sysUrl;
	}

	public void setSysUrl(String sysUrl) {
		this.sysUrl = sysUrl;
	}

	public String toString() {
		String rt = "";
		if(null == this.descInfo) {
			this.descInfo = "";
		}
		this.descInfo = this.descInfo.replaceAll("\r", "").replaceAll("\n", "");
		rt = "{\"function_code\":\"" + this.functionCode
				+ "\",\"function_name\":\"" + this.functionName
				+ "\",\"func_action\":\"" + this.funcAction
				+ "\",\"desc_info\":\"" + this.descInfo + "\",\"ico_desc\":\""
				+ this.icoDesc + "\",\"left_id\":\"" + this.leftId
				+ "\",\"right_id\":\"" + this.rightId + "\",\"func_type\":\""
				+ this.funcType + "\",\"sys_url\":\"" + this.sysUrl
				+ "\",\"parent_code\":\"" + this.parentCode + "\"}";
		return rt;
	}

	/**
	 * 从一个JSONObject解析出FuncJSON对象.
	 * 
	 * @param jo
	 * @return
	 */
	public FuncJSON analyseJSON(JSONObject jo) {
		FuncJSON f = new FuncJSON();
		f.setFunctionCode(Integer.parseInt(jo.getString("function_code")));
		f.setFunctionName(jo.getString("function_name"));
		f.setFuncAction(jo.getString("func_action"));
		f.setDescInfo(jo.getString("desc_info"));
		f.setIcoDesc(jo.getString("ico_desc"));
		f.setLeftId(Integer.parseInt(jo.getString("left_id")));
		f.setRightId(Integer.parseInt(jo.getString("right_id")));
		f.setFuncType(Integer.parseInt(jo.getString("func_type")));
		f.setSysUrl(jo.getString("sys_url"));
		if(null == jo.getString("parent_code")
				|| "\"null\"".equals(jo.getString("parent_code"))
				|| "".equals(jo.getString("parent_code"))) {
			f.setParentCode(-2);
		} else {
			f.setParentCode(Integer.parseInt(jo.getString("parent_code")));
		}
		return f;
	}


	/**
	 * 按照leftId排序功能列表.
	 * @param list
	 * @return
	 */
	public static ArrayList<FuncJSON> sort(ArrayList<FuncJSON> list) {
		
		if (null == list) {
			return null;
		} else if (list.size() <= 1) {
			return list;
		}
		ArrayList<FuncJSON> sorted = new ArrayList<FuncJSON>();
		HashMap<String, FuncJSON> leftIdMap = new HashMap<String, FuncJSON>();
		int size = list.size();
		int[] leftIdArray = new int[size];
		for (int i = 0; i < size; i++) {
			FuncJSON fj = list.get(i);
			leftIdMap.put("" + fj.getLeftId(), fj);
			leftIdArray[i] = fj.getLeftId().intValue();
		}
		Arrays.sort(leftIdArray);
		for (int i = 0; i < size; i++) {
			sorted.add(leftIdMap.get(leftIdArray[i] + ""));
		}
		return sorted;
	}
}
