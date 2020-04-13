package kr.kyoungjin.jobs.excel.service.impl;

import java.util.HashMap;
import java.util.List;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * 媛쒖젙�씠�젰
 * 2020. 3. 20. yester21 : 理쒖큹�옉�꽦
 * </PRE>
 */
@Service
public class ExcelUploadServiceImpl implements ExcelUploadService {

	private Logger logger  = LoggerFactory.getLogger(ExcelUploadServiceImpl.class);
	
	@Value("#{config['FILE.SAVE_PATH']}")
	private String FILE_SAVE_PATH;
	
	@Autowired
	private ExcelDao excelUploadDao;
	
	
	/**
	 *�뿊�� �뾽濡쒕뱶 泥섎━
	 */
	@Override
	@Transactional
	public String excelUpload(Map<String,Object> params , MultipartFile uploadfile, String uploaderId) throws Exception {
		
		//�깉濡� �깮�꽦�븷 selectKey瑜� 媛��졇�삩�떎
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
		saveExcelUpload.setStatusCode("RS_REG");
		
		excelUploadDao.updateExcelUploaInfo(saveExcelUpload);
		
		//pnu data update
		this.updatePnuData(sNewExcelKey);
		
		return sNewExcelKey;
	}
	
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 23.
	 * @Method Name : saveExcelData
	 * @return : int
	 */
	private int saveExcelData( String sNewExcelKey, String filePath ) throws Exception {
		int nDataCount = 0;
		int nStatRow = 2;
		
		//file �솗�옣�옄 
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

		String landWidth = "";
		sheet = workbook.getSheetAt(0);
        int rows = sheet.getPhysicalNumberOfRows();//�떆�듃�뿉�꽌 珥� �뻾�닔
        for (int nCrrentRow = nStatRow; nCrrentRow <= rows; nCrrentRow++) {
        	detailVo = new ExcelUploadDetailVo();
        	
        	detailVo.setExcelKey(sNewExcelKey);
        	detailVo.setDataSeq(++nDataCount); 
        	detailVo.setSido(this.getCellValue(sheet.getRow(nCrrentRow).getCell(1)));
        	detailVo.setSigungu(this.getCellValue(sheet.getRow(nCrrentRow).getCell(2)));
        	detailVo.setUpmyundong(this.getCellValue(sheet.getRow(nCrrentRow).getCell(3)));
        	detailVo.setRi(this.getCellValue(sheet.getRow(nCrrentRow).getCell(4)));
        	detailVo.setMountainYn(this.getCellValue(sheet.getRow(nCrrentRow).getCell(5)));
        	detailVo.setBunji(this.getCellValue(sheet.getRow(nCrrentRow).getCell(6)));
        	detailVo.setBubunji(this.getCellValue(sheet.getRow(nCrrentRow).getCell(7)));
        	
        	landWidth = this.getCellValue(sheet.getRow(nCrrentRow).getCell(8));
        	
        	detailVo.setLandWidth(this.landWidthColoneClear(landWidth));
        	detailVo.setIsValidYn(ConstantNames.USE_YN_N);
        	
        	excelUploadDao.insertExcelDetailInfo(detailVo);
        }
        
        
		return nDataCount;
		
	}

	
	private String getCellValue(   Cell cell ) {
		String value = "";
		if( cell == null ) {
			  value = "";
		}else if(cell != null && cell.getCellType() == Cell.CELL_TYPE_BLANK){
              value="";
       } else{
          //���엯蹂꾨줈 �궡�슜 �씫湲�
          switch (cell.getCellType()){
              case Cell.CELL_TYPE_FORMULA:
                  value=cell.getCellFormula();
                  break;
              case Cell.CELL_TYPE_NUMERIC:
                  value = cell.getNumericCellValue()+"";
                  if ( value.lastIndexOf(".") > 0 ) {
                	  value = value.substring(0, value.lastIndexOf("."));
                  }
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
	
	
	/**
	 * @Author : yeste
	 * @Date : 2020. 3. 31.
	 * @Method Name : updatePnuData
	 * @return : int
	 * @throws Exception 
	 */
	private int updatePnuData (String excelKey) throws Exception {
		int nRtnCnt = 0;
		
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("excelKey", excelKey);
		List<ExcelUploadDetailVo> list = excelUploadDao.selectPmuList(param);
		if ( list != null && list.size() > 0 ) {
			for ( ExcelUploadDetailVo element : list ) {
				nRtnCnt = nRtnCnt + excelUploadDao.updateExcelDataPmuCd(element);
			}
		}
		
		return nRtnCnt;
	}
	
	
	private String landWidthColoneClear(String landWidthData) {
		String rtnVal = landWidthData.replaceAll(",", "");
		/*
		 * rtnVal = rtnVal.replaceAll("(", ""); rtnVal = rtnVal.replaceAll(")", "");
		 * rtnVal = rtnVal.replaceAll("#", ""); rtnVal = rtnVal.replaceAll("!", "");
		 */
		return rtnVal;
	}
}
