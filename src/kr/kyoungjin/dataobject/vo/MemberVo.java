package kr.kyoungjin.dataobject.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import kr.kyoungjin.common.abstractObject.AbstractVo;


public class MemberVo  extends AbstractVo {

	private String memberNm;
	private String memberId;
	private String memberType;
	private String pwd;
	private String chkIdCode;
	private String chkPwCode;
	private Integer loginFailCnt;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date lastLoginDt;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date lastLogoutDt;

	
	private String saveCookieId;


	public String getMemberNm() {
		return memberNm;
	}


	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public String getMemberType() {
		return memberType;
	}


	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getChkIdCode() {
		return chkIdCode;
	}


	public void setChkIdCode(String chkIdCode) {
		this.chkIdCode = chkIdCode;
	}


	public String getChkPwCode() {
		return chkPwCode;
	}


	public void setChkPwCode(String chkPwCode) {
		this.chkPwCode = chkPwCode;
	}


	public Date getLastLoginDt() {
		return lastLoginDt;
	}


	public void setLastLoginDt(Date lastLoginDt) {
		this.lastLoginDt = lastLoginDt;
	}


	public Date getLastLogoutDt() {
		return lastLogoutDt;
	}


	public void setLastLogoutDt(Date lastLogoutDt) {
		this.lastLogoutDt = lastLogoutDt;
	}


	public String getSaveCookieId() {
		return saveCookieId;
	}


	public void setSaveCookieId(String saveCookieId) {
		this.saveCookieId = saveCookieId;
	}


	public Integer getLoginFailCnt() {
		return loginFailCnt;
	}


	public void setLoginFailCnt(Integer loginFailCnt) {
		this.loginFailCnt = loginFailCnt;
	}
	
	
	
	
}
