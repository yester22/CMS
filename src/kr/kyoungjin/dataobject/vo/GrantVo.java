package kr.kyoungjin.dataobject.vo;

import kr.kyoungjin.common.abstractObject.AbstractVo;

public class GrantVo  extends AbstractVo {
	private String grantCd; 
	private String grantNm; 
	private String delYn;
	
	public String getGrantCd() {
		return grantCd;
	}
	public void setGrantCd(String grantCd) {
		this.grantCd = grantCd;
	}
	public String getGrantNm() {
		return grantNm;
	}
	public void setGrantNm(String grantNm) {
		this.grantNm = grantNm;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

}
