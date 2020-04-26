package kr.kyoungjin.dataobject.dao;

import java.util.List;
import java.util.Map;

import kr.kyoungjin.common.config.Mapper;
import kr.kyoungjin.dataobject.vo.BoardVo;
import kr.kyoungjin.dataobject.vo.CodeVo;
import kr.kyoungjin.dataobject.vo.ExcelUploadDetailVo;

@Mapper
public interface CodeDao {
	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 20.
	 * @Method Name : selectCodeKeyRead
	 * @return : List<CodeVo>
	 */
	public List<CodeVo> selectCodeKeyRead(Map<String, Object> param) throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 20.
	 * @Method Name : selectCodeList
	 * @return : List<CodeVo>
	 */
	public List<CodeVo> selectCodeList(Map<String, Object> param) throws Exception;
	
	
	/**
	 * @Author : yeste
	 * @Date : 2020. 3. 25.
	 * @Method Name : selectCodeListCount
	 * @return : Long
	 */
	public Long selectCodeListCount(Map<String, Object> param) throws Exception;

	/**
	 * @Author : yester21`
	 * @Date : 2020. 4. 10.
	 * @Method Name : deleteCode
	 * @return : int
	 */
	public int deleteCode(Map<String, Object> params)  throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 20.
	 * @Method Name : insertCode
	 * @return : void
	 */
	public void insertCode(CodeVo vo) throws Exception;

	/**
	 * @Author : yeste
	 * @Date : 2020. 3. 23.
	 * @Method Name : updateCode
	 * @return : void
	 */
	public void updateCode(CodeVo saveCodeUpload) throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 10.
	 * @Method Name : selectCodeRead
	 * @return : CodeVo
	 */
	public CodeVo selectCodeRead(CodeVo params)  throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 31.
	 * @Method Name : selectCodeKey
	 * @return : int
	 */
	public int selectCodeKey(CodeVo vo) throws Exception;
}
