package kr.kyoungjin.dataobject.dao;

import java.util.List;

import kr.kyoungjin.common.config.Mapper;
import kr.kyoungjin.dataobject.vo.ExcelUploadDetailVo;
import kr.kyoungjin.dataobject.vo.ExcelUploadVo;

@Mapper
public interface ExcelUploadDao {
	
	
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
	 * @Author : yester21
	 * @Date : 2020. 3. 20.
	 * @Method Name : selectExcelUploaInfo
	 * @return : List<ExcelUploadVo>
	 */
	public List<ExcelUploadVo> selectExcelUploaInfo(ExcelUploadVo vo) throws Exception;
	
	
	/**
	 * @Author : yester21
	 * @Date : 2020. 3. 20.
	 * @Method Name : selectExcelUploaData
	 * @return : List<ExcelUploadDetailVo>
	 */
	public List<ExcelUploadDetailVo> selectExcelUploaData(ExcelUploadVo vo) throws Exception;
}
