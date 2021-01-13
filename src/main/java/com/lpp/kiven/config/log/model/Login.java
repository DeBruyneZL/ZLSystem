package com.lpp.kiven.config.log.model;

import java.util.Date;


/**
 * Login entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Login implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

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
	private Integer validFlag;
	private Date validDate;
	private String createNo;
	private Date createTime;
	private String updateNo;
	private Date updateTime;
	private String allCreateNo;
	private String areaIds;

	// Constructors

	/** default constructor */
	public Login() {
	}

	/** full constructor */
	// Property accessors
	public Integer getLoginId() {
		return this.loginId;
	}

	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}

	public String getLoginNo() {
		return this.loginNo;
	}

	public void setLoginNo(String loginNo) {
		this.loginNo = loginNo;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPasswd() {
		return this.loginPasswd;
	}

	public void setLoginPasswd(String loginPasswd) {
		this.loginPasswd = loginPasswd;
	}

	public Date getPwdSetDate() {
		return this.pwdSetDate;
	}

	public void setPwdSetDate(Date pwdSetDate) {
		this.pwdSetDate = pwdSetDate;
	}

	public Date getPwdInvalidDate() {
		return this.pwdInvalidDate;
	}

	public void setPwdInvalidDate(Date pwdInvalidDate) {
		this.pwdInvalidDate = pwdInvalidDate;
	}

	public String getPwdHis() {
		return this.pwdHis;
	}

	public void setPwdHis(String pwdHis) {
		this.pwdHis = pwdHis;
	}

	public Date getPwdUpdateDate() {
		return this.pwdUpdateDate;
	}

	public void setPwdUpdateDate(Date pwdUpdateDate) {
		this.pwdUpdateDate = pwdUpdateDate;
	}

	public Integer getIsModify() {
		return this.isModify;
	}

	public void setIsModify(Integer isModify) {
		this.isModify = isModify;
	}

	public Integer getNoticeTimes() {
		return this.noticeTimes;
	}

	public void setNoticeTimes(Integer noticeTimes) {
		this.noticeTimes = noticeTimes;
	}

	public Integer getTryTimes() {
		return this.tryTimes;
	}

	public void setTryTimes(Integer tryTimes) {
		this.tryTimes = tryTimes;
	}

	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Integer getIpbindFlag() {
		return this.ipbindFlag;
	}

	public void setIpbindFlag(Integer ipbindFlag) {
		this.ipbindFlag = ipbindFlag;
	}

	public Integer getLoginFlag() {
		return this.loginFlag;
	}

	public void setLoginFlag(Integer loginFlag) {
		this.loginFlag = loginFlag;
	}

	public Date getLastDate() {
		return this.lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public String getContactPhone() {
		return this.contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getValidFlag() {
		return this.validFlag;
	}

	public void setValidFlag(Integer validFlag) {
		this.validFlag = validFlag;
	}

	public Date getValidDate() {
		return this.validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public String getCreateNo() {
		return this.createNo;
	}

	public void setCreateNo(String createNo) {
		this.createNo = createNo;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateNo() {
		return this.updateNo;
	}

	public void setUpdateNo(String updateNo) {
		this.updateNo = updateNo;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getAllCreateNo() {
		return this.allCreateNo;
	}

	public void setAllCreateNo(String allCreateNo) {
		this.allCreateNo = allCreateNo;
	}

	public String getAreaIds() {
		return areaIds;
	}

	public void setAreaIds(String areaIds) {
		this.areaIds = areaIds;
	}

}