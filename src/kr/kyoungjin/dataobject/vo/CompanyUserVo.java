package kr.kyoungjin.dataobject.vo;

import kr.kyoungjin.common.abstractObject.AbstractVo;

public class CompanyUserVo  extends AbstractVo  {
	private String cmpCd; 
	private String chargerNm; 
	private String chargerId; 
	private String chargerPw; 
	private String tel; 
	private String mobile; 
	
	public String getCmpCd() {
		return cmpCd;
	}
	public void setCmpCd(String cmpCd) {
		this.cmpCd = cmpCd;
	}
	public String getChargerNm() {
		return chargerNm;
	}
	public void setChargerNm(String chargerNm) {
		this.chargerNm = chargerNm;
	}
	public String getChargerId() {
		return chargerId;
	}
	public void setChargerId(String chargerId) {
		this.chargerId = chargerId;
	}
	public String getChargerPw() {
		return chargerPw;
	}
	public void setChargerPw(String chargerPw) {
		this.chargerPw = chargerPw;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	
	
}
