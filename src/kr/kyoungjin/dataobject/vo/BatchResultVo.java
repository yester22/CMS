package kr.kyoungjin.dataobject.vo;

import kr.kyoungjin.common.abstractObject.AbstractVo;

public class BatchResultVo extends AbstractVo {
	private String resultId; 
	private String batchId; 
	private String startTime; 
	private String endTime; 
	private String successYn; 
	private String resultMsg; 
	private String adminSendDate; 
	private String adminSendMail;
	
	
	public String getResultId() {
		return resultId;
	}
	public void setResultId(String resultId) {
		this.resultId = resultId;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getSuccessYn() {
		return successYn;
	}
	public void setSuccessYn(String successYn) {
		this.successYn = successYn;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public String getAdminSendDate() {
		return adminSendDate;
	}
	public void setAdminSendDate(String adminSendDate) {
		this.adminSendDate = adminSendDate;
	}
	public String getAdminSendMail() {
		return adminSendMail;
	}
	public void setAdminSendMail(String adminSendMail) {
		this.adminSendMail = adminSendMail;
	}
	
	
	
}
