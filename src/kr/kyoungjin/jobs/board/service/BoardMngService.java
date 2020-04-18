package kr.kyoungjin.jobs.board.service;

import java.util.Map;

public interface BoardMngService {
	/**
	 * @Author : yeste21
	 * @Date : 2020. 3. 25.
	 * @Method Name : getExcelUploadList
	 * @return : List<ExcelUploadVo>
	 */
	public Map<String, Object> getBoardMngList(Map<String,Object> param) throws Exception;
}
