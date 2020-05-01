package kr.kyoungjin.jobs.app.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kyoungjin.common.abstractObject.AbstractService;
import kr.kyoungjin.dataobject.dao.AppServiceDao;
import kr.kyoungjin.dataobject.dao.ExcelDao;
import kr.kyoungjin.dataobject.vo.ExcelUploadVo;
import kr.kyoungjin.jobs.app.service.AppService;

@Service
public class AppServiceImpl extends AbstractService implements AppService {
	private Logger logger  = LoggerFactory.getLogger(AppServiceImpl.class);
	
	@Autowired
	private AppServiceDao appServiceDao;


	@Override
	public List<Map<String, Object>> selectUpmyundongList(Map<String, Object> param) throws Exception {
		return appServiceDao.selectUpmyundongList(param);
	}

	@Override
	public List<Map<String, Object>> selectAddrressList(Map<String, Object> param) throws Exception {
		return appServiceDao.selectAddrressList(param);
	}

	@Override
	public int updateJobControll(Map<String, Object> param) throws Exception {
		return updateJobControll(param);
	}
}