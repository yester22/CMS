package kr.kyoungjin.dataobject.vo;

import kr.kyoungjin.common.abstractObject.AbstractVo;

public class GrantMenuVo  extends AbstractVo {
	private String menuCd; 
	private String grantCd; 
	private String grantw; 
	private String grantd; 
	private String grantu;
	
	public String getMenuCd() {
		return menuCd;
	}
	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
	}
	public String getGrantCd() {
		return grantCd;
	}
	public void setGrantCd(String grantCd) {
		this.grantCd = grantCd;
	}
	public String getGrantw() {
		return grantw;
	}
	public void setGrantw(String grantw) {
		this.grantw = grantw;
	}
	public String getGrantd() {
		return grantd;
	}
	public void setGrantd(String grantd) {
		this.grantd = grantd;
	}
	public String getGrantu() {
		return grantu;
	}
	public void setGrantu(String grantu) {
		this.grantu = grantu;
	}
	
	
}
