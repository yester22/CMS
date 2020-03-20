package kr.kyoungjin.jobs.uploader.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @since 2020. 3. 20.
 * @author yeste
 * <PRE>
 * -------------------------
 * 개정이력
 * 2020. 3. 20. yeste : 최초작성
 * </PRE>
 */
public interface ExcelUploadService {
	public String excelUpload( MultipartFile uploadfile, String uploaderId) throws Exception;
}
