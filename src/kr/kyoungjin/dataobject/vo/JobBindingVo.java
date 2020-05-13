package kr.kyoungjin.dataobject.vo;

import kr.kyoungjin.common.abstractObject.AbstractVo;

public class JobBindingVo extends AbstractVo {
	private String memberId;
	private String excelKey     ;
	private String dataSeq      ;
	private String orderNo      ;
	private String planStartDt ;
	private String planEndDt   ;
	private String startDate    ;
	private String finishDate   ;
	private String finishYn     ;
	private String regDate      ;
	private String regMember    ;
	
	private int rowNum;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getExcelKey() {
		return excelKey;
	}

	public void setExcelKey(String excelKey) {
		this.excelKey = excelKey;
	}

	public String getDataSeq() {
		return dataSeq;
	}

	public void setDataSeq(String dataSeq) {
		this.dataSeq = dataSeq;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPlanStartDt() {
		return planStartDt;
	}

	public void setPlanStartDt(String planStartDt) {
		this.planStartDt = planStartDt;
	}

	public String getPlanEndDt() {
		return planEndDt;
	}

	public void setPlanEndDt(String planEndDt) {
		this.planEndDt = planEndDt;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public String getFinishYn() {
		return finishYn;
	}

	public void setFinishYn(String finishYn) {
		this.finishYn = finishYn;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getRegMember() {
		return regMember;
	}

	public void setRegMember(String regMember) {
		this.regMember = regMember;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	
}
