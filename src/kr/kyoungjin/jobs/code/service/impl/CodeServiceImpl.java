package kr.kyoungjin.jobs.code.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import kr.kyoungjin.dataobject.dao.CodeDao;
import kr.kyoungjin.dataobject.vo.BoardVo;
import kr.kyoungjin.dataobject.vo.CodeVo;
import kr.kyoungjin.jobs.code.service.CodeService;

public class CodeServiceImpl implements CodeService {

	@Autowired
	private CodeDao CodeDao;

	@Override
	public Map<String, Object> getCodeKeyRead(Map<String, Object> param) throws Exception {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("LIST",  CodeDao.selectCodeKeyRead(param));
		
		return returnMap;
	}

	@Override
	public Map<String, Object> getCodeList(Map<String, Object> param) throws Exception {		
		String startNum = (String)param.get("startNum");
		if ( startNum != null && !"".equals(startNum) ) {
			param.put("startNum", Long.parseLong(startNum));
		}
		
		String pageSize = (String)param.get("pageSize");
		if ( pageSize != null && !"".equals(pageSize) ) {
			param.put("pageSize", Long.parseLong(pageSize));
		}
		
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("LIST",  CodeDao.selectCodeList(param));
		returnMap.put("COUNT", CodeDao.selectCodeListCount(param));
		
		return returnMap;
	}

	@Override
	public int deleteCode(Map<String, Object> params) throws Exception {
		return CodeDao.deleteCode(params);
	}

	@Override
	@Transactional
	public String insertCode(Map<String,Object> params , String uploaderId) throws Exception {
		String codeSaveKey = params.get("codeSaveKey").toString();

		CodeVo saveCodeUpload = new CodeVo();
		saveCodeUpload.setCode(params.get("codeKey").toString());
		saveCodeUpload.setUpperCdKey(params.get("upperCode").toString());
		saveCodeUpload.setCodeNm(params.get("codeName").toString());
		saveCodeUpload.setOrderSeq(params.get("sortNum").toString());
		saveCodeUpload.setUseYn(params.get("codeUseYn").toString());

		int checkCodeKey = CodeDao.selectCodeKey(saveCodeUpload);

		if(checkCodeKey > 0) {
			codeSaveKey = "R";
		} else {
			if("C".equals(codeSaveKey)) {
				saveCodeUpload.setRegId(uploaderId);
				
				CodeDao.insertCode(saveCodeUpload);
			} else {
				CodeDao.updateCode(saveCodeUpload);
			}
		}

		return codeSaveKey;
	}

	@Override
	public CodeVo getCodeRead(Map<String, Object> params)  throws Exception {
		CodeVo paramObj = new CodeVo();
		paramObj.setCode(params.get("codeSeq").toString());
		return CodeDao.selectCodeRead(paramObj);
	}
}
