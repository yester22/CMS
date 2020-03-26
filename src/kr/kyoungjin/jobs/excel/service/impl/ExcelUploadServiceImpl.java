package kr.kyoungjin.jobs.excel.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import kr.kyoungjin.common.abstractObject.ConstantNames;
import kr.kyoungjin.common.util.FileUtil;
import kr.kyoungjin.dataobject.dao.ExcelDao;
import kr.kyoungjin.dataobject.vo.ExcelUploadDetailVo;
import kr.kyoungjin.dataobject.vo.ExcelUploadVo;
import kr.kyoungjin.jobs.excel.service.ExcelUploadService;

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
	private ExcelDao excelUploadDao;
	
	
	@Override
	@Transactional
	public String excelUpload(Map<String,Object> params , MultipartFile uploadfile, String uploaderId) throws Exception {
		
		//새로 생성할 selectKey를 가져온다
		String sNewExcelKey = excelUploadDao.selectNewExcelKey();
		
		Map<String, String> saveFileInfo = FileUtil.uploadFile(uploadfile, FILE_SAVE_PATH);
		
		ExcelUploadVo saveExcelUpload = new ExcelUploadVo();
		saveExcelUpload.setExcelKey(sNewExcelKey);
		saveExcelUpload.setTitle(params.get("title").toString());
		saveExcelUpload.setLocationCode(params.get("locationCode").toString());
		saveExcelUpload.setOriginalFileName(uploadfile.getOriginalFilename());
		saveExcelUpload.setPhysicalPath(saveFileInfo.get(FileUtil.FILE_PATH).toString());
		saveExcelUpload.setSavedFileName(saveFileInfo.get(FileUtil.UPLOAD_FILE_NM).toString());
		saveExcelUpload.setUploader(uploaderId);
		saveExcelUpload.setUseYn(ConstantNames.USE_YN_Y);
		saveExcelUpload.setDataCount(0);
		
		excelUploadDao.insertExcelUploaInfo(saveExcelUpload);

		int nExcelDataCount = this.saveExcelData(sNewExcelKey, saveExcelUpload.getPhysicalPath());
		
		saveExcelUpload = new ExcelUploadVo();
		saveExcelUpload.setExcelKey(sNewExcelKey);
		saveExcelUpload.setDataCount(nExcelDataCount);
		excelUploadDao.updateExcelUploaInfo(saveExcelUpload);
		
		
		return sNewExcelKey;
	}
	
	
	/**
	 * @Author : yeste
	 * @Date : 2020. 3. 23.
	 * @Method Name : saveExcelData
	 * @return : int
	 */
	private int saveExcelData( String sNewExcelKey, String filePath ) throws Exception {
		int nDataCount = 0;
		int nStatRow = 2;
		
		//file 확장자 
		String fileExt = FileUtil.getExt(filePath);
		
		ExcelUploadDetailVo detailVo = new ExcelUploadDetailVo();
		FileInputStream inputStream = new FileInputStream(filePath);
		
		Workbook workbook = null;
		Sheet sheet = null;
		if (fileExt.equals("xls") ) {
			workbook = new HSSFWorkbook(inputStream);
		} else if ( fileExt.equals("xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		}

		sheet = workbook.getSheetAt(0);
        int rows = sheet.getPhysicalNumberOfRows();//시트에서 총 행수
        for (int nCrrentRow = nStatRow; nCrrentRow <= rows; nCrrentRow++) {
        	detailVo = new ExcelUploadDetailVo();
        	
        	detailVo.setExcelKey(sNewExcelKey);
        	detailVo.setDataSeq(++nDataCount); 
        	detailVo.setSido(this.getCellValue(sheet.getRow(nCrrentRow).getCell(1)));
        	detailVo.setSigungu(this.getCellValue(sheet.getRow(nCrrentRow).getCell(2)));
        	detailVo.setUpmyundong(this.getCellValue(sheet.getRow(nCrrentRow).getCell(3)));
        	detailVo.setRi(this.getCellValue(sheet.getRow(nCrrentRow).getCell(4)));
        	detailVo.setBunji(this.getCellValue(sheet.getRow(nCrrentRow).getCell(5)));
        	detailVo.setBubunji(this.getCellValue(sheet.getRow(nCrrentRow).getCell(6)));
        	detailVo.setIsValidYn(ConstantNames.USE_YN_N);
        	detailVo.setIsTransYn(ConstantNames.USE_YN_N);
        	
        	excelUploadDao.insertExcelDetailInfo(detailVo);
        }
        
        
		return nDataCount;
		
	}

	
	private String getCellValue(   Cell cell ) {
		String value = "";
		if(cell.getCellType() == Cell.CELL_TYPE_BLANK){
              value="";
       } else{
          //타입별로 내용 읽기
          switch (cell.getCellType()){
              case Cell.CELL_TYPE_FORMULA:
                  value=cell.getCellFormula();
                  break;
              case Cell.CELL_TYPE_NUMERIC:
                  value=cell.getNumericCellValue()+"";
                  break;
              case Cell.CELL_TYPE_STRING:
                  value=cell.getStringCellValue()+"";
                  break;
              case Cell.CELL_TYPE_BLANK:
                  value=cell.getBooleanCellValue()+"";
                  break;
              case Cell.CELL_TYPE_ERROR:
                  value=cell.getErrorCellValue()+"";
                  break;
          }
       	}
		return value;
	}
}