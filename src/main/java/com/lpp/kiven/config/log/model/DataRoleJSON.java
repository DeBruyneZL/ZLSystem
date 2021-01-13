package com.lpp.kiven.config.log.model;

import net.sf.json.JSONObject;

import java.io.Serializable;

public class DataRoleJSON  implements Serializable{

	/** 功能URL */
	private String action;

	/** 数据过滤SQL */
	private String filterSql;

	/** 数据过滤顺序 */
	private Integer filterOrder;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getFilterSql() {
		return filterSql;
	}

	public void setFilterSql(String filterSql) {
		this.filterSql = filterSql;
	}

	public Integer getFilterOrder() {
		return filterOrder;
	}

	public void setFilterOrder(Integer filterOrder) {
		this.filterOrder = filterOrder;
	}

	public String toString() {
		String rt = "";
		rt = "{\"action\":\"" + this.action + "\",\"filterSql\":\""
				+ this.filterSql + "\",\"filterOrder\":\"" + this.filterOrder
				+ "\"}";
		return rt;
	}

	/**
	 * 从一个JSONObject解析出DataRoleJSON对象.
	 * 
	 * @param jo
	 * @return
	 */
	public DataRoleJSON analyseJSON(JSONObject jo) {
		DataRoleJSON json = new DataRoleJSON();
		json.setAction(jo.getString("action"));
		json.setFilterSql(jo.getString("filterSql"));
		json.setFilterOrder(Integer.parseInt(jo.getString("filterOrder")));
		return json;
	}

}
