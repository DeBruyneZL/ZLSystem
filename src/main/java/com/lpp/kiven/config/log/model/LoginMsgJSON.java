package com.lpp.kiven.config.log.model;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LoginMsgJSON  implements Serializable{
	
	private static Logger logger = Logger.getLogger(LoginMsgJSON.class);

	private Integer loginId;
	private String loginNo;
	private String loginName;
	private String loginPasswd;
	private Date pwdSetDate;
	private Date pwdInvalidDate;
	private String pwdHis;
	private Date pwdUpdateDate;
	private Integer isModify;
	private Integer noticeTimes;
	private Integer tryTimes;
	private String loginIp;
	private Integer ipbindFlag;
	private Integer loginFlag;
	private Date lastDate;
	private String contactPhone;
	private String email;
	private Integer orgId;
	private Integer loginType;
	private Integer validFlag;
	private Date validDate;
	private String createNo;
	private Date createTime;
	private String updateNo;
	private Date updateTime;
	private String allCreateNo;
	private Integer pwdValidDays;
	private List<Integer> roleId;
	
	public List<Integer> getRoleId() {
		return roleId;
	}

	public void setRoleId(List<Integer> roleId) {
		this.roleId = roleId;
	}

	public Integer getLoginId() {
		return loginId;
	}

	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}

	public String getLoginNo() {
		return loginNo;
	}

	public void setLoginNo(String loginNo) {
		this.loginNo = loginNo;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPasswd() {
		return loginPasswd;
	}

	public void setLoginPasswd(String loginPasswd) {
		this.loginPasswd = loginPasswd;
	}

	public Date getPwdSetDate() {
		return pwdSetDate;
	}

	public void setPwdSetDate(Date pwdSetDate) {
		this.pwdSetDate = pwdSetDate;
	}

	public Date getPwdInvalidDate() {
		return pwdInvalidDate;
	}

	public void setPwdInvalidDate(Date pwdInvalidDate) {
		this.pwdInvalidDate = pwdInvalidDate;
	}

	public String getPwdHis() {
		return pwdHis;
	}

	public void setPwdHis(String pwdHis) {
		this.pwdHis = pwdHis;
	}

	public Date getPwdUpdateDate() {
		return pwdUpdateDate;
	}

	public void setPwdUpdateDate(Date pwdUpdateDate) {
		this.pwdUpdateDate = pwdUpdateDate;
	}

	public Integer getIsModify() {
		return isModify;
	}

	public void setIsModify(Integer isModify) {
		this.isModify = isModify;
	}

	public Integer getNoticeTimes() {
		return noticeTimes;
	}

	public void setNoticeTimes(Integer noticeTimes) {
		this.noticeTimes = noticeTimes;
	}

	public Integer getTryTimes() {
		return tryTimes;
	}

	public void setTryTimes(Integer tryTimes) {
		this.tryTimes = tryTimes;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Integer getIpbindFlag() {
		return ipbindFlag;
	}

	public void setIpbindFlag(Integer ipbindFlag) {
		this.ipbindFlag = ipbindFlag;
	}

	public Integer getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(Integer loginFlag) {
		this.loginFlag = loginFlag;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getLoginType() {
		return loginType;
	}

	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}

	public Integer getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(Integer validFlag) {
		this.validFlag = validFlag;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public String getCreateNo() {
		return createNo;
	}

	public void setCreateNo(String createNo) {
		this.createNo = createNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateNo() {
		return updateNo;
	}

	public void setUpdateNo(String updateNo) {
		this.updateNo = updateNo;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getAllCreateNo() {
		return allCreateNo;
	}

	public void setAllCreateNo(String allCreateNo) {
		this.allCreateNo = allCreateNo;
	}

	public Integer getPwdValidDays() {
		return pwdValidDays;
	}

	public void setPwdValidDays(Integer pwdValidDays) {
		this.pwdValidDays = pwdValidDays;
	}
    @Override
	public String toString() {
		String rt = "";
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sb.append("{");
		sb.append("\"login_id\":\"").append(this.loginId).append("\",");
		sb.append("\"login_no\":\"").append(this.loginNo).append("\",");
		sb.append("\"login_name\":\"").append(this.loginName).append("\",");
		sb.append("\"login_passwd\":\"").append(this.loginPasswd).append("\",");
		sb.append("\"pwd_set_date\":\"").append(
				null == this.pwdSetDate ? "" : sdf.format(this.pwdSetDate))
				.append("\",");
		sb.append("\"pwd_invalid_date\":\"").append(
				null == this.pwdInvalidDate ? "" : sdf
						.format(this.pwdInvalidDate)).append("\",");
		sb.append("\"pwd_his\":\"").append(this.pwdHis).append("\",");
		sb.append("\"pwd_update_date\":\"").append(
				null == this.pwdUpdateDate ? "" : sdf
						.format(this.pwdUpdateDate)).append("\",");
		sb.append("\"is_modify\":\"").append(this.isModify).append("\",");
		sb.append("\"notice_times\":\"").append(this.noticeTimes).append("\",");
		sb.append("\"try_times\":\"").append(this.tryTimes).append("\",");
		sb.append("\"login_ip\":\"").append(this.loginIp).append("\",");
		sb.append("\"ipbind_flag\":\"").append(this.ipbindFlag).append("\",");
		sb.append("\"login_flag\":\"").append(this.loginFlag).append("\",");
		sb.append("\"last_date\":\"").append(
				null == this.lastDate ? "" : sdf.format(this.lastDate)).append(
				"\",");
		sb.append("\"contact_phone\":\"").append(this.contactPhone).append(
				"\",");
		sb.append("\"email\":\"").append(this.email).append("\",");
		sb.append("\"org_id\":\"").append(this.orgId).append("\",");
		sb.append("\"role_id\":\"").append(this.roleId.toString()).append("\",");
		sb.append("\"login_type\":\"").append(this.loginType).append("\",");
		sb.append("\"valid_flag\":\"").append(this.validFlag).append("\",");
		sb.append("\"valid_date\":\"").append(
				null == this.validDate ? "" : sdf.format(this.validDate))
				.append("\",");
		sb.append("\"create_no\":\"").append(this.createNo).append("\",");
		sb.append("\"create_time\":\"").append(
				null == this.createTime ? "" : sdf.format(this.createTime))
				.append("\",");
		sb.append("\"update_no\":\"").append(this.updateNo).append("\",");
		sb.append("\"update_time\":\"").append(
				null == this.updateTime ? "" : sdf.format(this.updateTime))
				.append("\",");
		sb.append("\"all_create_no\":\"").append(this.allCreateNo).append("\",");
		sb.append("\"pwd_valid_days\":\"").append(this.pwdValidDays).append("\"");
		sb.append("}");
		rt = sb.toString();
		return rt;
	}
	
	public LoginMsgJSON analyseJSON(JSONObject jo) {
		Date temp = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LoginMsgJSON lm = new LoginMsgJSON();
		
		lm.setLoginId(Integer.parseInt(jo.getString("login_id")));
		lm.setLoginNo(jo.getString("login_no"));
		lm.setLoginName(jo.getString("login_name"));
		lm.setLoginPasswd(jo.getString("login_passwd"));
		
		try {
			temp = sdf.parse(jo.getString("pwd_set_date"));
			lm.setPwdSetDate(temp);
		} catch (ParseException e) {
			lm.setPwdSetDate(null);
			logger.error("pwd_set_date", e);
		}
		
		try {
			temp = sdf.parse(jo.getString("pwd_invalid_date"));
			lm.setPwdInvalidDate(temp);
		} catch (ParseException e) {
			lm.setPwdInvalidDate(null);
			logger.error("pwd_invalid_date", e);
		}
		
		lm.setPwdHis(jo.getString("pwd_his"));
		
		try {
			temp = sdf.parse(jo.getString("pwd_update_date"));
			lm.setPwdUpdateDate(temp);
		} catch (ParseException e) {
			lm.setPwdUpdateDate(null);
			logger.error("pwd_update_date", e);
		}
		
		try {
			lm.setIsModify(Integer.parseInt(jo.getString("is_modify")));
		} catch (NumberFormatException e) {
			lm.setIsModify(0);
			logger.error("is_modify", e);
		}
		
		try {
			lm.setNoticeTimes(Integer.parseInt(jo.getString("notice_times")));
		} catch (NumberFormatException e) {
			lm.setNoticeTimes(0);
			logger.error("notice_times", e);
		}
		
		try {
			lm.setTryTimes(Integer.parseInt(jo.getString("try_times")));
		} catch (NumberFormatException e) {
			lm.setTryTimes(0);
			logger.error("try_times", e);
		}
		
		lm.setLoginIp(jo.getString("login_ip"));
		
		try {
			lm.setIpbindFlag(Integer.parseInt(jo.getString("ipbind_flag")));
		} catch (NumberFormatException e) {
			lm.setIpbindFlag(0);
			logger.error("ipbind_flag", e);
		}
		
		try {
			lm.setLoginFlag(Integer.parseInt(jo.getString("login_flag")));
		} catch (NumberFormatException e) {
			lm.setLoginFlag(0);
			logger.error("login_flag", e);
		}
		
		try {
			temp = sdf.parse(jo.getString("last_date"));
			lm.setLastDate(temp);
		} catch (ParseException e) {
			lm.setLastDate(null);
			logger.error("last_date", e);
		}
		
		lm.setContactPhone(jo.getString("contact_phone"));
		lm.setEmail(jo.getString("email"));
		
		try {
			lm.setOrgId(Integer.parseInt(jo.getString("org_id")));
		} catch (NumberFormatException e) {
			lm.setOrgId(0);
			logger.error("org_id", e);
		}
		
		try {
			lm.setLoginType(Integer.parseInt(jo.getString("login_type")));
		} catch (NumberFormatException e) {
			lm.setLoginType(0);
			logger.error("login_type", e);
		}
		try {
			lm.setValidFlag(Integer.parseInt(jo.getString("valid_flag")));
		} catch (NumberFormatException e) {
			lm.setValidFlag(0);
			logger.error("valid_flag", e);
		}
		
		try {
			temp = sdf.parse(jo.getString("valid_date"));
			lm.setValidDate(temp);
		} catch (ParseException e) {
			lm.setValidDate(null);
			logger.error("valid_date", e);
		}
		
		lm.setCreateNo(jo.getString("create_no"));
		
		try {
			temp = sdf.parse(jo.getString("create_time"));
			lm.setCreateTime(temp);
		} catch (ParseException e) {
			lm.setCreateTime(null);
			logger.error("create_time", e);
		}
		
		lm.setUpdateNo(jo.getString("update_no"));
		
		try {
			temp = sdf.parse(jo.getString("update_time"));
			lm.setUpdateTime(temp);
		} catch (ParseException e) {
			lm.setUpdateTime(null);
			logger.error("create_time", e);
		}
		
		lm.setAllCreateNo(jo.getString("all_create_no"));
		
		try {
			lm.setPwdValidDays(Integer.parseInt(jo.getString("pwd_valid_days")));
		} catch (NumberFormatException e) {
			lm.setPwdValidDays(1);
			logger.error("pwd_valid_days", e);
		}
		
		return lm;
	}
	
	/**
	 * 将Login转换为LoginMsgJSON.
	 * 
	 * @param login
	 * @return
	 */
	public LoginMsgJSON login2JSON(Login login) {
		if (null == login) {
			logger.error("Login参数不能为空.");
			return null;
		}
		LoginMsgJSON lm = new LoginMsgJSON();
		lm.setAllCreateNo(login.getAllCreateNo());
		lm.setContactPhone(login.getContactPhone());
		lm.setCreateNo(login.getCreateNo());
		lm.setCreateTime(login.getCreateTime());
		lm.setEmail(login.getEmail());
		lm.setIpbindFlag(login.getIpbindFlag());
		lm.setIsModify(login.getIsModify());
		lm.setLastDate(login.getLastDate());
		lm.setLoginFlag(login.getLoginFlag());
		lm.setLoginId(login.getLoginId());
		lm.setLoginIp(login.getLoginIp());
		lm.setLoginName(login.getLoginName());
		
		lm.setLoginNo(login.getLoginNo());
		lm.setLoginPasswd(login.getLoginPasswd());
		lm.setPwdHis(login.getPwdHis());
		lm.setPwdInvalidDate(login.getPwdInvalidDate());
		lm.setPwdSetDate(login.getPwdSetDate());
		lm.setPwdUpdateDate(login.getPwdUpdateDate());
		lm.setPwdValidDays((int) ((login.getPwdInvalidDate().getTime() - System
				.currentTimeMillis()) / (1000 * 60 * 60 * 24)) + 1);
		lm.setTryTimes(login.getTryTimes());
		lm.setUpdateNo(login.getUpdateNo());
		lm.setUpdateTime(login.getUpdateTime());
		lm.setValidDate(login.getValidDate());
		lm.setValidFlag(login.getValidFlag());
		return lm;
	}

}
