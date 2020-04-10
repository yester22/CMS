package kr.kyoungjin.jobs.excel.service;

import java.util.List;
import java.util.Map;

import kr.kyoungjin.dataobject.vo.ExcelUploadDetailVo;
import kr.kyoungjin.dataobject.vo.ExcelUploadVo;

/**
 * @since 2020. 3. 25.
 * @author yeste21
 * <PRE>
 * -------------------------
 * 개정이력
 * 2020. 3. 25. yeste21 : 최초작성
 * </PRE>
 */
public interface ExcelRetrieveService {
	/**
	 * @Author : yeste21
	 * @Date : 2020. 3. 25.
	 * @Method Name : getExcelUploadList
	 * @return : List<ExcelUploadVo>
	 */
	public Map<String, Object> getExcelUploadList(Map<String,Object> param) throws Exception;
	
	/**
	 * @Author : yeste21
	 * @Date : 2020. 3. 25.
	 * @Method Name : getExcelDetailList
	 * @return : List<ExcelUploadDetailVo>
	 */
	public Map<String, Object> getExcelDetailList(Map<String,Object> param) throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 10.
	 * @Method Name : getExcelUploadInfo
	 * @return : ExcelUploadVo
	 */
	public ExcelUploadVo getExcelUploadInfo(Map<String, Object> params)  throws Exception;

	/**
	 * @Author : yester21
	 * @Date : 2020. 4. 10.
	 * @Method Name : deleteExcelData
	 * @return : void
	 */
	public int deleteExcelData(Map<String, Object> params)   throws Exception;
}
