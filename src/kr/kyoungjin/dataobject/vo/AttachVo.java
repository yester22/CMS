package kr.kyoungjin.dataobject.vo;

import kr.kyoungjin.common.abstractObject.AbstractVo;

public class AttachVo extends AbstractVo {
	
	private String fileSeq  ;
	private String fileType ;
	private String ordSeq   ;
	private String boardSeq ;
	private String originNm ;
	private String saveNm   ;
	private String savePath ;
	private String fileSize ;
	
	
	public String getFileSeq() {
		return fileSeq;
	}
	public void setFileSeq(String fileSeq) {
		this.fileSeq = fileSeq;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getOrdSeq() {
		return ordSeq;
	}
	public void setOrdSeq(String ordSeq) {
		this.ordSeq = ordSeq;
	}
	public String getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(String boardSeq) {
		this.boardSeq = boardSeq;
	}
	public String getOriginNm() {
		return originNm;
	}
	public void setOriginNm(String originNm) {
		this.originNm = originNm;
	}
	public String getSaveNm() {
		return saveNm;
	}
	public void setSaveNm(String saveNm) {
		this.saveNm = saveNm;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	
}
