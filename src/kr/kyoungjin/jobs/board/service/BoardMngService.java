package kr.kyoungjin.jobs.board.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface BoardMngService {
	/**
	 * @Author : yeste21
	 * @Date : 2020. 3. 25.
	 * @Method Name : getExcelUploadList
	 * @return : List<ExcelUploadVo>
	 */
	public Map<String, Object> getBoardMngList(Map<String,Object> param) throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 10.
	 * @Method Name : deleteExcelData
	 * @return : void
	 */
	public int deleteBoardMng(Map<String, Object> params)   throws Exception;

	public String insertBoardMng( Map<String, Object> params, String uploaderId) throws Exception;
}
