package kr.kyoungjin.jobs.board.service;

import java.util.Map;

import kr.kyoungjin.dataobject.vo.BoardVo;

public interface BoardService {
	/**
	 * @Author : yeste21
	 * @Date : 2020. 3. 25.
	 * @Method Name : getExcelUploadList
	 * @return : List<ExcelUploadVo>
	 */
	public Map<String, Object> getBoardList(Map<String,Object> param) throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 10.
	 * @Method Name : deleteExcelData
	 * @return : void
	 */
	public int deleteBoard(Map<String, Object> params)   throws Exception;

	public String insertBoard( Map<String, Object> params, String uploaderId) throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 10.
	 * @Method Name : getExcelUploadInfo
	 * @return : ExcelUploadVo
	 */
	public BoardVo getBoardRead(Map<String, Object> params)  throws Exception;
}
