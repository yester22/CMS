package kr.kyoungjin.jobs.excel.service;

import java.util.Map;

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
	public String excelUpload( Map<String, Object> params, MultipartFile uploadfile, String uploaderId) throws Exception;
}
