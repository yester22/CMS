package kr.kyoungjin.dataobject.dao;

import java.util.List;
import java.util.Map;

import kr.kyoungjin.common.config.Mapper;
import kr.kyoungjin.dataobject.vo.BoardVo;

@Mapper
public interface BoardDao {
	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 20.
	 * @Method Name : selectBoardList
	 * @return : List<BoardVo>
	 */
	public List<BoardVo> selectBoardList(Map<String, Object> param) throws Exception;
	
	
	/**
	 * @Author : yeste
	 * @Date : 2020. 3. 25.
	 * @Method Name : selectBoardListCount
	 * @return : Long
	 */
	public Long selectBoardListCount(Map<String, Object> param) throws Exception;

	/**
	 * @Author : yester21`
	 * @Date : 2020. 4. 10.
	 * @Method Name : deleteBoard
	 * @return : int
	 */
	public int deleteBoard(Map<String, Object> params)  throws Exception;

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
	 * @Method Name : insertBoard
	 * @return : void
	 */
	public void insertBoard(BoardVo vo) throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 10.
	 * @Method Name : selectBoardRead
	 * @return : BoardVo
	 */
	public BoardVo selectBoardRead(BoardVo params)  throws Exception;

	/**
	 * @Author : yeste
	 * @Date : 2020. 3. 23.
	 * @Method Name : updateBoard
	 * @return : void
	 */
	public void updateBoard(BoardVo saveBoardUpload) throws Exception;
}
