package kr.kyoungjin.jobs.board.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kyoungjin.dataobject.dao.BoardMngDao;
import kr.kyoungjin.jobs.board.service.BoardMngService;

@Service
public class BoardMngServiceImpl implements BoardMngService {

	@Autowired
	private BoardMngDao BoardMngDao;

	@Override
	public Map<String, Object> getBoardMngList(Map<String, Object> param) throws Exception {		
		String startNum = (String)param.get("startNum");
		if ( startNum != null && !"".equals(startNum) ) {
			param.put("startNum", Long.parseLong(startNum));
		}
		
		String pageSize = (String)param.get("pageSize");
		if ( pageSize != null && !"".equals(pageSize) ) {
			param.put("pageSize", Long.parseLong(pageSize));
		}
		
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("LIST",  BoardMngDao.selectBoardMngList(param));
		returnMap.put("COUNT", BoardMngDao.selectBoardMngListCount(param));
		
		return returnMap;
	}
	
}
