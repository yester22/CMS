package kr.kyoungjin.jobs.board.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.kyoungjin.common.abstractObject.ConstantNames;
import kr.kyoungjin.common.util.FileUtil;
import kr.kyoungjin.dataobject.dao.BoardMngDao;
import kr.kyoungjin.dataobject.vo.BoardMngVo;
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

	@Override
	public int deleteBoardMng(Map<String, Object> params) throws Exception {
		return BoardMngDao.deleteBoardMng(params);
		
	}

	@Override
	@Transactional
	public String insertBoardMng(Map<String,Object> params , String uploaderId) throws Exception {

		String sNewBoardKey = BoardMngDao.selectNewBoardKey();
		
		BoardMngVo saveBoardUpload = new BoardMngVo();
		saveBoardUpload.setBoardCd(sNewBoardKey);
		saveBoardUpload.setBoardNm(params.get("boardName").toString());
		saveBoardUpload.setListRowCnt(params.get("listSize").toString());
		saveBoardUpload.setListBlockCnt(params.get("pageBlockSize").toString());
		saveBoardUpload.setTitleSplitLen(params.get("titleLength").toString());
		saveBoardUpload.setContentsSplitLen(params.get("contentLength").toString());
		saveBoardUpload.setUseYn(params.get("useStates").toString());
		saveBoardUpload.setRegId(uploaderId);
		
		BoardMngDao.insertBoardMng(saveBoardUpload);
		
		//BoardMngDao.updateExcelUploaInfo(saveBoardUpload);
		
		return sNewBoardKey;
	}
}
