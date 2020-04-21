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
	 * @Method Name : selectExcelUploaInfo
	 * @return : List<BoardMngVo>
	 */
	public List<BoardMngVo> selectBoardMngList(Map<String, Object> param) throws Exception;
	
	
	/**
	 * @Author : yeste
	 * @Date : 2020. 3. 25.
	 * @Method Name : selectExcelUploaInfoCount
	 * @return : Long
	 */
	public Long selectBoardMngListCount(Map<String, Object> param) throws Exception;

	/**
	 * @Author : yester21`
	 * @Date : 2020. 4. 10.
	 * @Method Name : deleteExcelData
	 * @return : int
	 */
	public int deleteBoardMng(Map<String, Object> params)  throws Exception;

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
	public void insertBoardMng(BoardMngVo vo) throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 10.
	 * @Method Name : selectExcelInfoData
	 * @return : void
	 */
	public BoardMngVo selectBoardMngRead(BoardMngVo params)  throws Exception;

	/**
	 * @Author : yeste
	 * @Date : 2020. 3. 23.
	 * @Method Name : updateExcelUploaInfo
	 * @return : void
	 */
	public void updateBoardMng(BoardMngVo saveBoardUpload) throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 20.
	 * @Method Name : selectExcelUploaInfo
	 * @return : List<BoardMngVo>
	 */
	public List<BoardMngVo> selectBoardCode(Map<String, Object> param) throws Exception;
}
