package kr.kyoungjin.jobs.uploader.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.kyoungjin.common.util.FileUtil;
import kr.kyoungjin.dataobject.dao.ExcelUploadDao;
import kr.kyoungjin.dataobject.vo.ExcelUploadVo;
import kr.kyoungjin.jobs.uploader.service.ExcelUploadService;

/**
 * @since 2020. 3. 20.
 * @author yester21
 * <PRE>
 * -------------------------
 * 개정이력
 * 2020. 3. 20. yester21 : 최초작성
 * </PRE>
 */
@Service
public class ExcelUploadServiceImpl implements ExcelUploadService {

	private Log logger = LogFactory.getLog(ExcelUploadServiceImpl.class);
	
	@Value("#{config['FILE.SAVE_PATH']}")
	private String FILE_SAVE_PATH;
	
	@Autowired
	private ExcelUploadDao excelUploadDao;
	
	
	@Override
	@Transactional
	public String excelUpload(MultipartFile uploadfile, String uploaderId) throws Exception {
		
		//새로 생성할 selectKey를 가져온다
		String sNewExcelKey = excelUploadDao.selectNewExcelKey();
		
		
		Map<String, String> saveFileInfo = FileUtil.uploadFile(uploadfile, FILE_SAVE_PATH);
		
		ExcelUploadVo saveExcelUpload = new ExcelUploadVo();
		saveExcelUpload.setExcelKey(sNewExcelKey);
		saveExcelUpload.setOriginalFileName(uploadfile.getOriginalFilename());
		saveExcelUpload.setPhysicalPath(FILE_SAVE_PATH);
		saveExcelUpload.setSavedFileName(saveFileInfo.get(FileUtil.UPLOAD_FILE_NM).toString());
		saveExcelUpload.setUploader(uploaderId);
		saveExcelUpload.setDataCount(0);
		
		
		excelUploadDao.insertExcelUploaInfo(saveExcelUpload);
		
		return sNewExcelKey;
	
	
	}

}
