package kr.kyoungjin.dataobject.vo;

import java.io.Serializable;

import kr.kyoungjin.common.abstractObject.AbstractVo;

/**
 * @since 2020. 4. 13.
 * @author yeste
 * <PRE>
 * -------------------------
 * 개정이력
 * 2020. 4. 13. yeste : 최초작성
 * </PRE>
 */
public class AddrCourtDongPolygonVo  extends AbstractVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1103432369319119050L;
	
	private String courtDongCode;
	private String sidoNm;
	private String sigunguNm;
	private String courtUpmyundongNm;
	private String courtRiNm;
	private String polygonData;
	
	
	public String getCourtDongCode() {
		return courtDongCode;
	}
	public void setCourtDongCode(String courtDongCode) {
		this.courtDongCode = courtDongCode;
	}
	public String getSidoNm() {
		return sidoNm;
	}
	public void setSidoNm(String sidoNm) {
		this.sidoNm = sidoNm;
	}
	public String getSigunguNm() {
		return sigunguNm;
	}
	public void setSigunguNm(String sigunguNm) {
		this.sigunguNm = sigunguNm;
	}
	public String getCourtUpmyundongNm() {
		return courtUpmyundongNm;
	}
	public void setCourtUpmyundongNm(String courtUpmyundongNm) {
		this.courtUpmyundongNm = courtUpmyundongNm;
	}
	public String getCourtRiNm() {
		return courtRiNm;
	}
	public void setCourtRiNm(String courtRiNm) {
		this.courtRiNm = courtRiNm;
	}
	public String getPolygonData() {
		return polygonData;
	}
	public void setPolygonData(String polygonData) {
		this.polygonData = polygonData;
	}

	
	
}
