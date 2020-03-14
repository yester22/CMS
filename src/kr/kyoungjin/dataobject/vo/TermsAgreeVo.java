package kr.kyoungjin.dataobject.vo;

import kr.kyoungjin.common.abstractObject.AbstractVo;

public class TermsAgreeVo  extends AbstractVo {
	
	private String termsCd; 
	private String userId; 
	private String checkYn;
	
	public String getTermsCd() {
		return termsCd;
	}
	public void setTermsCd(String termsCd) {
		this.termsCd = termsCd;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCheckYn() {
		return checkYn;
	}
	public void setCheckYn(String checkYn) {
		this.checkYn = checkYn;
	}
	
}
