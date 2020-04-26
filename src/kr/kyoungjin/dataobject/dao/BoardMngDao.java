package kr.kyoungjin.dataobject.dao;

import java.util.List;
import java.util.Map;

import kr.kyoungjin.common.config.Mapper;
import kr.kyoungjin.dataobject.vo.BoardMngVo;

@Mapper
public interface BoardMngDao {
	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 20.
	 * @Method Name : selectBoardMngList
	 * @return : List<BoardMngVo>
	 */
	public List<BoardMngVo> selectBoardMngList(Map<String, Object> param) throws Exception;
	
	
	/**
	 * @Author : yeste
	 * @Date : 2020. 3. 25.
	 * @Method Name : selectBoardMngListCount
	 * @return : Long
	 */
	public Long selectBoardMngListCount(Map<String, Object> param) throws Exception;

	/**
	 * @Author : yester21`
	 * @Date : 2020. 4. 10.
	 * @Method Name : deleteBoardMng
	 * @return : int
	 */
	public int deleteBoardMng(Map<String, Object> params)  throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 20.
	 * @Method Name : selectNewBoardKey
	 * @return : String
	 */
	public String selectNewBoardKey() throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 20.
	 * @Method Name : insertBoardMng
	 * @return : void
	 */
	public void insertBoardMng(BoardMngVo vo) throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 10.
	 * @Method Name : selectBoardMngRead
	 * @return : void
	 */
	public BoardMngVo selectBoardMngRead(BoardMngVo params)  throws Exception;

	/**
	 * @Author : yeste
	 * @Date : 2020. 3. 23.
	 * @Method Name : updateBoardMng
	 * @return : void
	 */
	public void updateBoardMng(BoardMngVo saveBoardUpload) throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 20.
	 * @Method Name : selectBoardCode
	 * @return : List<BoardMngVo>
	 */
	public List<BoardMngVo> selectBoardCode(Map<String, Object> param) throws Exception;
}
