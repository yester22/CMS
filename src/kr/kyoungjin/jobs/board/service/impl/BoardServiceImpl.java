package kr.kyoungjin.jobs.board.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.kyoungjin.common.abstractObject.ConstantNames;
import kr.kyoungjin.common.util.FileUtil;
import kr.kyoungjin.dataobject.dao.BoardDao;
import kr.kyoungjin.dataobject.vo.BoardVo;
import kr.kyoungjin.dataobject.vo.ExcelUploadVo;
import kr.kyoungjin.jobs.board.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao BoardDao;

	@Override
	public Map<String, Object> getBoardList(Map<String, Object> param) throws Exception {		
		String startNum = (String)param.get("startNum");
		if ( startNum != null && !"".equals(startNum) ) {
			param.put("startNum", Long.parseLong(startNum));
		}
		
		String pageSize = (String)param.get("pageSize");
		if ( pageSize != null && !"".equals(pageSize) ) {
			param.put("pageSize", Long.parseLong(pageSize));
		}
		
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("LIST",  BoardDao.selectBoardList(param));
		returnMap.put("COUNT", BoardDao.selectBoardListCount(param));
		
		return returnMap;
	}

	@Override
	public int deleteBoard(Map<String, Object> params) throws Exception {
		return BoardDao.deleteBoard(params);
		
	}

	@Override
	@Transactional
	public String insertBoard(Map<String,Object> params , String uploaderId) throws Exception {

		String boardSaveKey = params.get("boardSaveKey").toString();

		BoardVo saveBoardUpload = new BoardVo();
		saveBoardUpload.setBoardCd(params.get("boardCode").toString());
		saveBoardUpload.setTitle(params.get("boardTitle").toString());
		saveBoardUpload.setBody(params.get("boardContent").toString());
		saveBoardUpload.setTag(params.get("boardTag").toString());
		saveBoardUpload.setHtmlYn(params.get("boardHtmlYn").toString());

		if("C".equals(boardSaveKey)) {
			saveBoardUpload.setRegId(uploaderId);
			
			BoardDao.insertBoard(saveBoardUpload);
		} else {
			saveBoardUpload.setBoardSeq(params.get("boardSeq").toString());
			saveBoardUpload.setUptId(uploaderId);

			BoardDao.updateBoard(saveBoardUpload);
		}

		return boardSaveKey;
	}

	@Override
	public BoardVo getBoardRead(Map<String, Object> params)  throws Exception {
		BoardVo paramObj = new BoardVo();
		paramObj.setBoardSeq(params.get("boardSeq").toString());
		return BoardDao.selectBoardRead(paramObj);
	}
}
