package kr.kyoungjin.dataobject.vo;

import kr.kyoungjin.common.abstractObject.AbstractVo;

public class BoardVo  extends AbstractVo  {
	private String boardCd; 
	private String boardSeq; 
	private String upBoardSeq; 
	private String depth; 
	private String title; 
	private String tag; 
	private String body; 
	private String htmlYn;
	private String delYn;
	private String regDate;
	private String regId;
	private String uptDate;
	private String uptId;

	private long   rownum;

	public String getBoardCd() {
		return boardCd;
	}
	public void setBoardCd(String boardCd) {
		this.boardCd = boardCd;
	}
	public String getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(String boardSeq) {
		this.boardSeq = boardSeq;
	}
	public String getUpBoardSeq() {
		return upBoardSeq;
	}
	public void setUpBoardSeq(String upBoardSeq) {
		this.upBoardSeq = upBoardSeq;
	}
	public String getDepth() {
		return depth;
	}
	public void setDepth(String depth) {
		this.depth = depth;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getHtmlYn() {
		return htmlYn;
	}
	public void setHtmlYn(String htmlYn) {
		this.htmlYn = htmlYn;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
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
	public long getRownum() {
		return rownum;
	}
	public void setRownum(long rownum) {
		this.rownum = rownum;
	}
}
