package kr.kyoungjin.jobs.excel.service;

import java.util.List;
import java.util.Map;

import kr.kyoungjin.dataobject.vo.ExcelUploadVo;

public interface JobBindingService {
	/**
	 * @Author : yester21
	 * @Date : 2020. 5. 12.
	 * @Method Name : selectExcelList
	 * @return : List<ExcelUploadVo>
	 */
	public List<ExcelUploadVo> getExcelList(Map<String,Object> parameter) throws Exception;
}
