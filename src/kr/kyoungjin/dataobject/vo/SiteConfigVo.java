package kr.kyoungjin.dataobject.vo;

import kr.kyoungjin.common.abstractObject.AbstractVo;

public class SiteConfigVo  extends AbstractVo  {
	
	private String siteCd; 
	private String siteDomain; 
	private String siteName; 
	private String logoSeq; 
	private String mainBoardViewCnt; 
	private String mainReloadTime; 
	private String adminNm; 
	private String adminEmail; 
	private String adminPhone; 
	private String errSendYn;
	
	public String getSiteCd() {
		return siteCd;
	}
	public void setSiteCd(String siteCd) {
		this.siteCd = siteCd;
	}
	public String getSiteDomain() {
		return siteDomain;
	}
	public void setSiteDomain(String siteDomain) {
		this.siteDomain = siteDomain;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getLogoSeq() {
		return logoSeq;
	}
	public void setLogoSeq(String logoSeq) {
		this.logoSeq = logoSeq;
	}
	public String getMainBoardViewCnt() {
		return mainBoardViewCnt;
	}
	public void setMainBoardViewCnt(String mainBoardViewCnt) {
		this.mainBoardViewCnt = mainBoardViewCnt;
	}
	public String getMainReloadTime() {
		return mainReloadTime;
	}
	public void setMainReloadTime(String mainReloadTime) {
		this.mainReloadTime = mainReloadTime;
	}
	public String getAdminNm() {
		return adminNm;
	}
	public void setAdminNm(String adminNm) {
		this.adminNm = adminNm;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getAdminPhone() {
		return adminPhone;
	}
	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}
	public String getErrSendYn() {
		return errSendYn;
	}
	public void setErrSendYn(String errSendYn) {
		this.errSendYn = errSendYn;
	}
	
	
	
}
