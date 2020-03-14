package kr.kyoungjin.dataobject.vo;

import kr.kyoungjin.common.abstractObject.AbstractVo;

public class BatchVo extends AbstractVo {
	
	private String batchId; 
	private String batchNm; 
	private String description; 
	private String executeTime; 
	private String lastExecuteDate; 
	private String managerId; 
	private String managerEmail; 
	private String useYn;
	
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getBatchNm() {
		return batchNm;
	}
	public void setBatchNm(String batchNm) {
		this.batchNm = batchNm;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExecuteTime() {
		return executeTime;
	}
	public void setExecuteTime(String executeTime) {
		this.executeTime = executeTime;
	}
	public String getLastExecuteDate() {
		return lastExecuteDate;
	}
	public void setLastExecuteDate(String lastExecuteDate) {
		this.lastExecuteDate = lastExecuteDate;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getManagerEmail() {
		return managerEmail;
	}
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	} 
	
	
		
}
