package kr.kyoungjin.jobs.app.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kyoungjin.common.abstractObject.AbstractService;
import kr.kyoungjin.dataobject.dao.ExcelDao;
import kr.kyoungjin.dataobject.vo.ExcelUploadVo;
import kr.kyoungjin.jobs.app.service.AppService;

@Service
public class AppServiceImpl extends AbstractService implements AppService {
	private Logger logger  = LoggerFactory.getLogger(AppServiceImpl.class);
	
	@Autowired
	private ExcelDao excelDao;

	@Override
	public List<ExcelUploadVo> excelListByPaging(Map<String, Object> param) throws Exception {
		if ( !authAccess(param) ) return null;
		
		
		
		return null;
		
		
	}
	
	
	@Override
	public List<ExcelUploadVo> excelDataByPaging(Map<String, Object> param) throws Exception {
		return null;
		
	}
	
	
	
	/** 파라미터 입력처리 확인
	 * @Author : yeste
	 * @Date : 2020. 4. 19.
	 * @Method Name : authAccess
	 * @return : boolean
	 */
	private boolean authAccess(Map<String, Object> param) throws Exception {
		String tokenValue = (String)param.get("TOKEN_VALUE");
		boolean bAuth = true;
		if ( tokenValue == null || "".equals(tokenValue) ) {
			return false;
		} else {
			
		}
		return bAuth;
	}
	
}
