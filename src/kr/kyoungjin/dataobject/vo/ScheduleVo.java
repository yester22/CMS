package kr.kyoungjin.dataobject.vo;

import kr.kyoungjin.common.abstractObject.AbstractVo;

public class ScheduleVo  extends AbstractVo {
	
	private String cmpCd; 
	private String freeCode; 
	private String schduleType; 
	private String targetAmount; 
	private String amount; 
	private String charger; 
	private String startDate; 
	private String endDate; 
	
	public String getCmpCd() {
		return cmpCd;
	}
	public void setCmpCd(String cmpCd) {
		this.cmpCd = cmpCd;
	}
	public String getFreeCode() {
		return freeCode;
	}
	public void setFreeCode(String freeCode) {
		this.freeCode = freeCode;
	}
	public String getSchduleType() {
		return schduleType;
	}
	public void setSchduleType(String schduleType) {
		this.schduleType = schduleType;
	}
	public String getTargetAmount() {
		return targetAmount;
	}
	public void setTargetAmount(String targetAmount) {
		this.targetAmount = targetAmount;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCharger() {
		return charger;
	}
	public void setCharger(String charger) {
		this.charger = charger;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
		
}
