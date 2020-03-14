package kr.kyoungjin.dataobject.vo;

import kr.kyoungjin.common.abstractObject.AbstractVo;

public class TermsVo  extends AbstractVo {
	
	private String termsCd; 
	private String termsTitle; 
	private String termsBody; 
	private String target; 
	private String startDt; 
	private String endDt; 
	private String essentialYn; 
	private String useYn; 
	
	public String getTermsCd() {
		return termsCd;
	}
	public void setTermsCd(String termsCd) {
		this.termsCd = termsCd;
	}
	public String getTermsTitle() {
		return termsTitle;
	}
	public void setTermsTitle(String termsTitle) {
		this.termsTitle = termsTitle;
	}
	public String getTermsBody() {
		return termsBody;
	}
	public void setTermsBody(String termsBody) {
		this.termsBody = termsBody;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getEssentialYn() {
		return essentialYn;
	}
	public void setEssentialYn(String essentialYn) {
		this.essentialYn = essentialYn;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	
	
	
}
