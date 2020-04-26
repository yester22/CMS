package kr.kyoungjin.jobs.code.service;

import java.util.Map;

import kr.kyoungjin.dataobject.vo.CodeVo;

public interface CodeService {
	/**
	 * @Author : yeste21
	 * @Date : 2020. 3. 25.
	 * @Method Name : getCodeKeyRead
	 * @return : List<CodeVo>
	 */
	public Map<String, Object> getCodeKeyRead(Map<String,Object> param) throws Exception;

	/**
	 * @Author : yeste21
	 * @Date : 2020. 3. 25.
	 * @Method Name : getCodeList
	 * @return : List<CodeVo>
	 */
	public Map<String, Object> getCodeList(Map<String,Object> param) throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 10.
	 * @Method Name : deleteCode
	 * @return : int
	 */
	public int deleteCode(Map<String, Object> params)   throws Exception;

	public String insertCode( Map<String, Object> params, String uploaderId) throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 10.
	 * @Method Name : getCodeRead
	 * @return : CodeVo
	 */
	public CodeVo getCodeRead(Map<String, Object> params)  throws Exception;
}
