package kr.kyoungjin.dataobject.vo;

import kr.kyoungjin.common.abstractObject.AbstractVo;

public class BatchHistoryInfoVo extends AbstractVo{
	
	private String batchKey;
	private String batchType;
	private String batchTarget;
	private String batchStart;
	private String batchEnd;
	private String successYn;
	private String descript;
	
	public String getBatchKey() {
		return batchKey;
	}
	public void setBatchKey(String batchKey) {
		this.batchKey = batchKey;
	}
	public String getBatchType() {
		return batchType;
	}
	public void setBatchType(String batchType) {
		this.batchType = batchType;
	}
	public String getBatchTarget() {
		return batchTarget;
	}
	public void setBatchTarget(String batchTarget) {
		this.batchTarget = batchTarget;
	}
	public String getBatchStart() {
		return batchStart;
	}
	public void setBatchStart(String batchStart) {
		this.batchStart = batchStart;
	}
	public String getBatchEnd() {
		return batchEnd;
	}
	public void setBatchEnd(String batchEnd) {
		this.batchEnd = batchEnd;
	}
	public String getSuccessYn() {
		return successYn;
	}
	public void setSuccessYn(String successYn) {
		this.successYn = successYn;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	
	
	
	
}
