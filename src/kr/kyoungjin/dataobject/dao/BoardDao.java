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
	 * @Method Name : selectExcelUploaInfo
	 * @return : List<BoardVo>
	 */
	public List<BoardVo> selectBoardList(Map<String, Object> param) throws Exception;
	
	
	/**
	 * @Author : yeste
	 * @Date : 2020. 3. 25.
	 * @Method Name : selectExcelUploaInfoCount
	 * @return : Long
	 */
	public Long selectBoardListCount(Map<String, Object> param) throws Exception;

	/**
	 * @Author : yester21`
	 * @Date : 2020. 4. 10.
	 * @Method Name : deleteExcelData
	 * @return : int
	 */
	public int deleteBoard(Map<String, Object> params)  throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 20.
	 * @Method Name : selectExcelKey
	 * @return : String
	 */
	public String selectNewBoardKey() throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 20.
	 * @Method Name : insertExcelUploaInfo
	 * @return : void
	 */
	public void insertBoard(BoardVo vo) throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 10.
	 * @Method Name : selectExcelInfoData
	 * @return : void
	 */
	public BoardVo selectBoardRead(BoardVo params)  throws Exception;

	/**
	 * @Author : yeste
	 * @Date : 2020. 3. 23.
	 * @Method Name : updateExcelUploaInfo
	 * @return : void
	 */
	public void updateBoard(BoardVo saveBoardUpload) throws Exception;
}
