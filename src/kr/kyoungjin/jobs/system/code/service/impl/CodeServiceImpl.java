/**
 * 
 */
package kr.kyoungjin.jobs.system.code.service.impl;

import java.util.List;
import java.util.Map;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kyoungjin.common.abstractObject.AbstractService;
import kr.kyoungjin.dataobject.dao.CodeDao;
import kr.kyoungjin.jobs.system.code.service.ICodeService;

/**
 * @category 코드 관리 서비스클래스
 * @author 컴퓨터
 * @since 2018.01.11
 *
 */
@Service
public class CodeServiceImpl extends AbstractService implements ICodeService {

	@Autowired
	private CodeDao codeDao;
	
	/* 코드 목록을 가져온다
	 * @param param 파라미터
	 */
	public List<Map<String, Object>> getCodeList(Map<String, Object> param) {
		//List<Map<String, Object>> rtnList = codeDao.getCodeList(param);
		return null;
	}

}
