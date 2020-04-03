package kr.kyoungjin.dataobject.dao;

import kr.kyoungjin.common.config.Mapper;
import kr.kyoungjin.dataobject.vo.BatchHistoryInfoVo;

@Mapper
public interface BatchHistoryInfoDao {
	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 2.
	 * @Method Name : selectBatchKeyGenerate
	 * @return : String
	 */
	public String selectBatchKeyGenerate() throws Exception;
	
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 2.
	 * @Method Name : insertBatchHistory
	 * @return : void
	 */
	public void insertBatchHistory(BatchHistoryInfoVo vo) throws Exception; 
	
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 2.
	 * @Method Name : updateBatchHistory
	 * @return : int
	 */
	public int updateBatchHistory(BatchHistoryInfoVo vo) throws Exception;
}
