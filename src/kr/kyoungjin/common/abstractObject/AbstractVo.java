package kr.kyoungjin.common.abstractObject;

public class AbstractVo {
	protected String regDate;
	protected String regId;
	protected String uptDate;
	protected String uptId;
	protected long   startNum;
	protected long   pageSize;
	protected String regMember;
	protected String uptMember;
	
	
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getUptDate() {
		return uptDate;
	}
	public void setUptDate(String uptDate) {
		this.uptDate = uptDate;
	}
	public String getUptId() {
		return uptId;
	}
	public void setUptId(String uptId) {
		this.uptId = uptId;
	}
	public long getStartNum() {
		return startNum;
	}
	public void setStartNum(long startNum) {
		this.startNum = startNum;
	}
	public long getPageSize() {
		return pageSize;
	}
	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}
	public String getRegMember() {
		return regMember;
	}
	public void setRegMember(String regMember) {
		this.regMember = regMember;
	}
	public String getUptMember() {
		return uptMember;
	}
	public void setUptMember(String uptMember) {
		this.uptMember = uptMember;
	}
	
	
	
}
