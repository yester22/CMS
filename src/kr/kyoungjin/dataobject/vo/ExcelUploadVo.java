package kr.kyoungjin.dataobject.vo;

import java.io.Serializable;
import kr.kyoungjin.common.abstractObject.AbstractVo;
/**
 * @since 2020. 3. 20.
 * @author yester21
 * <PRE>
 * -------------------------
 * 개정이력
 * 2020. 3. 20. yester21 : 최초작성
 * </PRE>
 */
public class ExcelUploadVo extends AbstractVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String excelKey;
	private String title;
	private int dataCount;
	private String uploader;
	private String locationCode;
	private String locationCodeName;
	private String physicalPath;
	private String originalFileName;
	private String savedFileName;
	private String useYn;
	private String uploadDt;
	private String validCompleteYn;
	private String statusCode;
	
	private long   rownum;
	
	public String getExcelKey() {
		return excelKey;
	}
	public void setExcelKey(String excelKey) {
		this.excelKey = excelKey;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getDataCount() {
		return dataCount;
	}
	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public String getPhysicalPath() {
		return physicalPath;
	}
	public void setPhysicalPath(String physicalPath) {
		this.physicalPath = physicalPath;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public String getSavedFileName() {
		return savedFileName;
	}
	public void setSavedFileName(String savedFileName) {
		this.savedFileName = savedFileName;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getUploadDt() {
		return uploadDt;
	}
	public void setUploadDt(String uploadDt) {
		this.uploadDt = uploadDt;
	}
	public String getLocationCodeName() {
		return locationCodeName;
	}
	public void setLocationCodeName(String locationCodeName) {
		this.locationCodeName = locationCodeName;
	}
	public long getRownum() {
		return rownum;
	}
	public void setRownum(long rownum) {
		this.rownum = rownum;
	}
	public String getValidCompleteYn() {
		return validCompleteYn;
	}
	public void setValidCompleteYn(String validCompleteYn) {
		this.validCompleteYn = validCompleteYn;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

		
}
