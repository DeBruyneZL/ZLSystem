package com.lpp.kiven.config.log.model;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class CasJSON implements Serializable{

	/** 用户信息 */
	private LoginMsgJSON msg;

	/** 权限树 */
	private ArrayList<FuncJSON> anthority;

	/** 展现管理配置 */
	private ArrayList<FuncJSON> display;

	/** 收藏夹管理配置 */
	private ArrayList<FuncJSON> favorite;

	/** 数据过滤配置 */
	private ArrayList<DataRoleJSON> dataRole;

	public LoginMsgJSON getMsg() {
		return msg;
	}

	public void setMsg(LoginMsgJSON msg) {
		this.msg = msg;
	}

	public ArrayList<FuncJSON> getAnthority() {
		return anthority;
	}

	public void setAnthority(ArrayList<FuncJSON> anthority) {
		this.anthority = anthority;
	}

	public ArrayList<FuncJSON> getDisplay() {
		return display;
	}

	public void setDisplay(ArrayList<FuncJSON> display) {
		this.display = display;
	}

	public ArrayList<FuncJSON> getFavorite() {
		return favorite;
	}

	public void setFavorite(ArrayList<FuncJSON> favorite) {
		this.favorite = favorite;
	}

	public ArrayList<DataRoleJSON> getDataRole() {
		return dataRole;
	}

	public void setDataRole(ArrayList<DataRoleJSON> dataRole) {
		this.dataRole = dataRole;
	}

	/**
	 * 从一个json字符串解析出CasJSON对象.
	 * 
	 * @param json
	 * @return
	 */
	public CasJSON analyseJSON(String json) {
		CasJSON cj = new CasJSON();
		JSONObject j1 = JSONObject.fromObject(json);
		JSONObject j2 = JSONObject.fromObject(j1.get("msg"));
		LoginMsgJSON lm = new LoginMsgJSON();
		lm = lm.analyseJSON(j2);
		cj.setMsg(lm);

		JSONObject j3 = null;
		JSONArray a1 = JSONArray.fromObject(j1.get("authority"));
		JSONArray a2 = JSONArray.fromObject(j1.get("display"));
		JSONArray a3 = JSONArray.fromObject(j1.get("favorite"));

		int size = 0;
		FuncJSON f = null;

		size = a1.size();
		ArrayList<FuncJSON> aList = new ArrayList<FuncJSON>();
		for (int i = 0; i < size; i++) {
			j3 = JSONObject.fromObject(a1.get(i));
			f = new FuncJSON();
			f = f.analyseJSON(j3);
			aList.add(f);
		}
		cj.setAnthority(aList);

		size = a2.size();
		ArrayList<FuncJSON> dList = new ArrayList<FuncJSON>();
		for (int i = 0; i < size; i++) {
			j3 = JSONObject.fromObject(a2.get(i));
			f = new FuncJSON();
			f = f.analyseJSON(j3);
			dList.add(f);
		}
		cj.setDisplay(dList);

		size = a3.size();
		ArrayList<FuncJSON> fList = new ArrayList<FuncJSON>();
		for (int i = 0; i < size; i++) {
			j3 = JSONObject.fromObject(a3.get(i));
			f = new FuncJSON();
			f = f.analyseJSON(j3);
			fList.add(f);
		}
		cj.setFavorite(fList);

		DataRoleJSON drj = null;
		JSONArray a4 = JSONArray.fromObject(j1.get("dataRole"));
		size = a4.size();
		ArrayList<DataRoleJSON> drjList = new ArrayList<DataRoleJSON>();
		for(int i = 0; i < size; i++) {
			j3 = JSONObject.fromObject(a4.get(i));
			drj = new DataRoleJSON();
			drj = drj.analyseJSON(j3);
			drjList.add(drj);
		}
		cj.setDataRole(drjList);
		
		return cj;
	}

}
