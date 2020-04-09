package kr.kyoungjin.dataobject.vo;

import java.io.Serializable;

/**엑셀 업로드 상세 정보
 * @author gyeongjinjung
 * @since 2020-03-20
 *
 */
public class ExcelUploadDetailVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5353057628279586638L;

	private String excelKey;
	private long   dataSeq;
	private String sido;
	private String sigungu;
	private String upmyundong;
	private String ri;
	private String mountainYn;
	private String bunji;
	private String bubunji;
	
	private String isValidYn;
	private String pmuCd;
	private long   rownum;
	
	private String fullAddr;
	
	private String landWidth;
	private String crs;
	private String xPos;
	private String yPos;
	private String poligonData;
	
	
	public String getExcelKey() {
		return excelKey;
	}
	public void setExcelKey(String excelKey) {
		this.excelKey = excelKey;
	}
	public long getDataSeq() {
		return dataSeq;
	}
	public void setDataSeq(long dataSeq) {
		this.dataSeq = dataSeq;
	}
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getSigungu() {
		return sigungu;
	}
	public void setSigungu(String sigungu) {
		this.sigungu = sigungu;
	}
	public String getUpmyundong() {
		return upmyundong;
	}
	public void setUpmyundong(String upmyundong) {
		this.upmyundong = upmyundong;
	}
	public String getRi() {
		return ri;
	}
	public void setRi(String ri) {
		this.ri = ri;
	}
	public String getBunji() {
		return bunji;
	}
	public void setBunji(String bunji) {
		this.bunji = bunji;
	}
	public String getIsValidYn() {
		return isValidYn;
	}
	public void setIsValidYn(String isValidYn) {
		this.isValidYn = isValidYn;
	}

	public String getBubunji() {
		return bubunji;
	}
	public void setBubunji(String bubunji) {
		this.bubunji = bubunji;
	}
	public long getRownum() {
		return rownum;
	}
	public void setRownum(long rownum) {
		this.rownum = rownum;
	}

	public String getPmuCd() {
		return pmuCd;
	}
	public void setPmuCd(String pmuCd) {
		this.pmuCd = pmuCd;
	}
	public String getMountainYn() {
		return mountainYn;
	}
	public void setMountainYn(String mountainYn) {
		this.mountainYn = mountainYn;
	}
	public String getFullAddr() {
		return fullAddr;
	}
	public void setFullAddr(String fullAddr) {
		this.fullAddr = fullAddr;
	}
	public String getLandWidth() {
		return landWidth;
	}
	public void setLandWidth(String landWidth) {
		this.landWidth = landWidth;
	}
	public String getCrs() {
		return crs;
	}
	public void setCrs(String crs) {
		this.crs = crs;
	}
	public String getxPos() {
		return xPos;
	}
	public void setxPos(String xPos) {
		this.xPos = xPos;
	}
	public String getyPos() {
		return yPos;
	}
	public void setyPos(String yPos) {
		this.yPos = yPos;
	}
	public String getPoligonData() {
		return poligonData;
	}
	public void setPoligonData(String poligonData) {
		this.poligonData = poligonData;
	}
	
	
	
}
