package kr.kyoungjin.jobs.app.service;

import java.util.List;
import java.util.Map;

import kr.kyoungjin.dataobject.vo.ExcelUploadVo;

public interface AppService {

	public List<Map<String,Object>> selectUpmyundongList(Map<String,Object> param ) throws Exception;
	public List<Map<String,Object>> selectAddrressList(Map<String,Object> param ) throws Exception;
	public int updateJobControll(Map<String,Object> param ) throws Exception;
	
}
