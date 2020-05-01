package kr.kyoungjin.dataobject.dao;

import java.util.List;
import java.util.Map;

import kr.kyoungjin.common.config.Mapper;

@Mapper
public interface AppServiceDao {
	/**
	 * @Author : yester21
	 * @Date : 2020. 5. 1.
	 * @Method Name : selectUpmyundongList
	 * @return : List<Map<String,Object>>
	 */
	public List<Map<String,Object>> selectUpmyundongList(Map<String,Object> param ) throws Exception;
	/**
	 * @Author : yester21
	 * @Date : 2020. 5. 1.
	 * @Method Name : selectAddrressList
	 * @return : List<Map<String,Object>>
	 */
	public List<Map<String,Object>> selectAddrressList(Map<String,Object> param ) throws Exception;
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 5. 1.
	 * @Method Name : updateJobControll
	 * @return : int
	 */
	public int updateJobControll(Map<String,Object> param ) throws Exception;
}
