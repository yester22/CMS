package kr.kyoungjin.dataobject.dao;

import java.util.List;
import java.util.Map;

import kr.kyoungjin.common.config.Mapper;
import kr.kyoungjin.dataobject.vo.ExcelUploadVo;

@Mapper
public interface JobBindingDao {
	/**
	 * @Author : yester21
	 * @Date : 2020. 5. 12.
	 * @Method Name : selectExcelList
	 * @return : List<ExcelUploadVo>
	 */
	public List<ExcelUploadVo> selectExcelList(Map<String,Object> parameter) throws Exception;
}
