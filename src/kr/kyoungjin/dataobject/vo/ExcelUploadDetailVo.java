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
	private String bunji;
	private String isValidYn;
	private String isTransYn;
	
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
	public String getIsTransYn() {
		return isTransYn;
	}
	public void setIsTransYn(String isTransYn) {
		this.isTransYn = isTransYn;
	}
	
	
	
}
