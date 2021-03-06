package kr.kyoungjin.dataobject.vo;

import kr.kyoungjin.common.abstractObject.AbstractVo;

public class MenuVo  extends AbstractVo {
	
	private String menuCd; 
	private String menuNm;
	private String upMenuCd;
	private int    orderSeq;
	private String programYn; 
	private String mnuIcon;
	private String programUrl; 
	private String displayYn; 
	private String delYn; 
	
	public String getMenuCd() {
		return menuCd;
	}
	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
	}
	public String getMenuNm() {
		return menuNm;
	}
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	public String getProgramYn() {
		return programYn;
	}
	public void setProgramYn(String programYn) {
		this.programYn = programYn;
	}
	public String getProgramUrl() {
		return programUrl;
	}
	public void setProgramUrl(String programUrl) {
		this.programUrl = programUrl;
	}
	public String getDisplayYn() {
		return displayYn;
	}
	public void setDisplayYn(String displayYn) {
		this.displayYn = displayYn;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getUpMenuCd() {
		return upMenuCd;
	}
	public void setUpMenuCd(String upMenuCd) {
		this.upMenuCd = upMenuCd;
	}
	public int getOrderSeq() {
		return orderSeq;
	}
	public void setOrderSeq(int orderSeq) {
		this.orderSeq = orderSeq;
	}
	public String getMnuIcon() {
		return mnuIcon;
	}
	public void setMnuIcon(String mnuIcon) {
		this.mnuIcon = mnuIcon;
	}
	
	
}
