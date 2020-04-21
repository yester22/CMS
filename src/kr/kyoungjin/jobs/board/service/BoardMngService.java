package kr.kyoungjin.jobs.board.service;

import java.util.Map;

import kr.kyoungjin.dataobject.vo.BoardMngVo;

public interface BoardMngService {
	/**
	 * @Author : yeste21
	 * @Date : 2020. 3. 25.
	 * @Method Name : getBoardMngList
	 * @return : List<BoardMngVo>
	 */
	public Map<String, Object> getBoardMngList(Map<String,Object> param) throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 10.
	 * @Method Name : deleteBoardMng
	 * @return : void
	 */
	public int deleteBoardMng(Map<String, Object> params)   throws Exception;

	public String insertBoardMng( Map<String, Object> params, String uploaderId) throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 10.
	 * @Method Name : getBoardMngRead
	 * @return : BoardMngVo
	 */
	public BoardMngVo getBoardMngRead(Map<String, Object> params)  throws Exception;

	/**
	 * @Author : yeste21
	 * @Date : 2020. 3. 25.
	 * @Method Name : getBoardCode
	 * @return : List<BoardMngVo>
	 */
	public Map<String, Object> getBoardCode(Map<String,Object> param) throws Exception;
}
