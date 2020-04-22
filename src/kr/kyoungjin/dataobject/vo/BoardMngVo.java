package kr.kyoungjin.dataobject.vo;

import kr.kyoungjin.common.abstractObject.AbstractVo;

public class BoardMngVo extends AbstractVo {
	private String boardCd; 
	private String boardNm; 
	private String listRowCnt; 
	private String listBlockCnt; 
	private String titleSplitLen; 
	private String contentsSplitLen; 
	private String latestNoticeCnt; 
	private String useYn;
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
	public String getBoardNm() {
		return boardNm;
	}
	public void setBoardNm(String boardNm) {
		this.boardNm = boardNm;
	}
	public String getListRowCnt() {
		return listRowCnt;
	}
	public void setListRowCnt(String listRowCnt) {
		this.listRowCnt = listRowCnt;
	}
	public String getListBlockCnt() {
		return listBlockCnt;
	}
	public void setListBlockCnt(String listBlockCnt) {
		this.listBlockCnt = listBlockCnt;
	}
	public String getTitleSplitLen() {
		return titleSplitLen;
	}
	public void setTitleSplitLen(String titleSplitLen) {
		this.titleSplitLen = titleSplitLen;
	}
	public String getContentsSplitLen() {
		return contentsSplitLen;
	}
	public void setContentsSplitLen(String contentsSplitLen) {
		this.contentsSplitLen = contentsSplitLen;
	}
	public String getLatestNoticeCnt() {
		return latestNoticeCnt;
	}
	public void setLatestNoticeCnt(String latestNoticeCnt) {
		this.latestNoticeCnt = latestNoticeCnt;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
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
