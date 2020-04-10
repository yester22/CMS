package kr.kyoungjin.dataobject.dao;

import java.util.List;
import java.util.Map;

import kr.kyoungjin.common.config.Mapper;
import kr.kyoungjin.dataobject.vo.ExcelUploadDetailVo;
import kr.kyoungjin.dataobject.vo.ExcelUploadVo;

@Mapper
public interface ExcelDao {
	
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 20.
	 * @Method Name : selectExcelKey
	 * @return : String
	 */
	public String selectNewExcelKey() throws Exception;
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 20.
	 * @Method Name : insertExcelUploaInfo
	 * @return : void
	 */
	public void insertExcelUploaInfo(ExcelUploadVo vo) throws Exception;
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 20.
	 * @Method Name : insertExcelDetailInfo
	 * @return : void
	 */
	public void insertExcelDetailInfo(ExcelUploadDetailVo vo) throws Exception;
	

	/**
	 * @Author : yeste
	 * @Date : 2020. 3. 23.
	 * @Method Name : updateExcelUploaInfo
	 * @return : void
	 */
	public void updateExcelUploaInfo(ExcelUploadVo saveExcelUpload) throws Exception;
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 20.
	 * @Method Name : selectExcelUploaInfo
	 * @return : List<ExcelUploadVo>
	 */
	public List<ExcelUploadVo> selectExcelUploaInfo(Map<String, Object> param) throws Exception;
	
	
	/**
	 * @Author : yeste
	 * @Date : 2020. 3. 25.
	 * @Method Name : selectExcelUploaInfoCount
	 * @return : Long
	 */
	public Long selectExcelUploaInfoCount(Map<String, Object> param) throws Exception;
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 20.
	 * @Method Name : selectExcelUploaData
	 * @return : List<ExcelUploadDetailVo>
	 */
	public List<ExcelUploadDetailVo> selectExcelUploaData(Map<String, Object> param) throws Exception;

	
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 25.
	 * @Method Name : selectExcelUploaDataCount
	 * @return : Long
	 */
	public Long selectExcelUploaDataCount(Map<String, Object> param) throws Exception;
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 31.
	 * @Method Name : selectPmuList
	 * @return : List<ExcelUploadDetailVo>
	 */
	public List<ExcelUploadDetailVo> selectPmuList(Map<String, Object> param) throws Exception;
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 31.
	 * @Method Name : updateExcelDataPmuCd
	 * @return : int
	 */
	public int updateExcelDataPmuCd(ExcelUploadDetailVo vo) throws Exception;
	
	
	/**
	 * @Author : yeste
	 * @Date : 2020. 4. 2.
	 * @Method Name : selectExcelKeyForStatusConfirm
	 * @return : String
	 */
	public String selectExcelKeyForStatusConfirm(ExcelUploadVo vo)  throws Exception;
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 31.
	 * @Method Name : selectExcelUploaDataAddress
	 * @return : List<ExcelUploadDetailVo>
	 */
	public List<ExcelUploadDetailVo> selectExcelUploaDataAddress(Map<String, Object> param) throws Exception;
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 2.
	 * @Method Name : updateExcelDataForService
	 * @return : int
	 */
	public int updateExcelDataForService(ExcelUploadDetailVo vo) throws Exception;

	/**
	 * @Author : yeste
	 * @Date : 2020. 4. 2.
	 * @Method Name : updateExcelUpoadStatus
	 * @return : int
	 */
	public int updateExcelUpoadStatus(ExcelUploadVo excelUploadVo) throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 10.
	 * @Method Name : selectExcelInfoData
	 * @return : void
	 */
	public ExcelUploadVo selectExcelInfoData(ExcelUploadVo params)  throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 10.
	 * @Method Name : deleteExcelData
	 * @return : int
	 */
	public int deleteExcelData(Map<String, Object> params)  throws Exception;
}
