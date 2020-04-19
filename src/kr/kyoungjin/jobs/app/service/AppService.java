package kr.kyoungjin.jobs.app.service;

import java.util.List;
import java.util.Map;

import kr.kyoungjin.dataobject.vo.ExcelUploadVo;

public interface AppService {

	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 19.
	 * @Method Name : excelListByPaging
	 * @return : List<ExcelUploadVo>
	 */
	public List<ExcelUploadVo> excelListByPaging(Map<String,Object> param ) throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 19.
	 * @Method Name : excelDataByPaging
	 * @return : List<ExcelUploadVo>
	 */
	public List<ExcelUploadVo> excelDataByPaging(Map<String, Object> param) throws Exception;
	
}
