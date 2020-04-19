package kr.kyoungjin.dataobject.dao;

import java.util.List;
import java.util.Map;

import kr.kyoungjin.common.config.Mapper;
import kr.kyoungjin.dataobject.vo.BoardMngVo;
import kr.kyoungjin.dataobject.vo.ExcelUploadVo;

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



	public List<BoardMngVo> list ( BoardMngVo boardMngVo);
	public BoardMngVo view ( BoardMngVo boardMngVo);
	public void insert(BoardMngVo boardMngVo);
	public int  update(BoardMngVo boardMngVo);
	public int  delete(BoardMngVo boardMngVo);
	public int  count(BoardMngVo boardMngVo);
}
