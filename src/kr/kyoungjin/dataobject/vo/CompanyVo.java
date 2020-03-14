package kr.kyoungjin.dataobject.vo;

import kr.kyoungjin.common.abstractObject.AbstractVo;

public class CompanyVo  extends AbstractVo  {
	private String cmpCd; 
	private String cmpNm; 
	private String ceo; 
	private String tel; 
	
	public String getCmpCd() {
		return cmpCd;
	}
	public void setCmpCd(String cmpCd) {
		this.cmpCd = cmpCd;
	}
	public String getCmpNm() {
		return cmpNm;
	}
	public void setCmpNm(String cmpNm) {
		this.cmpNm = cmpNm;
	}
	public String getCeo() {
		return ceo;
	}
	public void setCeo(String ceo) {
		this.ceo = ceo;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
}
