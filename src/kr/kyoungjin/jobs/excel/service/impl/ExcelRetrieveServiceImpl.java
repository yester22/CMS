package kr.kyoungjin.jobs.excel.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kyoungjin.dataobject.dao.ExcelDao;
import kr.kyoungjin.dataobject.vo.ExcelUploadDetailVo;
import kr.kyoungjin.dataobject.vo.ExcelUploadVo;
import kr.kyoungjin.jobs.excel.service.ExcelRetrieveService;

/**
 * @since 2020. 3. 25.
 * @author yester21
 * <PRE>
 * -------------------------
 * 개정이력
 * 2020. 3. 25. yester21 : 최초작성
 * </PRE>
 */
@Service
public class ExcelRetrieveServiceImpl implements ExcelRetrieveService {

	@Autowired
	private ExcelDao excelDao;
	
	@Override
	public Map<String,Object> getExcelUploadList(Map<String, Object> param) throws Exception {
		String searchDate = (String)param.get("searchStartDate"); 
		if ( searchDate != null && !"".equals(searchDate) ) {
			param.put("searchStartDate", searchDate.replaceAll("-", ""));
		}
		
		searchDate = (String)param.get("searchEndDate"); 
		if ( searchDate != null && !"".equals(searchDate) ) {
			param.put("searchEndDate", searchDate.replaceAll("-", ""));
		}
		
		String startNum = (String)param.get("startNum");
		if ( startNum != null && !"".equals(startNum) ) {
			param.put("startNum", Long.parseLong(startNum));
		}
		
		String pageSize = (String)param.get("pageSize");
		if ( pageSize != null && !"".equals(pageSize) ) {
			param.put("pageSize", Long.parseLong(pageSize));
		}
		
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("LIST",  excelDao.selectExcelUploaInfo(param));
		returnMap.put("COUNT", excelDao.selectExcelUploaInfoCount(param));
		
		return returnMap;
	}

	
	
	
	@Override
	public  Map<String,Object> getExcelDetailList(Map<String, Object> param) throws Exception {
		String startNum = (String)param.get("startNum");
		if ( startNum != null && !"".equals(startNum) ) {
			param.put("startNum", Long.parseLong(startNum));
		}
		
		String pageSize = (String)param.get("pageSize");
		if ( pageSize != null && !"".equals(pageSize) ) {
			param.put("pageSize", Long.parseLong(pageSize));
		}
		
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("LIST",  excelDao.selectExcelUploaData(param));
		returnMap.put("COUNT", excelDao.selectExcelUploaDataCount(param));
		
		return returnMap;
	}

	@Override
	public ExcelUploadVo getExcelUploadInfo(Map<String, Object> params)  throws Exception {
		ExcelUploadVo paramObj = new ExcelUploadVo();
		paramObj.setExcelKey(params.get("excelKey").toString());
		return excelDao.selectExcelInfoData(paramObj);
	}


	@Override
	public int deleteExcelData(Map<String, Object> params) throws Exception {
		return excelDao.deleteExcelData(params);
		
	}
	
}
