package kr.kyoungjin.jobs.excel.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kyoungjin.common.abstractObject.AbstractService;
import kr.kyoungjin.dataobject.dao.JobBindingDao;
import kr.kyoungjin.dataobject.vo.ExcelUploadVo;
import kr.kyoungjin.jobs.excel.service.JobBindingService;

/**
 * @since 2020. 5. 14.
 * @author yeste
 * <PRE>
 * -------------------------
 * 개정이력
 * 2020. 5. 14. yeste : 최초작성
 * </PRE>
 */
@Service
public class JobBindingServiceImpl extends AbstractService implements JobBindingService {

	private Logger logger  = LoggerFactory.getLogger(JobBindingServiceImpl.class);
	
	@Autowired
	private JobBindingDao jobBindingDao;
	
	
	@Override
	public List<ExcelUploadVo> getExcelList(Map<String, Object> parameter) throws Exception {
		
		if ( logger.isDebugEnabled() ) {
			logger.debug(" getExcelList parameter = " + parameter.toString()); 
		}
		
		return jobBindingDao.selectExcelList(parameter);
	}
	
}
